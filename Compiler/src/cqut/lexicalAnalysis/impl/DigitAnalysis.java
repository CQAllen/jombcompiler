package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;
import cqut.util.entity.Source;

public class DigitAnalysis implements Recog {

	@Override
	public void error(String message) {
		System.out.println(message);
	}

	@Override
	public void recog(Character ch) {
		StringBuffer sb = new StringBuffer();
		int state = 0;
		while (state != 7) {
			char next = Source.getInstance().getNextCharacter();
			switch (state) {
			case 0:
				if (isDigit(ch)) {state = 1;} 
				else {state = 7;error("无法识别字符" + ch);}
				break;
			case 1:
				if (isDigit(next)) {state = 1;} 
				else if (ch == 'e' || ch == 'E') {state = 4;} 
				else if (ch == '.') {state = 2;} 
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 2:
				if (isDigit(next)) {state = 3;}
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 3:
				if (isDigit(next)) {state = 3;} 
				else if (next == 'e' || next == 'E') {state = 4;}
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 4:
				if (next == '-' || next == '+') {state = 5;} 
				else if (isDigit(next)) {state = 6;} 
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 5:
				if (isDigit(next)) {state = 6;} 
				else {state = 7;error("无法识别字符" + next);}
				break;
			case 6:
				if (isDigit(next)) {state = 6;}
				else {state = 7;error("无法识别字符" + next);}
				break;
			}
			if (state != 7) {
				sb.append(ch);
			}
		}
	}

	public boolean isDigit(Character ch) {
		return ch.toString().matches("[0-9]{1}");
	}
}
