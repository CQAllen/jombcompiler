package cqut.util.entity;

public class Error {

	int row;
	String message;

	public Error(int row, String message) {
		super();
		this.row = row;
		this.message = message;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}