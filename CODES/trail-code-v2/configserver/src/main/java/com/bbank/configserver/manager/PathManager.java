package com.bbank.configserver.manager;

import java.io.IOException;
import java.util.Properties;

public class PathManager {
	
	private static final String PATHS_CONFIG_FILE = "paths.properties";
	
	public static String getConfigPath() throws IOException {
		Properties properties = new Properties();
		properties.load(PathManager.class.getClassLoader().getResourceAsStream(PATHS_CONFIG_FILE));
		return properties.getProperty("CONF_PATH");
	}
}
