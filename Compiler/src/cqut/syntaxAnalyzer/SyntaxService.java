package cqut.syntaxAnalyzer;

import cqut.util.entity.Syntax;

/**
 * 文法表，以及对文法表的一些操作
 * 
 * @author Cheng
 * 
 * @date 2010-11-16
 */
public interface SyntaxService {

	/**
	 * 根据产生式头得到文法产生式
	 * 
	 * @param starting
	 * @return
	 */
	public Syntax getSyntax(String starting);

	public int size();
}
