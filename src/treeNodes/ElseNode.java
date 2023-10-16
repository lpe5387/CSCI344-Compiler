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
        Token tok;
        //
        //Testing the first token for the word else
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the token
            if (tok.getTokenType() != TokenType.ID_KEYWORD) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a ID KEYWORD, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "else")) { //check if first token is the word if
                throw new SyntaxException("Expected the word \"else\", got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
            //
            //if we got here that means this is an else statement so start parsing and removing tokens
            //
            tokenlist.remove(0);
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
        //
        //repeat step 1 to check for {
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the next token
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
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
        BodyNode body = BodyNode.parseBody(tokenlist); //grab node as a body node
        //
        //repeat step 1 to check for }
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the next token
            if (tok.getTokenType() != TokenType.R_BRACE) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a R BRACE, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "}")) { //check if first token is the word if
                throw new SyntaxException("Expected a }, got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
            //
            //now we throw away the brace and make the node
            //
            tokenlist.remove(0);
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
        return new ElseNode(body);
    }

    public String convertToJott(){
        String str = "else{\n";
        str += this.body.convertToJott();
        str += "}\n";
        return str;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
