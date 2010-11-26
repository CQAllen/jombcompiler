package cqut.util.entity;

/**
 * 错误信息实体
 * 
 * @author Cheng
 * 
 * @date 2010-11-26
 */
public class Error {

	public static final String ERROR_TYPE_LEXICAL = "词法错误";
	public static final String ERROR_TYPE_SYNTAX = "语法错误";

	private String description;// 错误描述
	private String resource;// 源文件名
	private String path;// 源文件路径
	private int location;// 错误定位
	private String type;// 错误类型

	public Error(String description, String type) {
		this.description = description;
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}