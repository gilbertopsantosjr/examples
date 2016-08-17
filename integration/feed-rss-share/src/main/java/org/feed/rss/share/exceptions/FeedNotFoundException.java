/**
 * 
 */
package org.feed.rss.share.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Santos, Gilberto
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="There is no feed provider with those arguments")  // 404
public class FeedNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
