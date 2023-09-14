/**
 * This class is responsible for the ID node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

public class IdNode implements JottTree {
 
    private String value;

    public IdNode(String value){
        this.value = value;
    }

    /**
     * Function used to Parse an id into an IdNode for the parse tree.
     * @param tokenlist the list of tokens generated by the Tokenizer
     */
    public static IdNode ParseId(ArrayList<Token> tokenlist){
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.ID_KEYWORD){
            IdNode node = new IdNode(token.getToken());
            tokenlist.remove(0); 
            return node;
        }
        else{
            // throw new SyntaxException();
            return null;
        }

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
