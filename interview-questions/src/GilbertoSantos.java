import java.util.Arrays;

/**
 * 
 */

/**
 * @author gilbertopsantosjr
 *
 */
public class GilbertoSantos {
	
	public static void main(String[] args) {
		
		testShouldReturn2EqualsCombinations();
		testShouldReturnAnyEqualsCombinations();
		
		int target = 15;
		int [] inputs = {5, 5, 15, 10}; 
		
		int total_combinations  = calculate_combinations(target, inputs);
		
		// waiting for 3 combinations
		System.out.println( "total_combinations is: " + total_combinations );
		
	}
	
	public static int calculate_combinations( int target, int ... all ){
		int total = 0;
		Arrays.sort(all);
		for (int i = 0; i < all.length; i++) {
			
		}
		return total;
	}
	
	//testing 
	public static void testShouldReturn2EqualsCombinations() {
		
		int target = 20;
		int [] inputs = {20, 5, 4, 20}; 
		
		int total_combinations  = calculate_combinations(target, inputs);
		
		if (total_combinations < 2)
			throw new RuntimeException("Test fail !");
	}
	
	public static void testShouldReturnAnyEqualsCombinations() {
		
		int target = 6;
		int [] inputs = {1, 2, 3, 4}; 
		
		int total_combinations  = calculate_combinations(target, inputs);
		
		if (total_combinations < 3)
			throw new RuntimeException("Test fail !");
	}
	
}
