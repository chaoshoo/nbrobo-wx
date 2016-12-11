package com.net.wx.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RedisService {


	//	private Logger LOG = LoggerFactory.getLogger(RedisService.class);

	@Autowired
	private List<RedisTemplate<String, String>> redisTemplateList;

	/**
	 * Delete the specified from the cachekey
	 * @param keys
	 */
	public void del(final String... keys) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<Long>() {
					public Long doInRedis(RedisConnection connection) throws DataAccessException {
						long result = 0;
						for (int i = 0; i < keys.length; i++) {
							result = connection.del(keys[i].getBytes());
						}
						return result;
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Delete the specifiedkey pattern matching，Low efficiency
	 * @param keys
	 */
	public void delByReg(final String... keys) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<Long>() {
					public Long doInRedis(RedisConnection connection) throws DataAccessException {
						long result = 0;
						for (int i = 0; i < keys.length; i++) {
							Set<byte[]> keyset = connection.keys((keys[i] + "*").getBytes());
							for (byte[] key : keyset) {
								result = connection.del(key);
							}
						}
						return result;
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * To determine if a key is present in the cache
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		Boolean result = false;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				result = (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
					public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.exists(key.getBytes());
					}
				});

				if (result) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Insert data into the cache
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		if(value==null)return;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<Object>() {
					public Long doInRedis(final RedisConnection connection) throws DataAccessException {
						connection.set(key, value);
						if (liveTime > 0) {
							connection.expire(key, liveTime);
						}
						return 1L;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public <T> void set(final byte[] key, final Object value, final long liveTime, Class<T> type) {
		final JacksonJsonRedisSerializer<T> serializer = new JacksonJsonRedisSerializer<T>(type);
		byte[] bv = serializer.serialize(value);
		this.set(key, bv, liveTime);
	}

	public void set(String key, String value, long liveTime) {
		if(value==null)return;
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	/**
	 * Get data from the cache
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		String cacheValue = "";
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				cacheValue = redisTemplate.execute(new RedisCallback<String>() {
					public String doInRedis(RedisConnection connection) throws DataAccessException {
						try {
							byte[] cacheBytes = connection.get(key.getBytes());
							if (cacheBytes != null) {
								String cacheStr = new String(cacheBytes, "utf-8");
								return cacheStr;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return "";
					}
				});
				if (!StringUtils.isEmpty(cacheValue))
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cacheValue;
	}

	public <T> T get(final String key, final Class<T> clazz) {
		T t = null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			t = redisTemplate.execute(new RedisCallback<T>() {
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] data = connection.get(key.getBytes());
					if (data != null)
						return new JacksonJsonRedisSerializer<T>(clazz).deserialize(data);
					return null;
				}
			});

			if (t != null)
				break;
		}
		return t;
	}

	/**
	 * wipe cache
	 */
	public void flushDB() {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<String>() {
					public String doInRedis(RedisConnection connection) throws DataAccessException {
						connection.flushDb();
						return "ok";
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Add to ordered set
	 * @param key
	 * @param score
	 * @param value
	 */
	public void zadd(final byte[] key, final double score, final byte[] value) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<String>() {
					public String doInRedis(RedisConnection connection) throws DataAccessException {
						connection.zAdd(key, score, value);
						return "ok";
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Acquisition of ordered set of subsets of elements
	 * @param key
	 *            Ordered setkey
	 * @param min
	 *            Minimum range
	 * @param max
	 *            Maximum range
	 * @param offset
	 *            From the article0ffset+1One element
	 * @param count
	 *            Return cap
	 * @return
	 */
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
			final long count) {
		Set<byte[]> set = null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				set = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {

					public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.zRangeByScore(key, min, max, offset, count);
					}

				});
				if (set != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	/**
	 * Add the specifiedmapTo cache
	 * @param key mapUnique identifier
	 * @param hashes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hMSet(final byte[] key, final Map<byte[], byte[]> hashes) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback() {
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						connection.hMSet(key, hashes);
						return null;
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void hSet(final String key, final String field,final String value) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback() {
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						connection.hSet(key.getBytes(), field.getBytes(), value.getBytes());
						return null;
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the specifiedmapList of values corresponding to the specified key in the
	 * @param key mapUnique identifier
	 * @param fields Key array
	 * @return Value array
	 */
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		List<byte[]> cacheValue = null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				cacheValue = redisTemplate.execute(new RedisCallback<List<byte[]>>() {
					public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.hMGet(key, fields);
					}
				});
				if (cacheValue != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cacheValue;
	}
	
	public String  hGet(final String key, final String field) {
		byte[] cacheValue = null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				cacheValue = redisTemplate.execute(new RedisCallback<byte[]>() {
					public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.hGet(key.getBytes(), field.getBytes());
					}
				});
				if (cacheValue != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(cacheValue == null){
			return null;
		}
		return new String(cacheValue);
	}
	
	public Map<String,String> hMGetAll(final byte[] key) {
		Map<byte[],byte[]> cacheValue = null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				cacheValue = redisTemplate.execute(new RedisCallback<Map<byte[],byte[]>>() {
					public Map<byte[],byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.hGetAll(key);
					}
				});
				if (cacheValue != null) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(cacheValue != null){
			Map<String,String> m = new HashMap<String, String>();
			for (byte[] k : cacheValue.keySet()) {
				try {
					m.put(new String(k), new String(cacheValue.get(k),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return m;
		}
		return null;
	}
	
	/**
	 * AddObjectTo cache
	 * 
	 */
	public void setObj(final byte[] key, final Object obj,final Long liveTime) {
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				redisTemplate.execute(new RedisCallback<Object>() {
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
						byte[] bobj = serializer.serialize(obj);
						connection.set(key, bobj);
						if (liveTime > 0) {
							connection.expire(key, liveTime);
						}
						return null;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * takeObject
	 * IfObjectproperties changing，Error during serialization，Returnerror
	 * @param key
	 */
	public Object getObj(final byte[] key){
		Object obj=null;
		for (RedisTemplate<String, String> redisTemplate : redisTemplateList) {
			try {
				obj = redisTemplate.execute(new RedisCallback<Object>() {
					@Override
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						byte[] data = connection.get(key);
						return new JdkSerializationRedisSerializer().deserialize(data);
					}
					
				});
			}catch (Exception e) {
				e.printStackTrace();
				obj = "error";
			}
		}
		return obj;
	}
	

	// getters and setters
	public List<RedisTemplate<String, String>> getRedisTemplateList() {
		return redisTemplateList;
	}

	public void setRedisTemplateList(List<RedisTemplate<String, String>> redisTemplateList) {
		this.redisTemplateList = redisTemplateList;
	}


}