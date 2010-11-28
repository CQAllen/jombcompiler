package cqut.lexicalAnalyzer.impl;

import cqut.lexicalAnalyzer.Recog;
import cqut.util.EncodeTable;
import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Error;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

public class DelimiterAnalysis implements Recog {
	private static DelimiterAnalysis d;
	static {
		d = new DelimiterAnalysis();
	}

	public static DelimiterAnalysis getInstance() {
		return d;
	}

	int state = 1;

	@Override
	public void recog(Character ch) {
		isDelimiter(ch);
		switch (state) {
		case 1:
			if (Source.getInstance().isLastCharacter()) {
				return;
			}
			Source.getInstance().getNextCharacter();

			Source.getInstance().sort();
			break;
		case 0:
			if (Source.getInstance().isLastCharacter()) {

				return;
			}
			Source.getInstance().sort();
			break;
		}

	}

	public boolean isDelimiter(Character c) {

		Character ch = c;
		TokenMeta tokenMeata;
		switch (ch) {

		case ';':

			Token.getInstance().insert(";", EncodeTable.search(";"));

			return true;

		case ',':

			Token.getInstance().insert(",", EncodeTable.search(","));
			return true;
		case '.':

			Token.getInstance().insert(".", EncodeTable.search("."));
			return true;
		case ':':

			Token.getInstance().insert(":", EncodeTable.search(":"));
			return true;
		case '"':

			Token.getInstance().insert("\"", EncodeTable.search("\""));
			// MyList.getList().add("\"");//用于判断到一句话结束的时候看“的个数
			StringBuffer sb = new StringBuffer();
			Character c2;
			while (true) {
				if (Source.getInstance().isLastCharacter()) {
					state = 0;
					ErrorFacade.getInstance().addError(ch.toString(),
							Error.ERROR_TYPE_LEXICAL);
					break;
				}
				c2 = Source.getInstance().getNextCharacter();
				if (c2 == '\n') {
					state = 0;
					ErrorFacade.getInstance().addError(ch.toString(),
							Error.ERROR_TYPE_LEXICAL);
					break;
				}

				if (c2 == '\"') {

					// 保存String
					Token.getInstance().insert(sb.toString(), 38);

					SymbolMeta sm = new SymbolMeta();
					sm.setPointer(38);
					sm.setKind(Symbol.KIND_NUM);
					sm.setMeta(sb.toString());
					Symbol.getInstance().insert(sm);
					sb = null;

					Token.getInstance().insert("\"", EncodeTable.search("\""));
					// MyList.getList().add("\"");
					break;
				}
				sb.append(c2);
			}

			return true;
		case '(':
			Token.getInstance().insert("(", EncodeTable.search("("));

			return true;

		case ')':

			Token.getInstance().insert(")", EncodeTable.search(")"));
			break;
		case '[':

			Token.getInstance().insert("[", EncodeTable.search("["));
			return true;

		case ']':

			Token.getInstance().insert("]", EncodeTable.search("]"));

			break;
		case '{':

			Token.getInstance().insert("{", EncodeTable.search("{"));
			return true;

		case '}':

			Token.getInstance().insert("}", EncodeTable.search("}"));
			break;

		case '\'':
			StringBuffer sb3 = new StringBuffer();

			Token.getInstance().insert("\'", EncodeTable.search("\'"));
			Character c3;
			while (true) {
				if (Source.getInstance().isLastCharacter()) {
					state = 0;
					ErrorFacade.getInstance().addError(ch.toString(),
							Error.ERROR_TYPE_LEXICAL);
					break;
				}
				c3 = Source.getInstance().getNextCharacter();
				if (c3 == '\n') {
					state = 0;
					ErrorFacade.getInstance().addError(ch.toString(),
							Error.ERROR_TYPE_LEXICAL);
					break;
				}

				if (c3 == '\'') {

					if (sb3.length() > 1) {// 一个单引号里面有几个字符报错
						state = 0;
						ErrorFacade.getInstance().addError(ch.toString(),
								Error.ERROR_TYPE_LEXICAL);
					} else {
						// 保存char
						Token.getInstance().insert(sb3.toString(), 38);
						SymbolMeta sm2 = new SymbolMeta();
						sm2.setPointer(38);
						sm2.setKind(Symbol.KIND_NUM);
						sm2.setMeta(sb3.toString());
						Symbol.getInstance().insert(sm2);
						sb3 = null;

					}

					Token.getInstance().insert("\'", EncodeTable.search("\'"));
					// MyList.getList().add("\'");
					break;
				}
				sb3.append(c3);
			}
			// if(sb3.length()>1){//一个单引号里面有几个字符报错
			// state=0;
			//   	    	 
			// }else{
			// //保存char
			// System.out.println("char="+sb3.toString());
			// TokenMeta t=new TokenMeta();
			// t.setMeta(sb3.toString());
			// t.setLine(Source.getInstance().getRow());
			// t.setPointer(38);
			// Token.getTokenTable().add(t);
			//   	    	  
			// SymbolMeta sm2=new SymbolMeta();
			// sm2.setPointer(38);
			// sm2.setKind(Symbol.KIND_NUM);
			// sm2.setMeta(sb3.toString());
			// Symbol.getInstance().insert(sm2);
			// sb3=null;
			//   	    	  
			// }
			return true;
		}

		return false;
	}

	@Override
	public void error(String message) {
		System.out.println(message + "出错");
		// System.out.println("第"+Source.getInstance().getRow()+"行"+Source.getInstance().getColspan()+"列出错");

	}

}
//
// class MyList {//用来记录一句话完了后 ，看引号的个数是基数还是偶数。
// private static List<Object> list ;
//	
// static {
// list=new ArrayList<Object> ();
// }
// public static List<Object> getList() {
// return list;
// }
//
// }
//
// class MyList2 {
// private static List<Object> list ;
//	
// static {
// list=new ArrayList<Object> ();
// }
// public static List<Object> getList() {
// return list;
// }
//
// }
//
//
// class MyStack {
// private static Stack<Character> stack;
//	
// static {
// stack=new Stack<Character>();
// }
//	
// public static Stack<Character> getStack() {
// return stack;
// }
//
// }
//
//
// class MyStack2 {
// private static Stack<Character> stack;
//	
// static {
// stack=new Stack<Character>();
// }
//	
// public static Stack<Character> getStack() {
// return stack;
// }
//
// }
//
// class MyStack3 {
// private static Stack<Character> stack;
//	
// static {
// stack=new Stack<Character>();
// }
//	
// public static Stack<Character> getStack() {
// return stack;
// }

// }

