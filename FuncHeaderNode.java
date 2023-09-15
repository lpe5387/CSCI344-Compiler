/**
 * This class is responsible for the number node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

public class FuncHeaderNode {

    private String value;

    public FuncHeaderNode(String value){
        this.value = value;
    }

    public static FuncHeaderNode ParseFuncHeader(ArrayList<Token> tokenlist) throws SyntaxException{
        Token token1 = tokenlist.get(0);
        Token token2 = tokenlist.get(1);
        if(token1.getToken().equals(":") && token2.getToken().equals(":")){
            FuncHeaderNode node = new FuncHeaderNode("::");
            tokenlist.remove(0);
            tokenlist.remove(0);
            return node;
        }
        else{
            throw new SyntaxException("Expected '::', got "+token1.getToken()+token2.getToken(), token1.getFilename(), token1.getLineNum());
        }
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
