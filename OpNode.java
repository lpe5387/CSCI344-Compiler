/**
 * This class is responsible for the operation node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

public class OpNode {

    private String value;

    public OpNode(String value){
        this.value = value;
    }

    public static OpNode ParseOp(ArrayList<Token> tokenlist){
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.REL_OP){
            OpNode node = new OpNode(token.getToken());
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
