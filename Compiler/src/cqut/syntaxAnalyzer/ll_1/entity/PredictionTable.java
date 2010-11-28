package cqut.syntaxAnalyzer.ll_1.entity;

import cqut.util.entity.Syntax;

public class PredictionTable {
	/*
	 * 最后产生的预测分析表
	 * */
	
	String starting;//非终结符
	String end;//面临的输入终结符
	Syntax nextSystax;//在当前非终结starting和面临输入的终结符end的情况下该选取的下一产生式
	
	
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Syntax getNextSystax() {
		return nextSystax;
	}
	public void setNextSystax(Syntax nextSystax) {
		this.nextSystax = nextSystax;
	}


}
