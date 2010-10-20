package cqut.util;

import java.util.ArrayList;
import java.util.List;

import cqut.util.entity.SymbolMeta;

/**
 * 符号表
 * 
 * @author Cheng
 * 
 */
public class Symbol {

	/**
	 * 整型类型
	 */
	public static final String TYPE_INTEGER = "整型";// 整型类型
	/**
	 * 实型
	 */
	public static final String TYPE_FLOAT = "实型";// 实型
	/**
	 * 简单变量
	 */
	public static final String KIND_VAR = "简单变量";// 简单变量
	/**
	 * 数值
	 */
	public static final String KIND_NUM = "数值";// 数值

	private static Symbol symbol;
	private static List<SymbolMeta> symbols;

	private Symbol() {
	}

	public static Symbol getInstance() {
		if (symbol == null) {
			symbol = new Symbol();
		}
		if (symbols == null) {
			symbols = new ArrayList<SymbolMeta>();
		}
		return symbol;
	}

	public List<SymbolMeta> getSymbol() {
		return symbols;
	}

	public void insert(SymbolMeta meta) {
		symbols.add(meta);
	}
	
	public void clear(){
		symbols.clear();
	}
}
