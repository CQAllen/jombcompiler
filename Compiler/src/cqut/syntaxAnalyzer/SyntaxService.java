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

	public Syntax getNext();

	public Syntax getPrevious();

	public Syntax getLast();

	public Syntax getFirst();

	public Syntax getCurrent();

	public int size();
}
