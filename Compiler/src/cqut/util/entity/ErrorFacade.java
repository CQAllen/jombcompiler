package cqut.util.entity;

import java.util.ArrayList;
import java.util.List;

import cqut.util.SourceReader;

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

	public void addError(String message, String type) {
		Error error = new Error(message, type);
		error.setLocation(Source.getInstance().getRow());
		error.setPath(SourceReader.getAbsoluteFilePath());
		error.setResource(SourceReader.getFileName());
		errors.add(error);
		count++;
	}

	public int getSize() {
		return count;
	}

	public List<Error> getErrors() {
		return errors;
	}
	
	public void clear(){
		errors.clear();
	}
}
