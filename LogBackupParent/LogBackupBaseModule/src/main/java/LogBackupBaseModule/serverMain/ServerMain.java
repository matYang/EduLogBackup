package LogBackupBaseModule.serverMain;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import LogBackupBaseModule.common.DebugLog;
import LogBackupBaseModule.configurations.ServerConfig;
import LogBackupBaseModule.logBackup.LogBackupMain;

public class ServerMain {

	public static void main(String[] args) {
		DebugLog.initializeLogger();
		String path = ServerConfig.logfilePath;
		File d = new File(path);
		File list[] = d.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (!list[i].getName().equals("behaviors.log")
					&& !list[i].getName().equals("system.log")) {
				LogBackupMain.backUpLogFile(list[i]);
			}

		}
	}

}
