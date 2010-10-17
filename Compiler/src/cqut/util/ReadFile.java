package cqut.util;

import cqut.lexicalAnalysis.Recog;
import cqut.lexicalAnalysis.impl.DelimiterAnalysis;

/**
 * 读取源文件
 * 
 * @author Cheng
 * 
 */
public class ReadFile {

	public static int row = 0;

	public static int col = 0;

	public static String getNextLine() {
		++row;
		return "";
	}

	public static Character getNextChar() {
		++col;
		return 'a';
	}

	public static Character getLastChar() {
		--col;
		return 'f';
	}

	public static String getLastLine() {
		--row;
		col = 0;
		return "dasfasd";
	}

	public static void sort() {

		Character a = getNextChar();
		switch (a) {

		}

	}
}
