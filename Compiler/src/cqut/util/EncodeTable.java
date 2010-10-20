package cqut.util;

import java.util.Iterator;
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

	/**
	 * 根据编码类型获取所有
	 * 
	 * @param type
	 * @return
	 */
	public static String findCharactersByType(int type) {
		SystemProperty.readProperties();
		switch (type) {
		case Token.ENCODETYPE_KEYWORD:
			return find(1, 23);
		case Token.ENCODETYPE_CONSTANT:
			return find(36, 39);
		case Token.ENCODETYPE_DELIMITER:
			return find(40, 52).replaceAll("[\\* ]", "").replaceFirst("/", "");
		case Token.ENCODETYPE_ID:
			return find(35, 35);
		case Token.ENCODETYPE_OPERATER:
			return find(24, 34).replaceAll("[=<>/ \\*]", "") + "<=>*";
		default:
			return null;
		}
	}

	private static String find(int begin, int end) {
		Map map = SystemProperty.getProperties();
		StringBuffer sb = new StringBuffer();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			int index = Integer.valueOf((String) entry.getKey());
			if (index >= begin && index <= end) {
				sb.append(entry.getValue().toString() + " ");
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println('/'=='/');
	}
}
