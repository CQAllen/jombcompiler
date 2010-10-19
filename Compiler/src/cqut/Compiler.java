package cqut;

import java.util.List;

import cqut.util.entity.Source;

public class Compiler {
	public static void main(String[] args) {
		List<String> ll = Source.getInstance().getSource();
		for (String s : ll) {
			System.out.println(s);
		}
	}
}
