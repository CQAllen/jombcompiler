package cqut.util.entity;

import java.io.Serializable;

/**
 * 语法类
 * 
 * @author Cheng
 * 
 * @date 2010-11-16
 */
public class Syntax implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String starting;// 产生式头，有可能是开始符号
	private String production;// 产生式

	public String getStarting() {
		return starting;
	}

	public void setStarting(String starting) {
		this.starting = starting;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

}
