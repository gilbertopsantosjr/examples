package com.gilbertosantos.movie;

import com.gilbertosantos.control.ParentalControlLevel;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public class Movie {

	private String id;
	private String title;
	private ParentalControlLevel pcontrolLevel;
	

	public Movie(String id, String title, ParentalControlLevel pcontrolLevel) {
		super();
		this.id = id;
		this.title = title;
		this.pcontrolLevel = pcontrolLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ParentalControlLevel getPcontrolLevel() {
		return pcontrolLevel;
	}

	public void setPcontrolLevel(ParentalControlLevel pcontrolLevel) {
		this.pcontrolLevel = pcontrolLevel;
	}
	
	
}
