package com.wipro.venu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private String message;
	private String status;
	
	public String getMessage() {
		return message;
	}


}
