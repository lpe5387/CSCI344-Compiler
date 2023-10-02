package treeNodes;

/**
 * This class is responsible for the else node for the parse tree
 *
 * @author Luka Eaton, Andrew Dantone
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.Objects;

public class ElseNode implements JottTree {

    private BodyNode body;

    public ElseNode(BodyNode body){
        this.body = body;
    }

    public static ElseNode parseElse(ArrayList<Token> tokenlist) throws SyntaxException{
        //
        //Testing the first token for the word else
        //
        Token tok = tokenlist.get(0); //grab the token
        if(tok.getTokenType() != TokenType.ID_KEYWORD){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a ID KEYWORD, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "else")){ //check if first token is the word if
            throw new SyntaxException("Expected the word \"else\", got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //if we got here that means this is an else statement so start parsing and removing tokens
        //
        tokenlist.remove(0);
        //
        //repeat step 1 to check for {
        //
        if(tok.getTokenType() != TokenType.L_BRACE){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a L BRACE, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "{")){ //check if first token is the word if
            throw new SyntaxException("Expected a {, got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //now we throw away the brace and continue parsing
        //
        tokenlist.remove(0);
        BodyNode body = BodyNode.parseBody(tokenlist); //grab node as a body node
        //
        //repeat step 1 to check for }
        //
        if(tok.getTokenType() != TokenType.R_BRACE){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a R BRACE, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "}")){ //check if first token is the word if
            throw new SyntaxException("Expected a }, got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //now we throw away the brace and make the node
        //
        tokenlist.remove(0);
        return new ElseNode(body);
    }

    public String convertToJott(){return "";} //TODO

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
