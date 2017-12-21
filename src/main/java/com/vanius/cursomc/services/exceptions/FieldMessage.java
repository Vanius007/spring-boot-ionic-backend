package com.vanius.cursomc.services.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String FieldName;
	private String Message;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String fieldName, String message) {
		super();
		FieldName = fieldName;
		Message = message;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	

}
