package cqut.test;

import cqut.util.Symbol;
import cqut.util.Token;
import cqut.util.entity.Source;

public class Regeg {
	public static void main(String[] args) {
		// System.out.println("2".matches("[0-9]{1}"));
		Source.getInstance().sort();
		System.out.println("Token：表");
		for (int i = 0; i < Token.getTokenTable().size(); i++)
			System.out.println(Token.getTokenTable().get(i).getMeta() + "\t"
					+ (Token.getTokenTable().get(i).getLine() + 1) + "行\t"
					+ Token.getTokenTable().get(i).getPointer());
		System.out.println("符号表：");
		for (int i = 0; i < Symbol.getInstance().getSymbol().size(); i++)
			System.out.println(Symbol.getInstance().getSymbol().get(i)
					.getMeta()
					+ "\t"
					+ Symbol.getInstance().getSymbol().get(i).getKind()
					+ "\t"
					+ Symbol.getInstance().getSymbol().get(i).getPointer());
//		System.out.println("".matches("[.\\* =-]"));
	}
}
