/**
 * 
 */
package org.feed.rss.share.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Santos, Gilberto
 */
@ResponseStatus(value=HttpStatus.FOUND, reason="There is other feed provider with the same URL")  // 409
public class FeedFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
