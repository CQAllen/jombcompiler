package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;
import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

public class DigitAnalysis implements Recog {

	@Override
	public void error(String message) {
		ErrorFacade.getInstance().addError(Source.getInstance().getRow(), message);
		System.out.println("shuzi:"+message);
	}

	@Override
	public void recog(Character ch) {
		StringBuffer sb = new StringBuffer();
		sb.append(ch);
		/**
		 * 标志位查看是整形还是实型,true为整形，false为实型
		 */
		boolean flag = true;
		int state = 1;
		while (state != 7) {
			if(Source.getInstance().isLastCharacter()){
				return;
			}
			char next = Source.getInstance().getNextCharacter();
			switch (state) {
			case 1:
				if (isDigit(next)) {state = 1;} 
				else if (next == 'e' || next == 'E') {state = 4;} 
				else if (next == '.') {state = 2;flag=false;} 
				else {state = 7;error("无法识别字符 " + next);}
				break;
			case 2:
				if (isDigit(next)) {state = 3;}
				else {state = 7;error("无法识别字符 " + next);}
				break;
			case 3:
				if (isDigit(next)) {state = 3;} 
				else if (next == 'e' || next == 'E') {state = 4;}
				else {state = 7;}
				break;
			case 4:
				if (next == '-' || next == '+') {state = 5;} 
				else if (isDigit(next)) {state = 6;} 
				else {state = 7;error("无法识别字符 '" + next+"'");}
				break;
			case 5:
				if (isDigit(next)) {state = 6;} 
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 6:
				if (isDigit(next)) {state = 6;}
				else {state = 7;}
				break;
			}
			if (state != 7) {
				sb.append(next);
			}
		}
		int pointer = 0;
		if(flag){
			pointer = 36;
		}else {
			pointer = 37;
		}
		insertToToken(sb.toString(),pointer);//插入Token表
		insertToSymbol(sb.toString(),pointer);//将得到的数值插入符号表、
		Source.getInstance().sort();
	}

	private void insertToToken(String string,int pointer) {
		TokenMeta tokenMeta = new TokenMeta();
		tokenMeta.setLine(Source.getInstance().getRow());
		tokenMeta.setMeta(string);
		tokenMeta.setPointer(pointer);
		Token.getTokenTable().add(tokenMeta);
	}

	private void insertToSymbol(String string,int pointer) {
		SymbolMeta symbolMeta = new SymbolMeta();
		symbolMeta.setMeta(string);
		symbolMeta.setPointer(pointer);
		symbolMeta.setKind(Symbol.KIND_NUM);
		if(pointer==36){
			symbolMeta.setType(Symbol.TYPE_INTEGER);
		}else if(pointer==37){
			symbolMeta.setType(Symbol.TYPE_FLOAT);
		}
		Symbol.getInstance().insert(symbolMeta);
	}

	public boolean isDigit(Character ch) {
		return ch.toString().matches("[0-9]{1}");
	}
}
