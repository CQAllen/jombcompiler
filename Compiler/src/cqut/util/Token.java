package cqut.util;

import java.util.ArrayList;
import java.util.List;

import cqut.exception.NoSuchTokenMetaException;
import cqut.util.entity.Source;
import cqut.util.entity.TokenMeta;

/**
 * Token表及服务
 * 
 * @author Cheng
 * 
 */
public class Token {

	private static List<TokenMeta> tokenTable;

	private static Token token;
	private int current;

	/**
	 * 编码类型为关键字
	 */
	public static final int ENCODETYPE_KEYWORD = 0;
	/**
	 * 编码类型为运算符
	 */
	public static final int ENCODETYPE_OPERATER = 1;
	/**
	 * 编码类型为标识符
	 */
	public static final int ENCODETYPE_ID = 2;
	/**
	 * 编码类型为常数
	 */
	public static final int ENCODETYPE_CONSTANT = 3;
	/**
	 * 编码类型为界符
	 */
	public static final int ENCODETYPE_DELIMITER = 4;

	public static Token getInstance() {
		if (token == null) {
			token = new Token();
			tokenTable = new ArrayList<TokenMeta>();
		}
		return token;
	}

	public void clear() {
		tokenTable.clear();
	}

	/**
	 * 将元信息插入TOKEN表
	 * 
	 * @param meta
	 * @param pointer
	 */
	public void insert(String meta, int pointer) {
		TokenMeta tokenMeta = new TokenMeta();
		tokenMeta.setLine(Source.getInstance().getRow());
		tokenMeta.setMeta(meta);
		tokenMeta.setPointer(pointer);
		tokenTable.add(tokenMeta);
	}

	public int getEncodeType(TokenMeta meta) throws NoSuchTokenMetaException {
		int pointer = meta.getPointer();
		if (pointer <= 23 && pointer >= 1) {
			return ENCODETYPE_KEYWORD;
		} else if (pointer >= 24 && pointer <= 34) {
			return ENCODETYPE_OPERATER;
		} else if (pointer == 35) {
			return ENCODETYPE_ID;
		} else if (pointer >= 36 && pointer <= 39) {
			return ENCODETYPE_CONSTANT;
		} else if (pointer >= 40 && pointer <= 52) {
			return ENCODETYPE_DELIMITER;
		} else {
			throw new NoSuchTokenMetaException("未找到该Token元信息@" + meta.getMeta());
		}
	}

	public List<TokenMeta> getAllMeta() {
		return tokenTable;
	}

	/**
	 * 获取当前指向的TOKEN表元信息
	 * 
	 * @return
	 */
	public TokenMeta getCurrentMeta() {
		return tokenTable.get(current);
	}

	/**
	 * 获取下一个TOKEN表元信息
	 * 
	 * @return
	 */
	public TokenMeta getNext() {
		return tokenTable.get(++current);
	}

	/**
	 * 获取上一个TOKEN表元信息
	 * 
	 * @return
	 */
	public TokenMeta getPrevious() {
		return tokenTable.get(--current);
	}

	/**
	 * 获取第一个TOKEN表元信息，指针不移动
	 * 
	 * @return
	 */
	public TokenMeta getFirst() {
		return tokenTable.get(0);
	}

	/**
	 * 获取最后一个TOKEN表元信息，指针不移动
	 * 
	 * @return
	 */
	public TokenMeta getLast() {
		return tokenTable.get(tokenTable.size() - 1);
	}

	public TokenMeta get(int index) {
		return tokenTable.get(index);
	}

	public int size() {
		return tokenTable.size();
	}

}
