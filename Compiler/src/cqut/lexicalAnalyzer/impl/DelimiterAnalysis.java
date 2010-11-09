package cqut.lexicalAnalyzer.impl;





import cqut.lexicalAnalyzer.Recog;
import cqut.util.EncodeTable;
import cqut.util.Symbol;
import cqut.util.Token;
/*
 * Design by JiaoJian
 * 界符
 */
import cqut.util.entity.ErrorFacade;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
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
			case 1:
				if(Source.getInstance().isLastCharacter()){
					return;
				}
				Source.getInstance().getNextCharacter();
				   
			   Source.getInstance().sort();break;
			case 0:
				System.out.println(Source.getInstance().isLastCharacter());
				if(Source.getInstance().isLastCharacter()){
					
					return;
				}
				System.out.println(Source.getInstance().getNextCharacter());
				Source.getInstance().sort();break;
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
//	         MyList.getList().add("\"");//用于判断到一句话结束的时候看“的个数 
	         StringBuffer sb=new StringBuffer();
	         Character c2;
	   	      while(true){
	   	    	      if(Source.getInstance().isLastCharacter()){
	   	    	    	  state=0;
	   	    	    	  ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, ch.toString());
	   	    	    	  break;
	   	    	      }
					  c2=Source.getInstance().getNextCharacter();
					 if(c2=='\n'){
						 state=0;
						 ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, ch.toString());
						break; 
					 }
					 
					 if(c2=='\"'){
						 
						  //保存String
			   	    	  TokenMeta t1=new TokenMeta();
			   	    	  t1.setMeta(sb.toString());
			   	    	  t1.setLine(Source.getInstance().getRow());
			   	    	  t1.setPointer(38);
			   	    	  Token.getTokenTable().add(t1);
			   	    	  
			   	    	  SymbolMeta sm=new SymbolMeta();
			   	    	  sm.setPointer(38);
			   	    	  sm.setKind(Symbol.KIND_NUM);
			   	    	  sm.setMeta(sb.toString());
			   	    	  Symbol.getInstance().insert(sm);
			   	          sb=null;
			   	          
						 tokenMeata=new TokenMeta();
						 tokenMeata.setLine(Source.getInstance().getRow());
						 tokenMeata.setMeta("\"");
						 tokenMeata.setPointer(EncodeTable.search("\""));
				         Token.getTokenTable().add(tokenMeata);
//				         MyList.getList().add("\"");
				         break;
					 }
					 sb.append(c2);
				 }
	   	     
//	   	    	  //保存String
//	   	    	  TokenMeta t1=new TokenMeta();
//	   	    	  t1.setMeta(sb.toString());
//	   	    	  t1.setLine(Source.getInstance().getRow());
//	   	    	  t1.setPointer(38);
//	   	    	  Token.getTokenTable().add(t1);
//	   	    	  
//	   	    	  SymbolMeta sm=new SymbolMeta();
//	   	    	  sm.setPointer(38);
//	   	    	  sm.setKind(Symbol.KIND_NUM);
//	   	    	  sm.setMeta(sb.toString());
//	   	    	  Symbol.getInstance().insert(sm);
//	   	          sb=null;
	         return true;
		case '(':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("(");
			 tokenMeata.setPointer(EncodeTable.search("("));
	         Token.getTokenTable().add(tokenMeata);
//		     MyStack.getStack().add('(');
		     return true;
		       
		            
		
		case ')':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta(")");
			 tokenMeata.setPointer(EncodeTable.search(")"));
	         Token.getTokenTable().add(tokenMeata);
//	    	 if(MyStack.getStack().size()==0){
//         			state=0;
//         			return true;
//         		}
//         		MyStack.getStack().pop();
         		break;
		case '[':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("[");
			 tokenMeata.setPointer(EncodeTable.search("]"));
	         Token.getTokenTable().add(tokenMeata);
//		     MyStack2.getStack().add('[');
		     return true;
		       
		            
		
		case ']':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("]");
			 tokenMeata.setPointer(EncodeTable.search("]"));
	         Token.getTokenTable().add(tokenMeata);
//	    	 if(MyStack2.getStack().size()==0){
//        			state=0;
//        			return true;
//        		}
//        		MyStack2.getStack().pop();
        		break;
		case '{':
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("{");
			 tokenMeata.setPointer(EncodeTable.search("{"));
	         Token.getTokenTable().add(tokenMeata);
//		     MyStack3.getStack().add('{');
		     return true;
		     
		            
		
		case '}':
			
			 tokenMeata=new TokenMeta();
			 tokenMeata.setLine(Source.getInstance().getRow());
			 tokenMeata.setMeta("}");
			 tokenMeata.setPointer(EncodeTable.search("}"));
	         Token.getTokenTable().add(tokenMeata);
