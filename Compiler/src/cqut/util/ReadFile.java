package cqut.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取源文件
 * 
 * @author Cheng
 * 
 */
public class ReadFile {
	public static List<String> read(String path) {
		List<String> source = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String strLine = "";
			StringBuffer sb = new StringBuffer();
			while ((strLine = br.readLine()) != null) {
				sb.append(strLine).append("\n");
				source.add(strLine);
			}
			sb = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return source;
	}
}
