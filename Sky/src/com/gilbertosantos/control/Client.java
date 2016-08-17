package com.gilbertosantos.control;

import com.gilbertosantos.exceptions.DenyAccessException;
import com.gilbertosantos.exceptions.TechnicalFailureException;
import com.gilbertosantos.exceptions.TitleNotFoundException;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public class Client {

	private final static ParentalControlService pcs = new ParentalControlService();
	
	public static void main(String[] args) {
		
		ParentalControlPreference pcp = new ParentalControlPreference();
		pcp.setParentalControlLevel(ParentalControlLevel.ADULT);
		
		try {
			pcs.isAbleToView(pcp, "33");
			
		} catch (DenyAccessException e){
		
			System.out.println(" you're not able to see this movie !");
			
		} catch (TitleNotFoundException e) {
			
			System.out.println(" The movie service could not find the given movie ! ");
			
		} catch (TechnicalFailureException e) {
			
			System.out.println(" System error ! ");
		}
		

	}

}
