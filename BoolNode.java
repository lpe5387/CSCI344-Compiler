/**
 * This class is responsible for the boolean node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

public class BoolNode {
    
    private String value;

    public BoolNode(String value){
        this.value = value;
    }

    /**
     * Function to parse a Boolean node for the parse tree.
     * @param tokenlist the list of tokens made from the Tokenizer
     * @return BoolNode
     */
    public static BoolNode ParseBool(ArrayList<Token> tokenlist){
        Token token = tokenlist.get(0);
        String tokenValue = token.getToken();
        if(token.getTokenType() == TokenType.ID_KEYWORD && (tokenValue.equals("True") || 
        tokenValue.equals("False"))){
            BoolNode node = new BoolNode(token.getToken());
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
