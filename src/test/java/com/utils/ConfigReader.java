package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
		private static Properties prop = new Properties();
		static {
			try {
				String env = System.getProperty("env","staging");
				String file = "src/test/resource/config."+env+ ".properties";
				FileInputStream fis = new FileInputStream(file);
				prop.load(fis);
			}catch (Exception e) {
				throw new RuntimeException("Failed to load config file: "+e.getMessage(), e);
			}
		}
		
		public static String get(String key) {
			return prop.getProperty(key);
		}
}
