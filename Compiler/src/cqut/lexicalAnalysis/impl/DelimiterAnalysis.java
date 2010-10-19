package cqut.lexicalAnalysis.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cqut.lexicalAnalysis.Recog;
import cqut.util.EncodeTable;
import cqut.util.Token;
/*
 * Design by JiaoJian
 * 界符
 */
import cqut.util.entity.Source;
import cqut.util.entity.TokenMeta;

public class DelimiterAnalysis implements Recog{
	private static DelimiterAnalysis d;
	static {
		d=new DelimiterAnalysis();
	}
	public static DelimiterAnalysis getInstance(){
		return d;
	}
	
   int state=1;
	@Override
	public void recog(Character ch) {

			isDelimiter(ch);
			switch(state){
			case 1:Source.getInstance().getNextCharacter();Source.getInstance().sort();
			case 0:
				error(ch.toString());
				Source.getInstance().getNextCharacter();
				Source.getInstance().sort();
		       } 

		}
		
		
	
	public boolean isDelimiter(Character c){
		
		
		Character ch=c;
		TokenMeta tokenMeata;
		switch(ch){
		
		case ';': 
			
			     tokenMeata=new TokenMeta();
		         tokenMeata.setLine(Source.getInstance().getRow());
		         tokenMeata.setMeta(";");
		         tokenMeata.setPointer(EncodeTable.search(";"));
		         Token.getTokenTable().add(tokenMeata);
                //一句话结束了，现在判断里面的括号和引号有没有是单数的，要是有就报错
		          if(MyStack.getStack().size()!=0)
		          {
		        	  state=0;
		        	  MyStack.getStack().clear();
		        	 }
		          if(MyStack2.getStack().size()!=0)
		          {
		        	  state=0;
		        	  MyStack2.getStack().clear();
		        	 }
		          if(MyStack3.getStack().size()!=0)
		          {
		        	  state=0;
		        	  MyStack3.getStack().clear();
		        	 }
		          if(MyList.getList().size()>0&&MyList.getList().size()/2!=0){
		        	  state=0;
		        	  MyList.getList().clear();
		          }
		          if(MyList2.getList().size()>0&&MyList2.getList().size()/2!=0){
		        	  state=0;
		        	  MyList2.getList().clear();
		          }
		          return true;
			
		case ',':
			    tokenMeata=new TokenMeta();
			    tokenMeata.setLine(Source.getInstance().getRow());
			    tokenMeata.setMeta(",");
			    tokenMeata.setPointer(EncodeTable.search(","));
		        Token.getTokenTable().add(tokenMeata);
		        return true;
		case '.': 
			    tokenMeata=new TokenMeta();
			   tokenMeata.setLine(Source.getInstance().getRow());
			   tokenMeata.setMeta(".");
			   tokenMeata.setPointer(EncodeTable.search("."));
		       Token.getTokenTable().add(tokenMeata);
		       return true;
		case ':':
			   tokenMeata=new TokenMeta();
			   tokenMeata.setLine(Source.getInstance().getRow());
			   tokenMeata.setMeta(":");
			   tokenMeata.setPointer(EncodeTable.search(":"));
		       Token.getTokenTable().add(tokenMeata);
		       return true;
		case '"': 
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("\"");
			 tokenMeata.setPointer(EncodeTable.search("\""));
	         Token.getTokenTable().add(tokenMeata);
	         MyList.getList().add("\"");//用于判断到一句话结束的时候看“的个数
	         return true;
		case '(':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("(");
			 tokenMeata.setPointer(EncodeTable.search("("));
	         Token.getTokenTable().add(tokenMeata);
		     MyStack.getStack().add('(');
		     return true;
		       
		            
		
		case ')':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta(")");
			 tokenMeata.setPointer(EncodeTable.search(")"));
	         Token.getTokenTable().add(tokenMeata);
	    	 if(MyStack.getStack().size()==0){
         			state=0;
         			return true;
         		}
         		MyStack.getStack().pop();
		case '[':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("[");
			 tokenMeata.setPointer(EncodeTable.search("]"));
	         Token.getTokenTable().add(tokenMeata);
		     MyStack2.getStack().add('[');
		     return true;
		       
		            
		
		case ']':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("]");
			 tokenMeata.setPointer(EncodeTable.search("]"));
	         Token.getTokenTable().add(tokenMeata);
	    	 if(MyStack2.getStack().size()==0){
        			state=0;
        			return true;
        		}
        		MyStack2.getStack().pop();
		case '{':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("{");
			 tokenMeata.setPointer(EncodeTable.search("{"));
	         Token.getTokenTable().add(tokenMeata);
		     MyStack3.getStack().add('{');
		     return true;
		       
		            
		
		case '}':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("}");
			 tokenMeata.setPointer(EncodeTable.search("}"));
	         Token.getTokenTable().add(tokenMeata);
	    	 if(MyStack3.getStack().size()==0){
       			state=0;
       			return true;
       		}
       		MyStack3.getStack().pop();
        		
         		
         		
        case '\'':
        	
         	 tokenMeata=new TokenMeta();
   			 tokenMeata.setLine(Source.getInstance().getRow());
   			 tokenMeata.setMeta("\'");
   			 tokenMeata.setPointer(EncodeTable.search("\'"));
   	         Token.getTokenTable().add(tokenMeata);
   	         MyList2.getList().add("\'");//用于判断到一句话结束的时候看“的个数
   	         return true;
	      }
		
		
			
		return false;
	}
	@Override
	public void error(String message) {
		System.out.println(message+"出错");
		System.out.println("第"+Source.getInstance().getRow()+"行"+Source.getInstance().getColspan()+"列出错");
		
	}
	

}

class MyList {//用来记录一句话完了后 ，看引号的个数是基数还是偶数。
	private static List<Object> list ;
	
	static {
		list=new ArrayList<Object> ();
	}
	public static List<Object>  getList() {
		return list;
	}

}

class MyList2 {
	private static List<Object> list ;
	
	static {
		list=new ArrayList<Object> ();
	}
	public static List<Object>  getList() {
		return list;
	}

}


class MyStack {
	private static Stack<Character> stack;
	
	static {
		stack=new Stack<Character>();
	}
	
	public static Stack<Character> getStack() {
		return stack;
	}

}


class MyStack2 {
	private static Stack<Character> stack;
	
	static {
		stack=new Stack<Character>();
	}
	
	public static Stack<Character> getStack() {
		return stack;
	}

}

class MyStack3 {
	private static Stack<Character> stack;
	
	static {
		stack=new Stack<Character>();
	}
	
	public static Stack<Character> getStack() {
		return stack;
	}

}

