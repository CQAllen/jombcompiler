package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;
import cqut.util.ReadFile;
import cqut.util.Token;

/*
 * 运算符
 * Design by JiaoJian
 */
public class OperaterAnalysis implements Recog{
    int state=1;
    NoteOrDivsionAnalysis noteOrDivsionAnalysis = new NoteOrDivsionAnalysis();
    
	@Override
	public void error() {
		
		
	}

	@Override
	public boolean recog(Character ch) {
		while (true){
			isOperater(ch);
			switch(state){
			case 1:ReadFile.sort();
			case 0:
				error();
			    break;
		       }
			}
		
	}
	
	public  boolean isOperater(Character c){
		switch(c){
		case '+':Token.getOperaterList().add(c.toString());return true;
		case '-':Token.getOperaterList().add(c.toString());return true;
		case '*':Token.getOperaterList().add(c.toString());return true;
		case '/':if(noteOrDivsionAnalysis.isDivsion(c)){return true;}else return false;//保存的过程在isDivision方法中处理了。
		case '<':if(ReadFile.getNextChar().equals('='))
		           {
			         Token.getOperaterList().add("<=");
			        }else
			        	{Token.getOperaterList().add(c.toString());
			        	  ReadFile.row--;
			        	}
		           return true;
		case '>':if(ReadFile.getNextChar().equals('='))
                 {
	               Token.getOperaterList().add(">=");
	             }else
	        	    {  Token.getOperaterList().add(c.toString());
	        	         ReadFile.row--;
	        	    }
                     return true;
          
          
		case '=':Token.getOperaterList().add(c.toString());return true;
		}
		
		
		return false;
	} 

}
