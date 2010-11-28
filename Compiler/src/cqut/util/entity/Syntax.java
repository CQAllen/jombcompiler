package cqut.util.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 语法实体
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
	private List<String> production;// 各种候选式

	public String getStarting() {
		return starting;
	}

	public void setStarting(String starting) {
		this.starting = starting;
	}

	public List<String> getProduction() {
		return production;
	}

	public void setProduction(List<String> production) {
		this.production = production;
	}

}
