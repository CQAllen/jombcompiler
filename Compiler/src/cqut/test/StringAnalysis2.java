package cqut.test;

import cqut.lexicalAnalysis.Recog;
import cqut.util.Token;
import cqut.util.entity.Source;
import cqut.util.entity.SymbolMeta;
import cqut.util.entity.TokenMeta;

public class StringAnalysis2 implements Recog{
    StringBuffer bf= new StringBuffer();
	private static StringAnalysis2 s2;
	public void error(String message) {
	    System.out.println(message+"出错");
		
	}
	static {
		s2= new StringAnalysis2();
	}
	public static StringAnalysis2 getInstance(){
		return s2;
	}

	
	public void recog(Character ch) {
		//正常情况下向下执行
		
		isString(ch);
		Source.getInstance().sort();
	
		
		
	}
	
	public void isString(Character ch){
        
        
         if(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_'||ch>=0||ch<=9){
        	 bf.append(ch);
        	 ch=Source.getInstance().getNextCharacter();
        	 isString(ch);
         }
         else if(ch == ' ' || ch == ';' ||ch == '+' || ch == '-' || ch == '*'
				||ch == '/'){//正常结束
        	 
        	 addIdentifier(bf.toString());
 			 insertSymbol(bf.toString());
 			 return;
         }
         else{//带非法字符 报错
        	 error(ch.toString());
        	 ch=Source.getInstance().getNextCharacter();
        	 isString(ch);
         }
 		
	}
	private void addIdentifier(String Str_temp) {
		// TODO Auto-generated method stub
		TokenMeta Cur = new TokenMeta();
		Cur.setLine(Source.getInstance().getRow());
		Cur.setMeta(Str_temp);
		Cur.setPointer(35);
		Token.getTokenTable().add(Cur);
		bf = new StringBuffer();// 清空字符缓存
	}
	private void insertSymbol(String Str_temp) {
		// TODO Auto-generated method stub
		SymbolMeta Cur=new SymbolMeta();
		Cur.setMeta(Str_temp);
		Cur.setPointer(35);
		Cur.setKind("简单变量");
	}

}
