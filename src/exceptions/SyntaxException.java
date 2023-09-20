package exceptions;

/**
 * This is the exception class that is thrown when there is a syntax error during compilation, in the tokenizing
 * or parsing phases.
 *
 * @author Dara Prak
 */
public class SyntaxException extends Exception{
    public SyntaxException(String errorMessage, String filename, int lineNum){
        super("Syntax Error:\n"+errorMessage+"\n"+filename+":"+lineNum+"\n");
    }
}
