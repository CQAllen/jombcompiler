package cqut.util.entity;

import java.util.List;

import cqut.lexicalAnalysis.Recog;
import cqut.lexicalAnalysis.impl.DelimiterAnalysis;
import cqut.lexicalAnalysis.impl.DigitAnalysis;
import cqut.lexicalAnalysis.impl.IdentifierAnalysis;
import cqut.lexicalAnalysis.impl.NoteOrDivsionAnalysis;
import cqut.util.EncodeTable;
import cqut.util.ReadFile;
import cqut.util.Token;

/**
 * 读取源代码生成的对象
 * 
 * @author Cheng
 * 
 */
public class Source {

	private static Source sourceCode;

	private Source() {
	}

	public static Source getInstance() {
		if (sourceCode == null) {
			init();
		}
		return sourceCode;
	}

	private static List<String> sources;// 源代码

	private static int row;
	private static int colspan;

	private static int MAX_LINE;

	private static void init() {
		sourceCode = new Source();
		sources = ReadFile.read("src/sample.jom");
		MAX_LINE = sources.size();
		row = 0;
		colspan = 0;
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
		return sources.get(row++);
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
		return sources.get(row--);
	}

	/**
	 * 得到当前行的下一个字符
	 * 
	 * @return
	 */
	public Character getNextCharacter() {
		char[] chars = sources.get(row).toCharArray();
		if (colspan == chars.length - 1) {// 当前字符已经是当前行最后一个
			chars = sources.get(++row).toCharArray();
			colspan = 0;
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

	public void sort() {
		Recog recog = null;
		Character ch = getCurrentCharacter();
		String curr = ch.toString();
		if (curr.matches("\\d{1}")) {// 如果是数字
			recog = new DigitAnalysis();
		} else if (curr.matches("[a-zA-Z_]")) {// 如果是字目或者下划线
			recog = new IdentifierAnalysis();
		} else if (curr.matches("["
				+ EncodeTable.findCharactersByType(Token.ENCODETYPE_DELIMITER)
				+ "]")) {// 如果是界符
			recog = new DelimiterAnalysis();
		} else if (ch == '/') {// 如果是反斜杠
			recog = new NoteOrDivsionAnalysis();
		}
		recog.recog(ch);
	}
}
