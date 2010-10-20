package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;
import cqut.util.EncodeTable;
import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

/**
 * 
 * @author Allen 字符串分析
 */

public class StringAnalysis implements Recog {

	Recog r = new DigitAnalysis();
	static StringAnalysis SA;
	static StringBuffer temp = new StringBuffer();// 字符缓存
	static int state = 1;// 1表示是关键字 2表示是标识符 0表示程序出错

	private StringAnalysis() {
	}

	public static StringAnalysis getStringAnalysis() {
		if (SA == null) {
			SA = new StringAnalysis();
		}
		return SA;
	}

	@Override
	public void recog(Character ch) {
		if (ch == '_')
			state = 2;
		switch (state) {
		case 0: {
			error("error:第" + (Source.getInstance().getRow() + 1) + "行 第"
					+ (Source.getInstance().getColspan() + 1) + "列");
			break;
		}
		case 1: {
			isKeyword(ch);
			break;
		}
		case 2: {
			isIdentifier(ch);
			break;
		}
		}
		Source.getInstance().sort();
	}

	public void error(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

	private void isIdentifier(Character ch) {// 包含下划线,不可能是关键字
		// TODO Auto-generated method stub
		System.out.println(ch);
		// if (ch != ' ' && ch != ';' && ch != '+' && ch != '-' && ch != '*'
		// && ch != '/' && ch != '=' && ch != '(' && ch != ')'
		// && ch != '[' && ch != ']') {
		if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_'
				|| ch >= '0' && ch <= '9') {
			temp.append(ch);
			if (Source.getInstance().isLastCharacter()) {
				addIdentifier(temp.toString());
				insertSymbol(temp.toString());
				state = 1;
				temp = new StringBuffer();// 清空字符缓存
				return;
			}
			isIdentifier(Source.getInstance().getNextCharacter());
		}
		// else {
		// error("error:第" + Source.getInstance().getRow() + "行 第"
		// + Source.getInstance().getColspan() + "列" + " :" + ch);
		// }

		// }
		else {
			addIdentifier(temp.toString());
			insertSymbol(temp.toString());
			state = 1;// 还原状态，方便下次调用
			temp = new StringBuffer();// 清空字符缓存
		}
	}

	private void isKeyword(Character ch) {// 纯字母组成，有可能是关键字
		// TODO Auto-generated method stub
		System.out.println(ch);
//		if (ch != ' ' && ch != ';' && ch != '+' && ch != '-' && ch != '*'
//				&& ch != '/' && ch != '=' && ch != '(' && ch != ')'
//				&& ch != '[' && ch != ']') {
			if (ch != '_' && (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
				temp.append(ch);
				if (Source.getInstance().isLastCharacter()) {
					if (EncodeTable.search(temp.toString()) != 0) {
						addKeyword(temp.toString());
						state = 1;
						temp = new StringBuffer();// 清空字符缓存
					} else {
						addIdentifier(temp.toString());
						insertSymbol(temp.toString());
						state = 1;
						temp = new StringBuffer();// 清空字符缓存
					}
					return;
				} else
					isKeyword(Source.getInstance().getNextCharacter());
			} else if (ch == '_' || ch >= '0' && ch <= '9') {
				state = 2;
				isIdentifier(Source.getInstance().getNextCharacter());
			} 
//			else {
//				temp = new StringBuffer();
//				error("error:第" + (Source.getInstance().getRow() + 1) + "行 第"
//						+ (Source.getInstance().getColspan() + 1) + "列" + " :"
//						+ ch);
//			}

//		} 
			else {
			if (EncodeTable.search(temp.toString()) != 0) {
				addKeyword(temp.toString());
				state = 1;
				temp = new StringBuffer();// 清空字符缓存
			} else {
				addIdentifier(temp.toString());
				insertSymbol(temp.toString());
				state = 1;
				temp = new StringBuffer();// 清空字符缓存
			}
		}
	}

	private void insertSymbol(String Str_temp) {
		// TODO Auto-generated method stub
		// Symbol.getInstance().getSymbol()
		SymbolMeta Cur = new SymbolMeta();
		Cur.setMeta(Str_temp);
		Cur.setPointer(35);
		Cur.setKind(Symbol.KIND_VAR);
		Symbol.getInstance().getSymbol().add(Cur);

	}

	private void addIdentifier(String Str_temp) {
		// TODO Auto-generated method stub
		TokenMeta Cur = new TokenMeta();
		Cur.setLine(Source.getInstance().getRow());
		Cur.setMeta(Str_temp);
		Cur.setPointer(35);
		Token.getTokenTable().add(Cur);
	}

	private void addKeyword(String Str_temp) {
		// TODO Auto-generated method stub
		int Cur_pointer = EncodeTable.search(Str_temp);
		TokenMeta Cur = new TokenMeta();
		Cur.setLine(Source.getInstance().getRow());
		Cur.setMeta(Str_temp);
		Cur.setPointer(Cur_pointer);
		Token.getTokenTable().add(Cur);
	}

}
