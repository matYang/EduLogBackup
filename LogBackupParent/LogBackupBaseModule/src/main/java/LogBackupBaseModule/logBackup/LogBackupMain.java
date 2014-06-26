package LogBackupBaseModule.logBackup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import LogBackupBaseModule.common.DateUtility;
import LogBackupBaseModule.common.DebugLog;
import LogBackupBaseModule.configurations.ServerConfig;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.ObjectMetadata;

public class LogBackupMain {
	private static final String myAccessKeyID = ServerConfig.AliyunAccessKeyID;
	private static final String mySecretKey = ServerConfig.AliyunAccessKeySecrete;
	static Logger logger = Logger.getLogger(LogBackupMain.class);

	public static String backUpLogFile(File file) {

		OSSClient client = new OSSClient(myAccessKeyID, mySecretKey);
		String imgAddress = "";

		try {
         	InputStream content = new FileInputStream(file);
            String tempFileKey;
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.length());
			if (file.getName().contains("behaviors.log")) {
				tempFileKey = getLogBackupKey(file.getName());
				client.putObject(ServerConfig.logBehaviorsBackupBucket,
						tempFileKey, content, meta);
				imgAddress = getOSSUrlPrefix(ServerConfig.logBehaviorsBackupBucket)
						+ tempFileKey;
			} else if (file.getName().contains("system.log")) {
				tempFileKey = getLogBackupKey(file.getName());
				client.putObject(ServerConfig.logSystemBackupPath, tempFileKey,
						content, meta);
				imgAddress = getOSSUrlPrefix(ServerConfig.logSystemBackupPath)
						+ tempFileKey;
			}
		} catch (ClientException | OSSException e) {
			e.printStackTrace();
			DebugLog.d(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			DebugLog.d(e);
		} finally {
			IdleConnectionReaper.shutdown();
			if (file != null) {
				file.delete();
			}
		}
		System.out.println("File Address is: " + imgAddress);
		return imgAddress;

	}

	private static String getLogBackupKey(String fileTime) {
		return fileTime + "-" + DateUtility.getCurTime();
	}

	private static String getOSSUrlPrefix(final String Bucket) {
		if (ServerConfig.configurationMap.get(ServerConfig.MAP_ENV_KEY).equals(
				ServerConfig.MAP_ENV_LOCAL)) {
			return "http://" + Bucket + ".oss-cn-hangzhou.aliyuncs.com/";
		} else if (ServerConfig.configurationMap.get(ServerConfig.MAP_ENV_KEY)
				.equals(ServerConfig.MAP_ENV_TEST)) {
			return "http://" + Bucket + ".oss-cn-hangzhou.aliyuncs.com/";
		} else if (ServerConfig.configurationMap.get(ServerConfig.MAP_ENV_KEY)
				.equals(ServerConfig.MAP_ENV_PROD)) {
			return "http://" + Bucket + ".oss-internal.aliyuncs.com/";
		} else {
			throw new RuntimeException();
		}
	}
}
