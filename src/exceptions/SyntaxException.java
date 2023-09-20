package exceptions;

/**
 * This is the exception class that is thrown when there is a syntax error during compilation, in the tokenizing
 * or parsing phases.
 *
 * @author Dara Prak, Luka Eaton
 */
public class SyntaxException extends Exception{

    private String errorMessage;
    private String filename;
    private int lineNum;

    public SyntaxException(String errorMessage, String filename, int lineNum){
        this.errorMessage = errorMessage;
        this.filename = filename;
        this.lineNum = lineNum;
    }

    public void printErrorMessage(){
        System.out.println("Syntax Error:\n"+this.errorMessage+"\n"+this.filename+":"+this.lineNum+"\n");
    }

}
