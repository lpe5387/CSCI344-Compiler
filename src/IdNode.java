/**
 * This class is responsible for the ID node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;
import java.util.ArrayList;

public class IdNode implements JottTree {
 
    private Token token;

    public IdNode(Token token){
        this.token = token;
    }

    /**
     * Function used to Parse an id into an IdNode for the parse tree.
     * @param tokenlist the list of tokens generated by the Tokenizer
     * @return IdNode
     * @throws SyntaxException
     */
    public static IdNode ParseId(ArrayList<Token> tokenlist) throws SyntaxException{
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.ID_KEYWORD){
            IdNode node = new IdNode(token);
            tokenlist.remove(0); 
            return node;
        }
        else{
            throw new SyntaxException("Expected an ID/Keyword, got "+token.getTokenType(), token.getFilename(), token.getLineNum());
        }

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
