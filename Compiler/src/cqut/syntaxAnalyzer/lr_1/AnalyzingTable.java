package cqut.syntaxAnalyzer.lr_1;

import java.io.Serializable;

/**
 * LR(1)语法分析表
 * 
 * @author Cheng
 * 
 * @date 2010-11-17
 */
public class AnalyzingTable {

	/**
	 * 表
	 */
	private Serializable[][] table;

	public Serializable[][] getTable() {
		return table;
	}

}
