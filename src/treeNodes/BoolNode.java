package treeNodes;
/**
 * This class is responsible for the boolean node for the parse tree
 *
 * @author Luka Eaton, lucie lim, Issac Kim
 */


import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class BoolNode implements ExprNode {
    
    private Token token;

    public BoolNode(Token token){
        this.token = token;
    }

    /**
     * Function to parse a Boolean node for the parse tree.
     * @param tokenlist the list of tokens made from the Tokenizer
     * @return treeNodes.BoolNode
     * @throws SyntaxException
     */
    public static BoolNode parseBool(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);
        String tokenValue = token.getToken();
        if(token.getTokenType() == TokenType.ID_KEYWORD && (tokenValue.equals("True") || 
        tokenValue.equals("False"))){
            BoolNode node = new BoolNode(token);
            tokenlist.remove(0); 
            return node;
        }
        else{
            throw new SyntaxException("Expected a Boolean value, got "+token.getTokenType(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott() {
        return this.token.getToken();
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
