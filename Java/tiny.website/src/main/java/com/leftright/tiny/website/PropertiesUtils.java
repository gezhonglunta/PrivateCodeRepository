package com.leftright.tiny.website;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {
	private static Properties property = null;
	private static final Logger log = Logger.getLogger(PropertiesUtils.class);
	static {
		InputStream stream = null;
		try {
			stream = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
			property = new Properties();
			property.load(stream);
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	public static String getProperty(String key) {
		return property.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return property.getProperty(key, defaultValue);
	}
}
