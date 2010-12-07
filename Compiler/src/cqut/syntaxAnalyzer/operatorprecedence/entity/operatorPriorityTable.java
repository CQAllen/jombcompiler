package cqut.syntaxAnalyzer.operatorprecedence.entity;

import java.util.LinkedList;

/**
 * 
 * @author Allen
 * 
 *         算法优先表
 */
public class operatorPriorityTable {
	private LinkedList<LinkedList<String>> PriorityTable = new LinkedList<LinkedList<String>>();

	public LinkedList<LinkedList<String>> getPriorityTable() {
		return PriorityTable;
	}
}
