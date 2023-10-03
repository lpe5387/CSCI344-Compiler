package treeNodes;

/**
 * This class is responsible for the string literal node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class StringLiteralNode implements JottTree, ExprNode {

    private String text;
    private Token token;

    public StringLiteralNode(Token token){
        this.text = token.getToken();
        this.token = token;
    }

    public static StringLiteralNode parseStringLiteral(ArrayList<Token> tokenlist) throws SyntaxException{
        Token tok = tokenlist.get(0); //grab the token
        if(tok.getTokenType() != TokenType.STRING){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a STRING, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        //its a string to pop the token and make the node
        tokenlist.remove(0);
        return new StringLiteralNode(tok);
    }

    public String convertToJott(){
        return this.text;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
