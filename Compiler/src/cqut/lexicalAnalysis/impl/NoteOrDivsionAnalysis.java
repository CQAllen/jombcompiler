package cqut.lexicalAnalysis.impl;


import cqut.lexicalAnalysis.Recog;
import cqut.util.EncodeTable;
import cqut.util.Token;
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Source;
import cqut.util.entity.TokenMeta;
 /*
  * Designed by JiaoJian
  * 判断是注释还是除号
  */

public class NoteOrDivsionAnalysis implements Recog {
	

	
	private static NoteOrDivsionAnalysis n;
	int currentRow;
    int state=1;
    static {
    	n=new NoteOrDivsionAnalysis();
    }
    public static NoteOrDivsionAnalysis getInstance(){
    	
    	return n;
    }

	@Override
	public void recog(Character ch) {
			isDivsion(ch);
			switch(state){
			case 1:
				Source.getInstance().sort();break;
			case 0:
				
				
				if(Source.getInstance().isLastCharacter()){
					return ;
				}
				Source.getInstance().getNextCharacter();
			    Source.getInstance().sort();break;
		       }
	
	}
	public boolean isDivsion(Character ch){
		System.out.println("处理/的方法");
		TokenMeta tokenMeata;
		if(Source.getInstance().isLastCharacter()){
			return true;
		}
		Character c=Source.getInstance().getNextCharacter();
		
		if(c.equals('/')){//判断单行注释
			
			 tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("//");
			tokenMeata.setPointer(EncodeTable.search("//"));
			Token.getTokenTable().add(tokenMeata);
            return sigenlineNote();
            
       }
		else if(c.equals('*')){//判断多行注释
			tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("/*");
			tokenMeata.setPointer(EncodeTable.search("/*"));
			Token.getTokenTable().add(tokenMeata);

			if(doubleNote()){
			
			 tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("*/");
			tokenMeata.setPointer(EncodeTable.search("*/"));
			Token.getTokenTable().add(tokenMeata);
			return true;
			  }else{
				  ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, "多行注释未完");
			  }
			return false;
			}
		else {//是除号
			Character c2=Source.getInstance().getNextCharacter();
		    if(c2=='-'||c2=='+'){
		    	
		    	ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, "运算符"+ c.toString());
		    	return false;
		    }
			tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("/");
			tokenMeata.setPointer(EncodeTable.search("/"));
			Token.getTokenTable().add(tokenMeata);
		    return true;
			}
		
	}
	
	

	private boolean sigenlineNote(){//����ע��
		while(true){
			if(Source.getInstance().isLastCharacter()){
				   return true;
			   }
			Character c=Source.getInstance().getNextCharacter();
			if(c.equals('\n')){ 
				break;
			 }
			}
			
			return true;
			
	}
	
	
	
	private boolean doubleNote(){ 
		 
		while(true){
			if(Source.getInstance().isLastCharacter()){
				   return false;
			   }
			Character c= Source.getInstance().getNextCharacter();
			Character c2=null;

				
			if(c=='*'){
				if(Source.getInstance().isLastCharacter()){
					   return false;
				   }
				c2=Source.getInstance().getNextCharacter();
				if(c2=='/'){
					if(Source.getInstance().isLastCharacter()){
						   return true;
					   }
					Source.getInstance().getNextCharacter();
					return true;//放回过后，需要从下一个字符开始读取
				}
				
			}

		}
		
	
	}



	@Override
	public void error(String message) {
		System.out.println(message+"多行注释出错");
	}

		
	}


