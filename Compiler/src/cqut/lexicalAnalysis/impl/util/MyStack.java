package cqut.lexicalAnalysis.impl.util;

import java.util.Stack;
/*
 * 焦建
 */
public class MyStack {
	private static Stack<Character> stack;
	
	static {
		stack=new Stack<Character>();
	}
	
	public static Stack<Character> getStack() {
		return stack;
	}

}
