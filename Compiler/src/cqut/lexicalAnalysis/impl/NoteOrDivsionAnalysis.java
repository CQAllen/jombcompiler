package cqut.lexicalAnalysis.impl;


import cqut.lexicalAnalysis.Recog;
import cqut.util.ReadFile;
import cqut.util.Token;
 /*
  * Designed by JiaoJian
  * ����ע�� �� ���� 
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
				doubleNote();return false;//������ȡ��һ���ַ�,b���ҷ��ദ��
			}
		else {//ǰ������ע�Ͷ������ǣ��������ǳ��ţ�Ȼ�󽫳��ŷŵ�Token������Ӧ��λ��
			
			Token.getOperaterList().add("/");
			
			//�������жϳ���ǰ���Ƿ��д�
			  state=0;
				return true;
			}
		
	}
	
	

	private boolean sigenlineNote(){//����ע��
		while(true){
			if(ReadFile.getNextChar().equals('\n')){ //һֱ��ȡ���ļ��Ļ��з�����������ע�ͽ���
				break;
			 }
			}
			
			return false;//���ǳ��žͻ���false
			
	}
	
	
	
	private boolean doubleNote(){ //����ע��
		while(true){
			if(ReadFile.getNextChar().equals('*')&&ReadFile.getNextChar().equals('/')){//һֱ��ȡ�����������ַ���* / �������ע�ͽ���
				break;  //���ע�ͽ���������ѭ��
			}
		}
		
		return false;// ��ע�ͣ������ǳ��ţ����Է���false
	}
	

	public void error() {
		//�������жϳ���ǰ���Ƿ��д�
		  int currentrow=(ReadFile.row=ReadFile.row-1);// '/'�����±�
		 StringBuffer bf=new StringBuffer();
		 Character c;
		 String str;
		while(true){
			if((c=(Character)ReadFile.getLastChar()).equals('=')){//��/��ʼ�������һ���ַ���=����ֹͣ���ˡ�Ҳ�ͻ��˵���=���
				
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
				System.out.println("��"+ReadFile.col+"�г���");
			}
		} else {
			System.out.println("��"+ReadFile.col+"�г���");
		}
		
	}

		
	}


