package cqut.lexicalAnalyzer.impl;

import cqut.lexicalAnalyzer.Recog;
import cqut.util.EncodeTable;
import cqut.util.Token;
import cqut.util.entity.Error;
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Source;
import cqut.util.entity.TokenMeta;

/*
 * 运算符号
 * Design by JiaoJian
 */
public class OperaterAnalysis implements Recog {

	NoteOrDivsionAnalysis noteOrDivsionAnalysis = new NoteOrDivsionAnalysis();
	private static OperaterAnalysis o;

	static {
		o = new OperaterAnalysis();

	}

	public static OperaterAnalysis getInstance() {
		return o;
	}

	int state = 1;

	@Override
	public void recog(Character ch) {

		isOperater(ch);
		switch (state) {

		case 0: // 给当前字符已经读出的方法调用服务
			// error("运算符类"+ch.toString());
			Source.getInstance().sort();
			break;

		case 1: // 给当前字符已经读出的方法调用服务
			Source.getInstance().sort();
			break;

		case 2:
			if (Source.getInstance().isLastCharacter()) {
				return;
			}// 给当前字符已经被保存，需要读出下一个字符的方法使用
			Source.getInstance().getNextCharacter();
			Source.getInstance().sort();
			break;
		case 3:
			if (Source.getInstance().isLastCharacter()) {
				return;
			}
			Source.getInstance().getNextCharacter();

			// error("运算符类"+ch.toString());
			Source.getInstance().sort();
			break;

		}
	}

	public boolean isOperater(Character c) {

		TokenMeta tokenMeta;
		switch (c) {
		case '+':
			Character c1 = Source.getInstance().getNextCharacter();
			if (c1 == '*' || c1 == '/' || c1 == '-') {
				ErrorFacade.getInstance().addError("运算符" + c.toString(),
						Error.ERROR_TYPE_LEXICAL);
				return false;
			}

			Token.getInstance().insert("+", EncodeTable.search("+"));
			state = 2;
			return true;
		case '-':
			Character c2 = Source.getInstance().getNextCharacter();
			if (c2 == '*' || c2 == '/' || c2 == '+') {
				ErrorFacade.getInstance().addError("运算符" + c.toString(),
						Error.ERROR_TYPE_LEXICAL);
				return false;
			}

			Token.getInstance().insert("-", EncodeTable.search("-"));
			state = 2;
			return true;
		case '*':
			Character c3 = Source.getInstance().getNextCharacter();
			if (c3 == '-' || c3 == '/' || c3 == '+') {
				ErrorFacade.getInstance().addError("运算符" + c.toString(),
						Error.ERROR_TYPE_LEXICAL);
				return false;
			}

			Token.getInstance().insert("*", EncodeTable.search("*"));
			state = 2;
			return true;

		case '<':
			if (Source.getInstance().getNextCharacter().equals('=')) {

				Token.getInstance().insert("+", EncodeTable.search("+"));
				return true;
			} else {

				Token.getInstance().insert("<", EncodeTable.search("<"));
				// Source.getInstance().getLastCharacter();//用来回退
			}
			return true;
		case '>':
			if (Source.getInstance().isLastCharacter()) {

				Token.getInstance().insert(">", EncodeTable.search(">"));
				return true;
			}
			if (Source.getInstance().getNextCharacter().equals('=')) {

				Token.getInstance().insert(">=", EncodeTable.search(">="));
				return true;
			} else {

				Token.getInstance().insert(">", EncodeTable.search(">"));
			}
			return true;

		case '=':
			if (Source.getInstance().isLastCharacter()) {

				Token.getInstance().insert("=", EncodeTable.search("="));
				return true;
			}
			if (Source.getInstance().getNextCharacter().equals('=')) {

				Token.getInstance().insert("==", EncodeTable.search("=="));
				return true;
			} else {

				Token.getInstance().insert("=", EncodeTable.search("="));
				// Source.getInstance().getLastCharacter();//用来回退
			}
			return true;

		case '!': {

			if (Source.getInstance().isLastCharacter()) {

				return false;
			}
			if (Source.getInstance().getNextCharacter().equals('=')) {

				Token.getInstance().insert("!=", EncodeTable.search("!="));
				return true;
			} else {
				state = 0;
				ErrorFacade.getInstance().addError(c.toString(),
						Error.ERROR_TYPE_LEXICAL);
				return false;
			}
		}

		}

		return false;
	}

	@Override
	public void error(String message) {
		System.out.println(message + "出错");

	}

}
