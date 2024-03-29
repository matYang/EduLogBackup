package LogBackupBaseModule.configurations;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import LogBackupBaseModule.encryption.AccessControlCrypto;



public final class ServerConfig {
	
		private static final String ENV_VAR_KEY = "RA_MAINSERVER_ENV";
		private static final String ENV_TEST = "RA_TEST";
		private static final String ENV_PROD = "RA_PROD";
		
		
		public static final String MAP_ENV_KEY = "env";
		public static final String MAP_ENV_LOCAL = "local";
		public static final String MAP_ENV_TEST = "test";
		public static final String MAP_ENV_PROD = "prod";
		public static final String MAP_MODULE_KEY = "module";
		public static final String MAP_MODULE_USER = "user";
		public static final String MAP_MODULE_PARTNER = "partner";
		public static final String MAP_MODULE_ADMIN = "admin";
		
		public static final String normalSpliter = "-";
		 
		//use concurrent hashmap to guarantee thread safety
		public static final Map<String, String> configurationMap = new ConcurrentHashMap<String, String>();
		
		
		static{
			try{
				final String value = System.getenv(ENV_VAR_KEY);
				

				System.out.println("Server starting under " + value + " envrionment");
				if (value == null || (!value.equals(ENV_TEST) && !value.equals(ENV_PROD))){

					//local env
					configurationMap.put("env", "local");			
					configurationMap.put("jdbcUri", "localhost:3306/db19r3708gdzx5d1?allowMultiQueries=true&&characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes");
					configurationMap.put("redisUri", "localhost");
					configurationMap.put("memcachedUri", "localhost:11211");
					configurationMap.put("sqlPass", "badstudent");
					configurationMap.put("sqlUser", "root");
					configurationMap.put("sqlMaxConnection","150");
					configurationMap.put("memcachedUser", "");
					configurationMap.put("memcachedPass", "");
				} 
				else if (value.equals(ENV_TEST)){
					//test env
					configurationMap.put("env", "test");
					configurationMap.put("jdbcUri", "badstudent.cunzg2tyzsud.us-west-2.rds.amazonaws.com:3306/test?allowMultiQueries=true&&characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes");
					configurationMap.put("redisUri", "redisserver.ppomgu.0001.usw2.cache.amazonaws.com");
					configurationMap.put("memcachedUri", "localhost:11211");
					configurationMap.put("sqlPass", "badstudent");
					configurationMap.put("sqlUser", "test");
					configurationMap.put("sqlMaxConnection","50");
					configurationMap.put("memcachedUser", "");
					configurationMap.put("memcachedPass", "");
				}
				else{
					//prod env
					configurationMap.put("env", "prod");
					configurationMap.put("jdbcUri", "as4359fdgk.mysql.rds.aliyuncs.com:3306/db19r3708gdzx5d1?allowMultiQueries=true&&characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes");
					configurationMap.put("redisUri", "localhost");
					configurationMap.put("memcachedUri", "fdbc1391e96411e3.m.cnhzalicm10pub001.ocs.aliyuncs.com:11211");
					configurationMap.put("sqlUser", "082E4185DBC158A01DC2DE32E241AA4C7167BFF2EAD2493A1E95D63496F69CA7");
					configurationMap.put("sqlPass", "9211A28B9094893E29F00C1072940646");
					configurationMap.put("sqlMaxConnection","50");
					configurationMap.put("memcachedUser", "595859005EA745D60DE860E460DBD057B0D26BEAA841B0DCBD0D4CE4A8F032E0");
					configurationMap.put("memcachedPass", "7FBA36F169E8667523ECCD8EAF79BCFB");
					
				}
			} catch (final Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		public static void acDecode(String ac_key, String ac_ivy){
			
			try {
				if (configurationMap.get("env").equals("prod")){
					configurationMap.put("sqlPass", AccessControlCrypto.decrypt(configurationMap.get("sqlPass"), ac_key, ac_ivy));
					configurationMap.put("sqlUser", AccessControlCrypto.decrypt(configurationMap.get("sqlUser"), ac_key, ac_ivy));
					configurationMap.put("memcachedUser", AccessControlCrypto.decrypt(configurationMap.get("memcachedUser"), ac_key, ac_ivy));
					configurationMap.put("memcachedPass", AccessControlCrypto.decrypt(configurationMap.get("memcachedPass"), ac_key, ac_ivy));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		
		public static final String urlSeperator = "+";
		public static final String urlSeperatorRegx = "\\+";

		
		
		/*API level constants*/
		public static final int category_DM = 0;
		public static final String userApplicationPrefix = "/api";
		public static final String partnerApplicationPrefix = "/p-api";
		public static final String adminApplicationPrefix = "/a-api";
		public static final String versionPrefix = "/v1.0";
		
		/*Time constants*/
		public static final String timeZoneIdCH = "asia/shanghai";
		

		/* ALIYUN Bucket*/		
		public static final String AliyunAccessKeyID = "UBnwEnaFUdBewFF9";
		public static final String AliyunAccessKeySecrete = "L8hyNuKRXo5bfQ9HWURDq0bprDSDYO";
		public static final String AliyunTeacherImgBucket = "teacherimgbucket";
		public static final String AliyunClassroomImgBucket = "classroomimgbucket";		
		public static final String AliyunLogoBucket = "logobucket";
		public static final String AliyunProfileBucket = "badstudent-aliyun";		
		public static final String resourcePrefix = "src/main/resources/";
		
		public static final String logBehaviorsBackupBucket = "behaviorslogbucket";
		public static final String logSystemBackupPath = "systemlogbucket";
		
		public static final String logfilePath = "log4j";
}
