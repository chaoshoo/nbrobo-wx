package com.net.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class DateUtil extends DateSupport {
	
	private static final long ONE_DAY_TIME = 24*60*60*1000;

	public static String nowDateForStr(String format) {
		Date date = new Date();

		return dateForString(date, format);
	}

	/**
	 * Based formatyyyy-MM-dd,Gets the current date
	 * 
	 * @author tangshengyu
	 * @version falvm
	 * @date Dec 9, 2009
	 * @return String
	 */
	public static String nowDateForStrYMD() {

		return nowDateForStr("yyyy-MM-dd");

	}

	public static String nowDateForStrYMDHMS() {

		return nowDateForStr("yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * Get the last day of the current month based on the current date
	 * 
	 * @return
	 * @author tangshengyu
	 * @version panyu
	 * @date Jul 16, 2010
	 * @return String
	 */
	public static String dateForMonthLastDayYMD(String strDate) {
		int lastDay = getDateMonthLastDay(strDate);
		Date d = strForDate(strDate, "yyyy-MM-dd");
		d.setDate(lastDay);
		return dateForString(d, "yyyy-MM-dd");
	}

	public static void main(String args[]) {
		Date d = new Date();
		Date da = DateUtil.strForDate("2009-09-09 11:11:11",
				"yyyy-MM-dd hh:mm:ss");
		DateUtil.dateForString(da, "yyyyMMddHHmmss");
		System.out.println(DateUtil.nowDateForStr("HH:mm:ss"));
		System.out.println(DateUtil.dateForString(strForDate("17:38:23",
				"HH:mm:ss"), "HH:mm:ss"));
	}

	/**
	 * Take current year
	 * 
	 * @return
	 */
	public static String getYMDHMSS() {
		// TODO Auto-generated method stub
		return nowDateForStr("yyyyMMddHHmmss");
	}

	/**
	 * Take the specified time
	 * 
	 * @param from
	 *            Start from today. A few days ago.，Just after a few days
	 * @param format
	 *            Format type
	 *            
	 *            For example, take the previous daygetDate(-1, "YYYY_MM_DD")
	 *            One day after taking getDate(1, "YYYY_MM_DD");
	 * @return
	 */
	public static String getDate(int from, String format) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, from);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String day = dateFormat.format(c.getTime());
		return day;
	}

	/**
	 * Get the current morning0Point of time
	 * @return
	 */
	public static Date getCurrentZeroPoint(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * Get a period of time
	 * yyyy-MM-dd hh:mm:ss
	 * @param beginDay
	 * @param endDay
	 * @return
	 */
	public static String[] getYearBasis(String beginDay,String endDay){
		String[] section = new String[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginTime = dateFormat.parse(beginDay);
			Date endTime = dateFormat.parse(endDay);
			
			Calendar c= Calendar.getInstance();
			c.setTime(beginTime);
			c.add(Calendar.YEAR, -1);
			beginTime = c.getTime();
			section[0] = dateFormat.format(beginTime);
			
			c.setTime(endTime);
			c.add(Calendar.YEAR, -1);
			endTime = c.getTime();
			section[1] = dateFormat.format(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return section;
	}
	
	/**
	 * Get the chain period of time
	 * @param beginDay
	 * @param endDay
	 * @return
	 */
	public static String[] getLinkRelativeRatio(String beginDay,String endDay){
		String[] section = new String[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginTime = dateFormat.parse(beginDay);
			Date endTime = dateFormat.parse(endDay);
			
			long sectionTime = endTime.getTime() - beginTime.getTime();
			
			endTime = new Date(beginTime.getTime()-ONE_DAY_TIME);
			beginTime = new Date(endTime.getTime()-sectionTime);
			
			section[0] = dateFormat.format(beginTime);
			section[1] = dateFormat.format(endTime);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return section;
	}
	
	public static void transMap2Bean(Map<String, Object> map, Object obj) {  
	    //ConvertUtils.register(new DateLocaleConverter(), Date.class);
	    ConvertUtils.register(new Converter()  
	    {  
	         
	   
	      @SuppressWarnings("rawtypes")  
	      @Override  
	      public Object convert(Class arg0, Object arg1)  
	      {  
//	        System.out.println("注册字符串转换为date类型转换器");  
	        if(arg1 == null)  
	        {  
	          return null;  
	        }  
	        if(!(arg1 instanceof String))  
	        {  
	          throw new ConversionException("Only support string conversion !");  
	        }  
	        String str = (String)arg1;  
	        if(str.trim().equals(""))  
	        {  
	          return null;  
	        }  
	           
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	           
	        try{  
	          return sd.parse(str);  
	        }  
	        catch(ParseException e)  
	        {  
	          throw new RuntimeException(e);  
	        }  
	           
	      }  
	         
	    }, java.util.Date.class);  
	    if (map == null || obj == null) {  
	      return;  
	    }  
	    try {  
	    	String key = null;
	    	Object value = null;
	    	Map newMap = new HashMap();
	      for(Map.Entry<String, Object> entry:map.entrySet()){
	    	 key = entry.getKey();
	    	 value = entry.getValue();
	    	 newMap.put(key.toLowerCase().replace("_", ""), value);
	      }
	      BeanUtils.populate(obj, newMap);  
	    } catch (Exception e) {  
	      System.out.println("Map<String,Object>转化Bean异常：" + e);  
	    }  
	  }
	
	
	public static void  populate2(Object obj,Map<String,Object> map){
		Class clazz = obj.getClass();
		
		Map<String,Method> mapValue = new HashMap<String,Method>();
		
		Method[] methods = clazz.getDeclaredMethods();

		String methodName = null;
		for(Method method:methods){
			methodName = method.getName();
			if(methodName.startsWith("set")){
				mapValue.put(methodName.substring(3).toLowerCase(), method);
			}
		}
		
		try {
			String key = null;
			for(Map.Entry<String, Object> entry:map.entrySet()){
					key = entry.getKey();
					if(mapValue.containsKey(key)){
						mapValue.get(key).invoke(obj, entry.getValue()+"");
					}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Calculate the number of days that the difference between the two dates
     * @param smdate Less time
     * @param bdate  Larger time
     * @return return int
     * @throws ParseException
     */
    public static int daysBetween(Date begin,Date end) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		begin=sdf.parse(sdf.format(begin));
		end=sdf.parse(sdf.format(end));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		
		long time1 = cal.getTimeInMillis();
		cal.setTime(end);
		
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		
		return Integer.parseInt(String.valueOf(between_days));           
    }

    public static Date strToDate(String strDate) {
 	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 	   ParsePosition pos = new ParsePosition(0);
 	   Date strtodate = formatter.parse(strDate, pos);
 	   return strtodate;
 	}
    public static Date strToDateTime(String strDate) {
  	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	   ParsePosition pos = new ParsePosition(0);
  	   Date strtodate = formatter.parse(strDate, pos);
  	   return strtodate;
  	}
    
    /**
	 * According to the user's birthday to calculate the age
	 */
	public static int getAgeByBirthday(Date birthday) {
		int age = 0;
		try {
			Calendar cal = Calendar.getInstance();

			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			cal.setTime(birthday);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					// monthNow==monthBirth 
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					// monthNow>monthBirth 
					age--;
				}
			}
		} catch (Exception e) {
		}
		return age;
	}
	
	/**
	 * ObtainNDays before and after the day
	 * @return
	 */
	public static String getDayTime(int n) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, n);
		return formatter.format(c.getTime());
	}
    
}