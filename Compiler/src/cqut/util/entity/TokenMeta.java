package cqut.util.entity;

/**
 * Token表元信息
 * 
 * @author Cheng
 * 
 */
public class TokenMeta {

	private int line;// 该字符所在行数
	private String meta;// 字符
	private int pointer;// 字符在单词编码表的类别码

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
}
