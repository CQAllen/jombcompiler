package cqut.syntaxAnalyzer.ll_1;

import cqut.syntaxAnalyzer.Validation;

public class LL1_SyntaxAnalyzer implements Validation {
	/*
	 * 根据构造好的预测分析表，validate调用预测分析表，确定该句子是否有语法错误
	 * */

	@Override
	public void error() {
	}

	@Override
	public boolean validate() {
		return false;
	}

}
