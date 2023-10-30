package treeNodes;

/**
 * This class is responsible for the number node for the parse tree
 *
 * @author Luka Eaton, lucie lim, Dara Prak
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class NumNode implements ExprNode {

    private Token token;

    public NumNode(Token token){
        this.token = token;
    }

    public static NumNode parseNum(ArrayList<Token> tokenList) throws SyntaxException {
        if(tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }
        Token first = tokenList.get(0);
        if(first.getTokenType() != TokenType.NUMBER) {
            throw new SyntaxException("Expected a number, Got: "+ first.getToken(), first.getFilename(), first.getLineNum());
        }
        tokenList.remove(0);
        return new NumNode(first);
    }

    public boolean isBooleanExpression() throws SemanticException{
        return false;
    }

    public String convertToJott() {
        return this.token.getToken();
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
