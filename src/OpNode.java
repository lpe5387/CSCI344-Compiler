/**
 * This class is responsible for the operation node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

public class OpNode implements JottTree {

    private Token token;

    public OpNode(Token token){
        this.token = token;
    }

    /**
     * Function to parse a relational operator for the parse tree.
     * @param tokenlist the list of tokens generated by the Tokenizer
     * @return OpNode
     * @throws SyntaxException
     */
    public static OpNode ParseOp(ArrayList<Token> tokenlist) throws SyntaxException{
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.REL_OP){
            OpNode node = new OpNode(token);
            tokenlist.remove(0); 
            return node;
        }
        else{
            throw new SyntaxException("Expected a Relational Operator, got "+token.getToken(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
