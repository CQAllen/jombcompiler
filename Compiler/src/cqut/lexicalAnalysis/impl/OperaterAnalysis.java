package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;
import cqut.util.EncodeTable;
import cqut.util.Token;
import cqut.util.entity.Source;
import cqut.util.entity.TokenMeta;

/*
 * 运算符号
 * Design by JiaoJian
 */
public class OperaterAnalysis implements Recog{
    int state=1;
    NoteOrDivsionAnalysis noteOrDivsionAnalysis = new NoteOrDivsionAnalysis();
    private static OperaterAnalysis o;
    
    
    static {
    	o=new OperaterAnalysis();
    	
    }
    public static OperaterAnalysis getInstance(){
    	return o;
    }
	@Override
	public void error() {
		
		
	}

	@Override
	public boolean recog(Character ch) {
//		while (true){
			isOperater(ch);
			switch(state){
			case 1:Source.getInstance().sort();
			case 0:
				error();
				Source.getInstance().sort();
//			    break;
		       }
//			}
		return true;
	}
	
	public  boolean isOperater(Character c){
		TokenMeta tokenMeta;
		switch(c){
		case '+':
			     tokenMeta=new TokenMeta();
		         tokenMeta.setLine(Source.getInstance().getRow());
		         tokenMeta.setMeta("+");
		         tokenMeta.setPointer(EncodeTable.search("+"));
		         Token.getTokenTable().add(tokenMeta);
			     return true;
		case '-':
			      tokenMeta=new TokenMeta();
                 tokenMeta.setLine(Source.getInstance().getRow());
                 tokenMeta.setMeta("-");
                 tokenMeta.setPointer(EncodeTable.search("-"));
                 Token.getTokenTable().add(tokenMeta);
	             return true;
		case '*':
			tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta("*");
            tokenMeta.setPointer(EncodeTable.search("*"));
            Token.getTokenTable().add(tokenMeta);
			return true;
		case '/':if(noteOrDivsionAnalysis.isDivsion(c)){return true;}else return false;
		case '<':if(Source.getInstance().getNextCharacter().equals('='))
		           {
			tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta("+");
            tokenMeta.setPointer(EncodeTable.search("+"));
            Token.getTokenTable().add(tokenMeta);
            return true;
			        }else
			        	{
			        	tokenMeta=new TokenMeta();
			            tokenMeta.setLine(Source.getInstance().getRow());
			            tokenMeta.setMeta("<");
			            tokenMeta.setPointer(EncodeTable.search("<"));
			            Token.getTokenTable().add(tokenMeta);
			        	Source.getInstance().getLastCharacter();//用来回退
			        	}
		           return true;
		case '>':
			
			if(Source.getInstance().getNextCharacter().equals('='))
		           {
			tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta(">=");
            tokenMeta.setPointer(EncodeTable.search(">="));
            Token.getTokenTable().add(tokenMeta);
            return true;
			        }else
			        	{
			        	tokenMeta=new TokenMeta();
			            tokenMeta.setLine(Source.getInstance().getRow());
			            tokenMeta.setMeta(">");
			            tokenMeta.setPointer(EncodeTable.search(">"));
			            Token.getTokenTable().add(tokenMeta);
			        	Source.getInstance().getLastCharacter();//用来回退
			        	}
		               return true;
          
		case '=':	
			
			if(Source.getInstance().getNextCharacter().equals('='))
	           {
		             tokenMeta=new TokenMeta();
                     tokenMeta.setLine(Source.getInstance().getRow());
                     tokenMeta.setMeta("==");
                     tokenMeta.setPointer(EncodeTable.search("=="));
                     Token.getTokenTable().add(tokenMeta);
                       return true;
		        }else
		        	{
		        	tokenMeta=new TokenMeta();
		            tokenMeta.setLine(Source.getInstance().getRow());
		            tokenMeta.setMeta("=");
		            tokenMeta.setPointer(EncodeTable.search("="));
		            Token.getTokenTable().add(tokenMeta);
		        	Source.getInstance().getLastCharacter();//用来回退
		        	}
	           return true;
	           
		
		case '!':
			if(Source.getInstance().getNextCharacter().equals('='))
	           {
		         tokenMeta=new TokenMeta();
                 tokenMeta.setLine(Source.getInstance().getRow());
                 tokenMeta.setMeta("!=");
                 tokenMeta.setPointer(EncodeTable.search("!="));
                 Token.getTokenTable().add(tokenMeta);
                 return true;
		        }else{
		        	error();
		        }
		}
		
		
		
			
		return false;
	} 

}
