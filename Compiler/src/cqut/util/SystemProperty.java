package cqut.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemProperty {

	public static Map readProperties(String path) {
		Properties props = new Properties();
		Map properties = new HashMap();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(path));
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
}