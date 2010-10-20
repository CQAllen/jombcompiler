package cqut.util.entity;

import java.util.List;

import cqut.lexicalAnalysis.Recog;
import cqut.lexicalAnalysis.impl.DelimiterAnalysis;
import cqut.lexicalAnalysis.impl.DigitAnalysis;
import cqut.lexicalAnalysis.impl.NoteOrDivsionAnalysis;
import cqut.lexicalAnalysis.impl.OperaterAnalysis;
import cqut.lexicalAnalysis.impl.StringAnalysis;
import cqut.util.EncodeTable;
import cqut.util.ReadFile;
import cqut.util.SystemProperty;
import cqut.util.Token;

/**
 * 读取源代码生成的对象
 * 
 * @author Cheng
 * 
 */
public class Source {

	private static Source sourceCode;
	private static String filepath;

	private Source() {
	}

	public static Source getInstance() {
		if (sourceCode == null) {
			init();
		}
		return sourceCode;
	}

	public static Source getInstance(String filepath) {
		if (sourceCode == null) {
			init(filepath);
		}
		return sourceCode;
	}

	private static List<String> sources;// 源代码

	private static int row;
	private static int colspan;
	private static int max_colspan;

	private static int MAX_LINE;// 源代码文件的行数

	private static void init(String filepath) {
		sourceCode = new Source();
		SystemProperty.readProperties();// 读取语言编码表
		sources = ReadFile.read(filepath);// 读源文件
		MAX_LINE = sources.size();
		max_colspan = sources.get(0).length();
		row = 0;
		colspan = 0;
	}

	private static void init() {
		init(ReadFile.sourcePath);
	}

	public List<String> getSource() {
		return sources;
	}

	/**
	 * 得到下一行源代码，若已经是最后一行，则返回空
	 * 
	 * @return
	 */
	public String getNextLine() {
		if (row == MAX_LINE) {// 当前已经是最后一行，无法得到下一行
			return null;
		}
		colspan = 0;// 转到开头
		String s = sources.get(row++);
		max_colspan = s.length();
		return s;
	}

	/**
	 * 得到上一行源代码，若已经是第一行，则返回空
	 * 
	 * @return
	 */
	public String getLastLine() {
		if (row == 0) {// 当前已经是第一行，无法取得上一行
			return null;
		}
		colspan = 0;// 转到开头
		String s = sources.get(row--);
		max_colspan = s.length();
		return s;
	}

	/**
	 * 得到当前行的下一个字符
	 * 
	 * @return
	 */
	public Character getNextCharacter() {
		char[] chars = sources.get(row).toCharArray();
		if (row == MAX_LINE - 1 && colspan == max_colspan - 1) {
			return null;
		}
		if (colspan == chars.length - 1) {// 当前字符已经是当前行最后一个
			chars = sources.get(++row).toCharArray();
			colspan = 0;
			max_colspan = chars.length;
			return chars[colspan];
		}
		return chars[++colspan];
	}

	/**
	 * 得到当前行的上一个字符
	 * 
	 * @return
	 */
	public Character getLastCharacter() {
		char[] chars = sources.get(row).toCharArray();
		if (colspan == 0) {// 当前字符已经是当前行第一个
			chars = sources.get(--row).toCharArray();
			colspan = chars.length - 1;
			max_colspan = chars.length;
			return chars[colspan];
		}
		return chars[--colspan];
	}

	public int getRow() {
		return row;
	}

	public int getColspan() {
		return colspan;
	}

	/**
	 * 获取当前指针指向的字符
	 * 
	 * @return
	 */
	public Character getCurrentCharacter() {
		return sources.get(row).toCharArray()[colspan];
	}

	/**
	 * 获取当前行字符串
	 * 
	 * @return
	 */
	public String getCurrentLine() {
		return sources.get(row);
	}

	public boolean isLastCharacter() {
		return row == MAX_LINE - 1 && colspan == max_colspan - 1;
	}

	public boolean isLastLine() {
		return row == MAX_LINE - 1;
	}

	public void sort() {
		if (isLastCharacter()) {// 源文件识别完毕
			return;
		}
		Recog recog = null;
		Character ch = getCurrentCharacter();
		String curr = ch.toString();
		System.out.println("读到了一个" + curr + "啊");
		boolean flag = true;
		while (flag) {
			if (ch == ' '||ch =='	') {
				ch = getNextCharacter();
				curr = ch.toString();
			} else {
				flag = false;
			}
		}
		if (curr.matches("\\d{1}")) {// 如果是数字
			recog = new DigitAnalysis();
		} else if (curr.matches("[a-zA-Z_]")) {// 如果是字目或者下划线
			recog = StringAnalysis.getStringAnalysis();
		} else if (curr.matches("["
				+ EncodeTable.findCharactersByType(Token.ENCODETYPE_DELIMITER)
						.replaceAll("[\\[\\]\\;]", "").concat("\\]\\[\\;")
				+ "]")) {// 如果是界符
			recog = DelimiterAnalysis.getInstance();
		} else if (ch == '/') {// 如果是反斜杠
			recog = NoteOrDivsionAnalysis.getInstance();
		} else if (curr.matches("["
				+ EncodeTable.findCharactersByType(Token.ENCODETYPE_OPERATER)
				+ "]")) {
			recog = OperaterAnalysis.getInstance();
		}
		System.out.println("交给了" + recog + "啊");
		recog.recog(ch);
	}
}
