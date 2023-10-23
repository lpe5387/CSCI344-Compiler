package exceptions;

/**
 * This is the exception class that is thrown when there is a semantic error during compilation,
 * in the validation phase.
 *
 * @author Luka Eaton
 */
public class SemanticException extends Exception{

    private String errorMessage;
    private String filename;
    private int lineNum;

    public SemanticException(String errorMessage, String filename, int lineNum){
        this.errorMessage = errorMessage;
        this.filename = filename;
        this.lineNum = lineNum;
    }

    public SemanticException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){ return errorMessage;}

    public void printErrorMessage(){
        if(this.filename == null)
            System.err.println("Semantic Error:\n"+this.errorMessage);
        else
            System.err.println("Semantic Error:\n"+this.errorMessage+"\n"+this.filename+":"+this.lineNum+"\n");
    }

}