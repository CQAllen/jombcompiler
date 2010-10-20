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
	public void recog(Character ch) {

			isOperater(ch);
			switch(state){
			case 1:                    //给当前字符已经读出的方法调用服务
				
			       Source.getInstance().sort();
			case 0:                     //给当前字符已经读出的方法调用服务
				error("运算符类"+ch.toString());
				Source.getInstance().sort();
			case 2:  
				if(Source.getInstance().isLastCharacter()){
					return ;
				}//给当前字符已经被保存，需要读出下一个字符的方法使用
				Source.getInstance().getNextCharacter();
		        Source.getInstance().sort();
		    case 3:
		    	if(Source.getInstance().isLastCharacter()){
					return ;
				}
		    	Source.getInstance().getNextCharacter();
			
			    error("运算符类"+ch.toString());
			    Source.getInstance().sort();

		       }	
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
		         state=2;
			     return true;
		case '-':
			      tokenMeta=new TokenMeta();
                 tokenMeta.setLine(Source.getInstance().getRow());
                 tokenMeta.setMeta("-");
                 tokenMeta.setPointer(EncodeTable.search("-"));
                 Token.getTokenTable().add(tokenMeta);
                 state=2;
	             return true;
		case '*':
			tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta("*");
            tokenMeta.setPointer(EncodeTable.search("*"));
            Token.getTokenTable().add(tokenMeta);
            state=2;
			return true;
//		case '/':if(noteOrDivsionAnalysis.isDivsion(c)){return true;}else return false;
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
//			        	Source.getInstance().getLastCharacter();//用来回退
			        	}
		           return true;
		case '>':
			if(Source.getInstance().isLastCharacter()){
				
			 	tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta(">");
            tokenMeta.setPointer(EncodeTable.search(">"));
            Token.getTokenTable().add(tokenMeta);
				return true;
			}
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
//			        	Source.getInstance().getLastCharacter();//用来回退
			        	}
		               return true;
          
		case '=':	
	if(Source.getInstance().isLastCharacter()){
				
			 	tokenMeta=new TokenMeta();
            tokenMeta.setLine(Source.getInstance().getRow());
            tokenMeta.setMeta("=");
            tokenMeta.setPointer(EncodeTable.search("="));
            Token.getTokenTable().add(tokenMeta);
				return true;
			}
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
//		        	Source.getInstance().getLastCharacter();//用来回退
		        	}
	           return true;
	           
		
		case '!':
	    
			if(Source.getInstance().isLastCharacter()){
			
				return false;
			}
			if(Source.getInstance().getNextCharacter().equals('='))
	           {
		         tokenMeta=new TokenMeta();
                 tokenMeta.setLine(Source.getInstance().getRow());
                 tokenMeta.setMeta("!=");
                 tokenMeta.setPointer(EncodeTable.search("!="));
                 Token.getTokenTable().add(tokenMeta);
                 return true;
		        }else{
		        	state=0;
		        	return false;
		        }
		}
		
		
		
			
		return false;
	}


	@Override
	public void error(String message) {
		System.out.println(message+"出错");
		
	} 

}
