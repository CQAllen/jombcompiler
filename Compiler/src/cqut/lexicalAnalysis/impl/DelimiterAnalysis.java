package cqut.lexicalAnalysis.impl;



import cqut.lexicalAnalysis.Recog;
import cqut.lexicalAnalysis.impl.util.MyStack;
import cqut.util.ReadFile;
import cqut.util.Token;
/*
 * Design by JiaoJian
 * 界符号
 */

public class DelimiterAnalysis implements Recog{
   
   int state=1;//状态
	@Override
	public void error() {
		System.out.println("第"+ReadFile.row+"行，第"+ReadFile.col+"列有错");
		
	}
      
	@Override
	public boolean recog(Character ch) {//ch是分类方法sort里面读到的那个字符
		
		while (true){
			isDelimiter(ch);
			switch(state){
			case 1:ReadFile.sort();
			case 0:
				error();
			    break;
		       }
			}
		
		}
		
		
	
	public boolean isDelimiter(Character c){
		
		//其他人要判断一个字符是不是界符就调用这个方法
		Character ch=c;
		switch(ch){
		
		case ';': Token.getDelimiterList().add(ch.toString()); 
		          if(MyStack.getStack().size()!=0)
		          {
		        	  state=0;//出错 状态转换为0
		        	  MyStack.getStack().clear();
		        	 }
		          return true;
			//后面带具体的处理方法。如果出错就将state=1;如果不出错，那就状态依然是0。就继续向下执行
		case ',':Token.getDelimiterList().add(ch.toString());return true;
		case '.': Token.getDelimiterList().add(ch.toString());return true;//句号的用途：如12.5
		case ':':Token.getDelimiterList().add(ch.toString());return true;
		case '"':Token.getDelimiterList().add(ch.toString());return true;
		case '(':{Token.getDelimiterList().add(c.toString()); 
		            
		            MyStack.getStack().add('(');
		            return true;
		       
		            
		}
		case ')':{
			Token.getDelimiterList().add(c.toString());
	    	if(MyStack.getStack().size()==0){
         			error();
         			return true;
         		}
         		MyStack.getStack().pop();
         	 }
	      }
		return false;
	}
	

}
