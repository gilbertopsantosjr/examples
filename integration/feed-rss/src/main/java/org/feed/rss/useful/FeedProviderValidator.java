package org.feed.rss.useful;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.validator.UrlValidator;
import org.feed.rss.share.exceptions.FeedInvalidException;
import org.feed.rss.share.model.FeedProvider;

/**
 * @author Santos, Gilberto
 *
 */
@SuppressWarnings("deprecation")
public class FeedProviderValidator {
	
	public static final String message = "this is an invalid feed url rss !";
	public static final String message_02 = "There is invalid inputs either nNumber or URL or NickName";
	
	public static void validate(FeedProvider _feedProvider) throws FeedInvalidException {
		// rules for validate nNumber ?
		if(_feedProvider.getnNumber() == null || _feedProvider.getnNumber().equals("") 
				|| _feedProvider.getNickName() == null || _feedProvider.getNickName().equals("")
				|| _feedProvider.getUrl() == null || _feedProvider.getUrl().equals("") )
			throw new FeedInvalidException(message_02);
		
		UrlValidator urlValidator = new UrlValidator();		 
	    //validation URL
	    if (!urlValidator.isValid(_feedProvider.getUrl())) 
	    	throw new FeedInvalidException(message);
	    
	    // verify if URL is a valid RSS
	    try {
		    
	    	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    org.w3c.dom.Document doc = builder.parse(_feedProvider.getUrl()); 
		    boolean validRss = doc.getDocumentElement().getNodeName().equalsIgnoreCase("rss");
		    if(!validRss)
		    	throw new FeedInvalidException(message);
		    
	    } catch (FeedInvalidException fe) {
	    	
		   throw fe;
		   
	    } catch (Exception e) {
	    	//for unknown case
	    	throw new FeedInvalidException( message +"\n"+ e.getMessage() );
		}
	    
	}
	
}
