import java.util.ArrayList;
import provided.Token;
import provided.JottTokenizer;

/**
 *  This is the main file to run the program
 * 
 *  @author: Lucie Lim, 
 */

public class JottMain {
    public static void main (String[] args) {
        try {
            ArrayList <Token> tokenList = JottTokenizer.tokenize(args[1]);
            System.out.println(tokenList.toString());
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }    
}
