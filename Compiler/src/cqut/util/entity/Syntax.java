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
	 * 每一个非终结符要对应一个first集和一个follow集。
	 * 
	 * 所有的非终结符最后得到一个预测分析表。
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
