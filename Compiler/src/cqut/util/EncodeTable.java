package cqut.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Jomb语言单词编码表
 * 
 * @author Cheng
 * 
 */
public class EncodeTable {
	private static Map<Integer, String> encodeTable;

	private EncodeTable() {
	}

	static {
		if (encodeTable == null) {
			encodeTable = SystemProperty.readProperties();
		}
	}

	public static Integer search(String s) {
		for (int i = 0; i < 52; i++) {
			if (s.equals(encodeTable.get(i + ""))) {
				return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(EncodeTable.search("字符常数"));
	}
}
