/**
 *  This is the main file to run the program
 * 
 *  @author: Lucie Lim, 
 */

public class JottMain {
    public static void main (String[] args) {
        try {
            JottTokenizer.tokenize(args[1]);
         } catch (Exception exception) {
             //syntax error, invalid token "!", filename.jott: lineNum
             // 
             // TODO print the exception being sent from the jott tokenizer
             // 
             //System.out.println();
         }
    }    
}
