/**
 * This class is responsible for the number node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

import java.util.ArrayList;

public class FuncHeaderNode implements JottTree {

    private Token token;

    public FuncHeaderNode(Token token){
        this.token = token;
    }

    public static FuncHeaderNode ParseFuncHeader(ArrayList<Token> tokenlist) throws SyntaxException{
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.FC_HEADER){
            FuncHeaderNode node = new FuncHeaderNode(token);
            tokenlist.remove(0);
            tokenlist.remove(0);
            return node;
        }
        else{
            throw new SyntaxException("Expected '::', got "+token.getToken(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
