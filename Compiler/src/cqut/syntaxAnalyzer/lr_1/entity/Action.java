package cqut.syntaxAnalyzer.lr_1.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ACTION表元组
 * 
 * @author Cheng
 * 
 * @date 2010-11-17
 */
public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * action表的初始化需要所有的终结符号
	 * 
	 * @param ch
	 */
	public Action(List<Character> ch) {
	}
}
