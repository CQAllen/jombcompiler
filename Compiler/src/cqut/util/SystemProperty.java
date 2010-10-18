package cqut.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemProperty {

	private static String profilepath = "src/word.properties";
	private static Map properties;
	private static Properties props;

	public static Map readProperties() {
		if (props == null) {
			props = new Properties();
		}
		if (properties == null) {
			properties = new HashMap();
		}
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					profilepath));
			props.load(in);
			Enumeration<?> en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				properties.put(key, new String(props.getProperty(key).getBytes(
						"iso-8859-1"), "utf-8"));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Map getProperties() {
		return properties;
	}
}