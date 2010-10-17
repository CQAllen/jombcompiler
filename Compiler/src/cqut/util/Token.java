package cqut.util;

import java.util.ArrayList;
import java.util.List;

import cqut.util.entity.TokenMeta;

public class Token {

	private static List<TokenMeta> tokenTable;

	public static List<TokenMeta> getTokenTable() {
		if (tokenTable == null)
			tokenTable = new ArrayList<TokenMeta>();
		return tokenTable;
	}

}
