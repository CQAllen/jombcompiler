package cqut.syntaxAnalyzer.util;

public class StringUtil {

	/**
	 * 判断参数中字符串大写字母的起始位置，若没有大写字母则返回-1
	 * 
	 * @param str
	 * @return
	 */
	public static int indexOf(String str) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] <= 'Z' && chars[i] >= 'A') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(indexOf("ddD23"));
		System.out.println(indexOf("dd23"));
	}
}
