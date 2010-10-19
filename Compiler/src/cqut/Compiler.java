package cqut;

import java.util.List;

import cqut.util.Token;
import cqut.util.entity.Source;

public class Compiler {
	public static void main(String[] args) {
		List<String> ll = Source.getInstance().getSource();
		
		for (String s : ll) {
			System.out.println(s);
		}
		
		Source.getInstance().sort();
		for(int i=0;i<Token.getTokenTable().size();i++){
			System.out.println(Token.getTokenTable().get(1).toString());
		}
		
	}
}
