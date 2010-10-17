package cqut.lexicalAnalysis.impl;



import cqut.lexicalAnalysis.Recog;
import cqut.lexicalAnalysis.impl.util.MyStack;
import cqut.util.ReadFile;
import cqut.util.Token;
/*
 * Design by JiaoJian
 * ����
 */

public class DelimiterAnalysis implements Recog{
   int state=1;//״̬
	@Override
	public void error() {
		System.out.println("��"+ReadFile.row+"�У���"+ReadFile.col+"���д�");
		
	}
	@Override
	public boolean recog(Character ch) {//ch�Ƿ��෽��sort����������Ǹ��ַ�
		
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
		
		//������Ҫ�ж�һ���ַ��ǲ��ǽ��͵����������
		Character ch=c;
		switch(ch){
		
		case ';': Token.getDelimiterList().add(ch.toString()); 
		          if(MyStack.getStack().size()!=0)
		          {
		        	  state=0;//���� ״̬ת��Ϊ0
		        	  MyStack.getStack().clear();
		        	 }
		          return true;
			//��������Ĵ��?����������ͽ�state=1;�����?�Ǿ�״̬��Ȼ��0���ͼ�������ִ��
		case ',':Token.getDelimiterList().add(ch.toString());return true;
		case '.': Token.getDelimiterList().add(ch.toString());return true;//��ŵ���;����12.5
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
