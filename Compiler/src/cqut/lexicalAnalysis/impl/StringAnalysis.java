package cqut.lexicalAnalysis.impl;

import cqut.lexicalAnalysis.Recog;

public class StringAnalysis implements Recog {

	Recog r = new DigitAnalysis();

	public StringAnalysis(String a) {
	}

	@Override
	public void error() {
		r.error();
	}

	@Override
	public boolean recog(Character ch) {
		return false;
	}

}
