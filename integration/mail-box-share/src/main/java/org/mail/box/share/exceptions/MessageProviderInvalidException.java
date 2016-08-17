package org.mail.box.share.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Santos, Gilberto
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="There is invalid inputs either nNumber or Email address")  
public class MessageProviderInvalidException extends RuntimeException {

	
	public MessageProviderInvalidException() {
		super();
	}
	
	public MessageProviderInvalidException(String arg0) {
		super(arg0);
	}
	
	
}
