package cqut.util.entity;

import java.util.ArrayList;
import java.util.List;

public class ErrorFacade {
	private static ErrorFacade errorFacade;
	private static int count = 0;
	private static List<Error> errors;

	public static ErrorFacade getInstance() {
		if (errorFacade == null) {
			errorFacade = new ErrorFacade();
			errors = new ArrayList<Error>();
		}
		return errorFacade;
	}

	public void addError(int row, String message) {
		errors.add(new Error(row, message));
		count++;
	}

	public int getSize() {
		return count;
	}

	public List<Error> getErrors() {
		return errors;
	}
}
