package cqut.syntaxAnalyzer;

import java.util.List;

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
	 * 获取全部产生式
	 * 
	 * @return
	 */
	public List<Syntax> getSyntax();

	/**
	 * 根据产生式头得到文法产生式
	 * 
	 * @param starting
	 * @return
	 */
	public Syntax getSyntax(String starting);

	/**
	 * 根据文法头获取所有候选式中的非终结符
	 * 
	 * @param starting
	 * @return
	 */
	public List<String> getNonTerminalStarting(String starting);

	/**
	 * 根据文法头获取所有候选式中索引位置为指定参数的非终结符
	 * 
	 * @param starting
	 *            文法头
	 * @param index
	 *            索引位置，就是第几个候选式，从0开始计算
	 * @return
	 */
	public List<String> getNonTerminalStarting(String starting, int index);

	/**
	 * 获取指定文法中的所有非终结符，以|为分隔符
	 * 
	 * @param starting
	 * @return
	 */
	public List<String> getTerminalSymbol(String starting);

	/**
	 * 获取指定文法中指定索引位置的的所有非终结符，以|为分隔符
	 * 
	 * @param starting
	 * @param index
	 * @return
	 */
	public List<Character> getTerminalSymbol(String starting, int index);

	public int size();
}
