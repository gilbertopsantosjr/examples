package com.gilbertosantos.movie;

import com.gilbertosantos.exceptions.TechnicalFailureException;
import com.gilbertosantos.exceptions.TitleNotFoundException;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public interface MovieService {
	
	String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
