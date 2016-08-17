package com.gilbertosantos;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import com.gilbertosantos.model.Snake;

/**
 * @author Gilberto Santos 
 */
public class Principal {

	public static void main(String[] args) {
		try {
			askForSSESFF(args);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			
		} finally {
			System.exit(0);
		}	
	}
	
	private static void askForSSESFF(String[] args) {
		try {
			if(args.length >= 2){
				final String CSV = String.valueOf(args[0]);
				
				if(!CSV.contains(".csv"))
					throw new RuntimeException("Provide a valid csv file.");
				
				final Integer MRPS = Integer.valueOf(args[1]);
				
				if(MRPS < 0)
					throw new RuntimeException("MRPS can't be less then 0");
				
				Functions func = new Functions(CSV);
				func.createSnakes();
				
				final Set<Snake> ensemble = func.getAll();
				System.out.println();
				
				doSoundFromSnake(ensemble, MRPS);
				
			}else
				System.out.println(" Please provide a valid filename and MRPS");
			
		} catch (ClassCastException e) {
			throw new RuntimeException("Provide a valid file name.");

		} catch (NumberFormatException e) {
			throw new RuntimeException("Provide a valid number of MRPS.");

		} catch (FileNotFoundException e) {
			throw new RuntimeException("Provide a valid path to CSV file.");
			
		} catch (RuntimeException e){
			e.printStackTrace();
			throw e;
			
		} catch (Exception e) {
			throw new RuntimeException("Something wrong, please restart the program.");
		}

	}

	private static void doSoundFromSnake(Set<Snake> ensemble, int mrps) {
		for (Snake snake : ensemble) {
			System.out.println(snake);
		}
	}
	
}
