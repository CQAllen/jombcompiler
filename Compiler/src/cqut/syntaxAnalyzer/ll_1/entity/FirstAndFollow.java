package cqut.syntaxAnalyzer.ll_1.entity;

import java.util.List;

public class FirstAndFollow {
	/*
	 * 每一个非终结符应该对应一 个first集和一个follow集
	 * */
	String starting;//非终结符
	List<String> first;//非终结符starting的frist集
	List<String> follow;
	
	
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public List<String> getFirst() {
		return first;
	}
	public void setFirst(List<String> first) {
		this.first = first;
	}
	public List<String> getFollow() {
		return follow;
	}
	public void setFollow(List<String> follow) {
		this.follow = follow;
	}


}
