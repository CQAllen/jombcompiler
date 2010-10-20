package cqut.lexicalAnalysis.impl;


import cqut.lexicalAnalysis.Recog;
import cqut.util.EncodeTable;
import cqut.util.Token;
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
			case 1://Source.getInstance().getNextCharacter();
				  
			       Source.getInstance().sort();
			case 0:
				error("注释或者除号类"+ch.toString());
				
				if(Source.getInstance().isLastCharacter()){
					return ;
				}
				Source.getInstance().getNextCharacter();
			    Source.getInstance().sort();
		       }
	
	}
	public boolean isDivsion(Character ch){
		System.out.println("处理/的方法");
		TokenMeta tokenMeata;
		if(Source.getInstance().isLastCharacter()){
			return true;
		}
		Character c=Source.getInstance().getNextCharacter();
		
		if(c.equals('/')){
			
			 tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("//");
			tokenMeata.setPointer(EncodeTable.search("//"));
			Token.getTokenTable().add(tokenMeata);
            return sigenlineNote();
            
       }
		else if(c.equals('*')){
			tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("/*");
			tokenMeata.setPointer(EncodeTable.search("/*"));
			Token.getTokenTable().add(tokenMeata);

			doubleNote();
			
			 tokenMeata=new TokenMeta();
			tokenMeata.setLine(Source.getInstance().getRow());
			tokenMeata.setMeta("*/");
			tokenMeata.setPointer(EncodeTable.search("*/"));
			Token.getTokenTable().add(tokenMeata);
			
				return false;
			}
		else {
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
	
	
	
	private boolean doubleNote(){ //����ע��
		 currentRow= Source.getInstance().getRow();
		while(true){
			if(Source.getInstance().isLastCharacter()){
				   return true;
			   }
			Character c= Source.getInstance().getNextCharacter();
			Character c2=null;
			if(c==null){
				state=0;
				break;
			}
				
			if(c=='*'){
				if(Source.getInstance().isLastCharacter()){
					   return true;
				   }
				c2=Source.getInstance().getNextCharacter();
				if(c2=='/'){
					return true;//放回过后，需要从下一个字符开始读取
				}
				
			}

		}
		return false;
		
	
	}
	

//	public int  isError() {
//		
//		  int currentrow=Source.getInstance().getRow()-1;// 回退一格
//		 StringBuffer bf=new StringBuffer();
//		 Character c;
//		 String str;
//		while(true){
//			if((c=(Character)Source.getInstance().getLastCharacter()).equals('=')){
//				
//			  break;
//		 }
//	}
//		
//		for(int i=Source.getInstance().getRow();i<currentrow-1;i++){
//			bf.append(Source.getInstance().getNextCharacter());
//		}
//		str=bf.toString();
//		boolean b1=identifier.getIsValueIdentifier().containsValue(str);
//		if(b1){
//			Source.getInstance().getNextCharacter();
//			while(true){
//			    if((c=(Character)Source.getInstance().getNextCharacter()).equals(';')){
//			    	break;
//			 }
//			  bf.append(c);
//		  }
//			str=bf.toString();
//			boolean b2=identifier.getIsValueIdentifier().containsValue(str);
//			if(b2){
//				return state=1; 
//			} else {
//				return  state=0;
//				
//			}
//		} else {
//			return state=0;
//			
//		}
//		
//	}


	@Override
	public void error(String message) {
		System.out.println(message+"多行注释出错");
//		System.out.println("第"+Source.getInstance().getRow()+"行"+Source.getInstance().getColspan()+"列出错");
	}

		
	}


