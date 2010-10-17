package cqut.lexicalAnalysis.impl.util;

import java.util.Stack;
/*
 * 焦健
 */
public class MyStack3 {
	private static Stack<Character> stack;
	
	static {
		stack=new Stack<Character>();
	}
	
	public static Stack<Character> getStack() {
		return stack;
	}

}
