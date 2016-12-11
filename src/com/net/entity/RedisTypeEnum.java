package com.net.entity;

/**
 * redisCache primary key
 * @author lenovo
 *
 */
public enum RedisTypeEnum {
	
	DEFAULT{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "";
		}
	},
	DIC{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "dic";
		}
	},
	SYSAUTH{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "sysAuth";
		}
	},
	AREAMAP{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "areaMap";
		}
	},
	AREAPARENT{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "AREAPARENT";
		}
	},
	SYS{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "sys";
		}
	},
		
	DEPARTMENT{

		@Override
		public String toId() {
			// TODO Auto-generated method stub
			return "department";
		}
	}
	;
	
	
	public abstract String toId();
	
	public static RedisTypeEnum toEnum(String timeCell){
		RedisTypeEnum[] tcs = RedisTypeEnum.values();
		for(int i=0;i<tcs.length;i++){
			RedisTypeEnum tc = tcs[i];
			if(tc.toId().equals(timeCell)){
				return tc;
			}
		}
		return RedisTypeEnum.DEFAULT;
	}

}