package cqut.lexicalAnalysis;

public interface Recog {
	
	public void error();

	public boolean recog(Character ch);
}
