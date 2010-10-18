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
}
