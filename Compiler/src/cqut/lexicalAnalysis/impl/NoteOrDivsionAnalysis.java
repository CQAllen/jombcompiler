package cqut.lexicalAnalysis.impl;


import cqut.lexicalAnalysis.Recog;
import cqut.util.ReadFile;
import cqut.util.Token;
 /*
  * Designed by JiaoJian
  * 处理注释 和 除号 
  */

public class NoteOrDivsionAnalysis implements Recog {
	
	IdentifierAnalysis identifier = new IdentifierAnalysis();
	

    int state=1;


	@Override
	public boolean recog(Character ch) {
		while (true){
			isDivsion(ch);
			switch(state){
			case 1:ReadFile.sort();
			case 0:
				error();
			    break;
		       }
			}
	}
	public boolean isDivsion(Character ch){
		if(ReadFile.getNextChar().equals('/')){
			Token.getDelimiterList().add("//");
			sigenlineNote();return false;
		}
		else if(ReadFile.getNextChar().equals('*')){
			Token.getDelimiterList().add("/*");
			Token.getDelimiterList().add("*/");
				doubleNote();return false;//继续读取下一个字符,b并且分类处理
			}
		else {//前面两种注释都不上是，表明就是除号，然后将除号放到Token表中相应的位置
			
			Token.getOperaterList().add("/");
			
			//下面是判断除号前后是否有错
			  state=0;
				return true;
			}
		
	}
	
	

	private boolean sigenlineNote(){//单行注释
		while(true){
			if(ReadFile.getNextChar().equals('\n')){ //一直读取到文件的换行符。表明该行注释结束
				break;
			 }
			}
			
			return false;//不是除号就换回false
			
	}
	
	
	
	private boolean doubleNote(){ //多行注释
		while(true){
			if(ReadFile.getNextChar().equals('*')&&ReadFile.getNextChar().equals('/')){//一直读取到连续两个字符是* / 表明多段注释结束
				break;  //多段注释结束，跳出循环
			}
		}
		
		return false;// 是注释，而不是除号，所以返回false
	}
	

	public void error() {
		//下面是判断除号前后是否有错
		  int currentrow=(ReadFile.row=ReadFile.row-1);// '/'处的下标
		 StringBuffer bf=new StringBuffer();
		 Character c;
		 String str;
		while(true){
			if((c=(Character)ReadFile.getLastChar()).equals('=')){//从/开始，如果上一个字符是=，就停止回退。也就回退到了=那里。
				
			  break;
		 }
	}
		
		for(int i=ReadFile.row;i<currentrow;i++){
			bf.append(ReadFile.getLastChar());
		}
		str=bf.toString();
		boolean b1=identifier.getIsValueIdentifier().containsValue(str);
		if(b1){
			while(true){
			    if((c=(Character)ReadFile.getNextChar()).equals(';')){
			    	break;
			 }
			  bf.append(c);
		  }
			str=bf.toString();
			boolean b2=identifier.getIsValueIdentifier().containsValue(str);
			if(b2){
				return ; 
			} else {
				System.out.println("第"+ReadFile.col+"行出错");
			}
		} else {
			System.out.println("第"+ReadFile.col+"行出错");
		}
		
	}

		
	}


