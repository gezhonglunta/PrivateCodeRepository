package com.leftright.tiny.website.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {
	private Properties property = null;
	private final Logger log = Logger.getLogger(PropertiesUtils.class);

	public PropertiesUtils() {
		InputStream stream = null;
		try {
			stream = PropertiesUtils.class.getClassLoader().getResourceAsStream(getPropertyFiles());
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

	public String getPropertyFiles() {
		return "application.properties";
	}

	public String getProperty(String key) {
		return property.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return property.getProperty(key, defaultValue);
	}
}
