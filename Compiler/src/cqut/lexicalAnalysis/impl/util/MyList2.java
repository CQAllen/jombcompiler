package cqut.lexicalAnalysis.impl.util;

import java.util.ArrayList;
import java.util.List;
/*
 * 焦健
 */
public class MyList2 {
	private static List<Object> list ;
	
	static {
		list=new ArrayList<Object> ();
	}
	public static List<Object>  getList() {
		return list;
	}

}
