package cqut.syntaxAnalyzer;

/**
 * 根据语法产生式和语法规则分析一段代码是否属于该文法
 * 
 * @author Cheng
 * 
 * @date 2010-11-16
 */
public interface Validation {
	
	/**
	 * 判断是否属于该文法
	 * 
	 * @return
	 */
	public boolean validate();

	/**
	 * 如果出现错误的处理办法
	 */
	public void error();
}
