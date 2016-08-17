package com.gilbertosantos.data;

import java.util.ArrayList;
import java.util.List;

import com.gilbertosantos.control.ParentalControlLevel;
import com.gilbertosantos.movie.Movie;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public class Database {

	
	private final static List<Movie> movies = new ArrayList<>();
	// Add some movies.
	{
		movies.add( new Movie ("5", "Superman x Batman", ParentalControlLevel.FAMILY) );
		movies.add( new Movie ("3", "Frozen", ParentalControlLevel.CHILD) );
		movies.add( new Movie ("33", "The Conjuring", ParentalControlLevel.ADULT) );
		movies.add( new Movie ("10", "Love and other drugs", ParentalControlLevel.ADULT) );
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
}
