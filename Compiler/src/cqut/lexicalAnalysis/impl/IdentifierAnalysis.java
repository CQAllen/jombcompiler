package cqut.lexicalAnalysis.impl;

import java.util.HashMap;
import java.util.Map;

import cqut.lexicalAnalysis.Recog;

/*
 * 标识符识别
 * 刘
 */
public class IdentifierAnalysis implements Recog{

	private Map isValueIdentifier= new HashMap();

	public Map getIsValueIdentifier() {
		return isValueIdentifier;
	}

	@Override
	public void error() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recog(Character ch) {
		// TODO Auto-generated method stub
		return false;
	}

}
