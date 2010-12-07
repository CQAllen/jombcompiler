package cqut.syntaxAnalyzer.operatorprecedence;

import java.util.List;

import cqut.syntaxAnalyzer.Validation;
import cqut.util.Token;
import cqut.util.entity.TokenMeta;

/**
 * 算符优先分析方法
 * 
 * @author Cheng
 * 
 * @date 2010-11-16
 */
public class OperatorPrecedenceAnalyzer implements Validation {
	private List<TokenMeta> tokenTable;

	@Override
	public void error() {
	}

	@Override
	public boolean validate() {
		tokenTable = Token.getInstance().getAllMeta();
		return false;
	}

}
