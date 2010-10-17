package cqut.lexicalAnalysis.impl.util;

import java.util.ArrayList;

import java.util.List;
/*
 * 焦建
 */

public class MyList {
	private static List<Object> list ;
	
	static {
		list=new ArrayList<Object> ();
	}
	public static List<Object>  getList() {
		return list;
	}

}
