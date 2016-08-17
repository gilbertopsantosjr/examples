/**
 * Created by gilbertopsantosjr on 01/09/15.
 */
public class WellFormed {

    private static final String EMPTY = "";

    static boolean isWellFormed(String input)
    {
        String previous = "";
        while (input.length() !=  previous.length())
        {
            previous = input;
            input = input
                    .replace("()", EMPTY)
                    .replace("[]", EMPTY)
                    .replace("{}", EMPTY);
        }
        return (input.length() == 0);
    }

    public static void main (String args []) {
        String types [] = new String[4];
        types[0] = "[(])";
        types[1] = "({[()]})";
        types[2] = "[(])";
        types[3] = "[]{[]}";

        for (int i = 0; i < types.length; i++)
            System.out.println( (isWellFormed(types[i])) );

    }

}