//	    	 if(MyStack3.getStack().size()==0){
//       			state=0;
//       			return true;
//       		}
//       		MyStack3.getStack().pop();
       		break;  
         		
         		
        case '\'':
        	 StringBuffer sb3=new StringBuffer();
         	 tokenMeata=new TokenMeta();
   			 tokenMeata.setLine(Source.getInstance().getRow());
   			 tokenMeata.setMeta("\'");
   			 tokenMeata.setPointer(EncodeTable.search("\'"));
   	         Token.getTokenTable().add(tokenMeata);
//   	         MyList2.getList().add("\'");//用于判断到一句话结束的时候看“的个数
   	      Character c3;
   	      while(true){
   	    	      if(Source.getInstance().isLastCharacter()){
   	    	    	  state=0;
   	    	    	ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, ch.toString());
   	    	    	  break;
   	    	      }
				  c3=Source.getInstance().getNextCharacter();
				 if(c3=='\n'){
					 state=0;
					 ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, ch.toString());
					break; 
				 }
				 
				 if(c3=='\''){
					 
					 if(sb3.length()>1){//一个单引号里面有几个字符报错
			   	    	  state=0;
			   	    	ErrorFacade.getInstance().addError(Source.getInstance().getRow()+1, ch.toString());
			   	      }else{
			   	    	  //保存char
			   	    	  System.out.println("char="+sb3.toString());
			   	    	  TokenMeta t=new TokenMeta();
			   	    	  t.setMeta(sb3.toString());
			   	    	  t.setLine(Source.getInstance().getRow());
			   	    	  t.setPointer(38);
			   	    	  Token.getTokenTable().add(t);
			   	    	  
			   	    	 SymbolMeta sm2=new SymbolMeta();
			 	    	  sm2.setPointer(38);
			 	    	  sm2.setKind(Symbol.KIND_NUM);
			 	    	  sm2.setMeta(sb3.toString());
			 	    	  Symbol.getInstance().insert(sm2);
			   	    	  sb3=null;
			   	    	  
			   	      }
					 tokenMeata=new TokenMeta();
					 tokenMeata.setLine(Source.getInstance().getRow());
					 tokenMeata.setMeta("\'");
					 tokenMeata.setPointer(EncodeTable.search("\'"));
			         Token.getTokenTable().add(tokenMeata);
//			         MyList.getList().add("\'");
			         break;
				 }
				 sb3.append(c3);
			 }
//   	      if(sb3.length()>1){//一个单引号里面有几个字符报错
//   	    	  state=0;
//   	    	 
//   	      }else{
//   	    	  //保存char
//   	    	  System.out.println("char="+sb3.toString());
//   	    	  TokenMeta t=new TokenMeta();
//   	    	  t.setMeta(sb3.toString());
//   	    	  t.setLine(Source.getInstance().getRow());
//   	    	  t.setPointer(38);
//   	    	  Token.getTokenTable().add(t);
//   	    	  
//   	    	 SymbolMeta sm2=new SymbolMeta();
// 	    	  sm2.setPointer(38);
// 	    	  sm2.setKind(Symbol.KIND_NUM);
// 	    	  sm2.setMeta(sb3.toString());
// 	    	  Symbol.getInstance().insert(sm2);
//   	    	  sb3=null;
//   	    	  
//   	      }
   	         return true;
	      }
		
		
			
		return false;
	}
	@Override
	public void error(String message) {
		System.out.println(message+"出错");
//		System.out.println("第"+Source.getInstance().getRow()+"行"+Source.getInstance().getColspan()+"列出错");
		
	}
	

}
//
//class MyList {//用来记录一句话完了后 ，看引号的个数是基数还是偶数。
//	private static List<Object> list ;
//	
//	static {
//		list=new ArrayList<Object> ();
//	}
//	public static List<Object>  getList() {
//		return list;
//	}
//
//}
//
//class MyList2 {
//	private static List<Object> list ;
//	
//	static {
//		list=new ArrayList<Object> ();
//	}
//	public static List<Object>  getList() {
//		return list;
//	}
//
//}
//
//
//class MyStack {
//	private static Stack<Character> stack;
//	
//	static {
//		stack=new Stack<Character>();
//	}
//	
//	public static Stack<Character> getStack() {
//		return stack;
//	}
//
//}
//
//
//class MyStack2 {
//	private static Stack<Character> stack;
//	
//	static {
//		stack=new Stack<Character>();
//	}
//	
//	public static Stack<Character> getStack() {
//		return stack;
//	}
//
//}
//
//class MyStack3 {
//	private static Stack<Character> stack;
//	
//	static {
//		stack=new Stack<Character>();
//	}
//	
//	public static Stack<Character> getStack() {
//		return stack;
//	}

//}

