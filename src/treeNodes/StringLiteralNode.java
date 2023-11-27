package treeNodes;

/**
 * This class is responsible for the string literal node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class StringLiteralNode implements ExprNode {

    private String text;
    private Token token;

    public StringLiteralNode(Token token){
        this.text = token.getToken();
        this.token = token;
    }

    public static StringLiteralNode parseStringLiteral(ArrayList<Token> tokenlist) throws SyntaxException{
        if (!tokenlist.isEmpty()) {
            Token tok = tokenlist.get(0); //grab the token
            if (tok.getTokenType() != TokenType.STRING) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a STRING, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            //its a string to pop the token and make the node
            tokenlist.remove(0);
            return new StringLiteralNode(tok);
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
    }

    public String convertToJott(){
        return this.text;
    }

    public String convertToJava(String className){ return this.text; }

    public String convertToC(){return this.text;}

    public String convertToPython(){ return this.text; }
    
    public boolean validateTree(){return true;}

    public boolean isBooleanExpression() throws SemanticException {
        return false;
    }

    public String evaluateType() throws SemanticException {
        return "String";
    }
}
