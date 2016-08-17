package org.feed.rss.share.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE,  reason="There is invalid inputs either nNumber or URL")  // 406
public class FeedInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeedInvalidException() {
		super();
	}
	
	public FeedInvalidException(String arg0) {
		super(arg0);
	}
	
	
}
