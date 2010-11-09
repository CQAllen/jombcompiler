package cqut.lexicalAnalyzer;

public interface Recog {

	/**
	 * 出错信息发布
	 * 
	 * @param message
	 */
	public void error(String message);

	/**
	 * 识别各种字符
	 * 
	 * @param ch
	 */
	public void recog(Character ch);
}
