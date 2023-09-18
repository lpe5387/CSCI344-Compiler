public class SyntaxException extends Exception{
    public SyntaxException(String errorMessage, String filename, int lineNum){
        super("Syntax Error:\n"+errorMessage+"\n"+filename+":"+lineNum+"\n");
    }
}
