package cqut.gui.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	private static List<Integer> starts = new ArrayList<Integer>();

	public static Integer[] search(int point, String s1, String s2) {
		int a = s1.indexOf(s2);
		if (a == -1) {
			return null;
		} else {
			starts.add(a + point);
			int l1 = s1.length();
			int l2 = s2.length();
			String temp = s1.substring(a + l2, l1);
			search(point + l2 + a, temp, s2);
			temp = "";
			temp = null;
			return starts.toArray(new Integer[starts.size()]);
		}
	}

	public static void clear() {
		starts.clear();
	}

	public static void main(String[] args) {
		for (int a : search(0, "void int main(){int a,b,c;", "int")) {
			System.out.println(a);
		}
	}
}
