/**
 * Created by gilbertopsantosjr on 01/09/15.
 */
public class PalindromeNumber {


    private static boolean isPalindrome(int number) {
        int palindrome = number;
        int reverse = 0;
        while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
        }
        return number == reverse;
    }

    private static String reverse(String original){
        String reverse = "";
        int length = original.length();
        for ( int i = length - 1; i >= 0; i-- )
            reverse = reverse + original.charAt(i);
        return reverse;
    }

    private static int getNextNumber(int number) {
        if ( ! isPalindrome( number ) )
            return Integer.valueOf( reverse( String.valueOf(number) ) );
        else
            return getNextNumber( number + 1 );
    }

    public static void main (String [] args){
        int [] initial_number = new int[4];

        initial_number[0] = 195;
        initial_number[1] = 786;
        initial_number[2] = 1473;
        initial_number[3] = 5214;

        for (int i = 0; i < initial_number.length; i++){
            System.out.println("initial number:" + initial_number[i]);
            int inverted = getNextNumber(initial_number[i]);

            System.out.println("inverted number:" + inverted);
            int total = ( initial_number[i] + inverted);

            System.out.println("total:" + total);
        }

    }

}
