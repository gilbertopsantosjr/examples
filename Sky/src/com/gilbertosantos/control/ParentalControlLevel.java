package com.gilbertosantos.control;

/**
 * 25/01/16
 * @author Gilberto Santos
 */
public enum ParentalControlLevel {

	ADULT(18), CHILD(2), FAMILY(0);
	
	private int level;
	
	ParentalControlLevel(int value){
		level = value;
	}

	public int getLevel() {
		return level;
	}
	
}
