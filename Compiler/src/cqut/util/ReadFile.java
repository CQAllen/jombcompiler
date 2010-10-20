package cqut.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

	public static String sourcePath = "src/sample.jom";

	public static String currentPath;

	public static List<String> read(String path) {
		currentPath = path;
		List<String> source = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String strLine = "";
			StringBuffer sb = new StringBuffer();
			while ((strLine = br.readLine()) != null) {
				sb.append(strLine).append('\n');
				source.add(strLine + '\n');
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

	public static void write(String source) {
		FileWriter fw;
		try {
			fw = new FileWriter(currentPath);
			fw.write(source);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String path,String source) {
		File f = new File(path);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(path);
			fw.write(source);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
