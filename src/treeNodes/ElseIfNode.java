package treeNodes;

/**
 * This class is responsible for the else if node for the parse tree
 *
 * @author Luka Eaton, Andrew Dantone
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.Objects;

public class ElseIfNode implements JottTree {

    private ExprNode expr;

    private BodyNode body;
    private Token elseIfStart;

    public ElseIfNode(ExprNode expr, BodyNode body, Token elseIfStart){
        this.expr = expr;
        this.body = body;
        this.elseIfStart = elseIfStart;
    }

    public static ElseIfNode parseElseIf(ArrayList<Token> tokenlist) throws SyntaxException{
        Token tok;
        Token elseIfStart;
        //
        //Testing the first token for the word elseif
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the token
            if (tok.getTokenType() != TokenType.ID_KEYWORD) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a ID KEYWORD, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "elseif")) { //check if first token is the word if
                throw new SyntaxException("Expected the word \"elseif\", got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
            elseIfStart = tokenlist.get(0);
            //
            //if we got here that means this is an elseif statement so start parsing and removing tokens
            //
            tokenlist.remove(0);
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
        //
        //repeat step 1 to check for [
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the next token
            if (tok.getTokenType() != TokenType.L_BRACKET) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a L BRACKET, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "[")) { //check if first token is the word if
                throw new SyntaxException("Expected a [, got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
            //
            //now we throw away the brace and continue parsing
            //
            tokenlist.remove(0);
        }
        else{
            throw new SyntaxException("Unexpected End Of File");
        }
        ExprNode expr = ExprNode.parseExpr(tokenlist); //grab the next node as an expression node
        //
        //repeat step 1 to check for ]
        //
        if(!tokenlist.isEmpty()) {
            tok = tokenlist.get(0); //grab the next token
            if (tok.getTokenType() != TokenType.R_BRACKET) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a R BRACKET, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "]")) { //check if first token is the word if
                throw new SyntaxException("Expected a ], got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
            //
            //now we throw away the brace and continue parsing
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
            if (tok.getTokenType() != TokenType.L_BRACE) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a L BRACE, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            if (!Objects.equals(tok.getToken(), "{")) { //check if first token is the word if
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
        return new ElseIfNode(expr, body, elseIfStart);
    }

    public String convertToJott(){
        String str = "elseif["; //starting elseif
        str += this.expr.convertToJott(); //condition for the elseif
        str += "]{\n"; //end if start body
        str += this.body.convertToJott(); //body statement
        str += "}\n"; //end body
        return str;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree() throws SemanticException {
        if(!this.expr.isBooleanExpression()){
            throw new SemanticException("While loop condition is not a valid boolean expresion",
                    this.elseIfStart.getFilename(),
                    this.elseIfStart.getLineNum());
        }
        this.expr.validateTree();
        this.body.validateTree();
        return true;
    }

}
