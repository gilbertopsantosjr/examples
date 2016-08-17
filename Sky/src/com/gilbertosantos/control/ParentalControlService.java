package com.gilbertosantos.control;

import java.util.List;

import com.gilbertosantos.data.Database;
import com.gilbertosantos.exceptions.DenyAccessException;
import com.gilbertosantos.exceptions.TechnicalFailureException;
import com.gilbertosantos.exceptions.TitleNotFoundException;
import com.gilbertosantos.movie.Movie;
import com.gilbertosantos.movie.MovieService;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public class ParentalControlService implements MovieService {

	
	public boolean isAbleToView(ParentalControlPreference settings, String movieId) throws TitleNotFoundException, TechnicalFailureException, DenyAccessException{
		boolean option = false;
		String result = getParentalControlLevel(movieId);
		if(result != ""){
			ParentalControlLevel parentControl = ParentalControlLevel.valueOf(result);
			if( parentControl.getLevel() > settings.getParentalControlLevel().getLevel() )
				throw new DenyAccessException();
			else 
				option = true;
		} else
			throw new TechnicalFailureException();
		return option;
	}
	

	@Override
	public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
		Database data = new Database();
		List<Movie> movies = data.getMovies();
		for (Movie movie : movies) {
			if( movie.getId().equals(movieId) )
				return movie.getPcontrolLevel().name();
		}
		throw new TitleNotFoundException();
	}
	
}

