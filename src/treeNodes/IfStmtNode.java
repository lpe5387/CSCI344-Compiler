package treeNodes;

/**
 * This class is responsible for the if statement node for the parse tree
 *
 * @author Luka Eaton, Andrew Dantone
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.Objects;

public class IfStmtNode implements JottTree, BodyStmtNode {

    private ExprNode expr;

    private BodyNode body;

    private ArrayList<ElseIfNode> elseIfLst;
    private ElseNode elseStmt;

    //
    //IMPORTANT NOTE: ElseIfLst and elseStmt CAN BE EMPTY AND NULL RESPECTIVELY, THESE CASES MUST BE ACCOUNTED FOR
    //
    public IfStmtNode(ExprNode expr, BodyNode body, ArrayList<ElseIfNode> elseIfLst, ElseNode elseStmt){
        this.expr = expr;
        this.body = body;
        this.elseIfLst = elseIfLst;
        this.elseStmt = elseStmt;
    }

    public static IfStmtNode parseIfStmt(ArrayList<Token> tokenlist) throws SyntaxException {
        //
        //Testing the first token for the word if
        //
        Token tok = tokenlist.get(0); //grab the token
        if(tok.getTokenType() != TokenType.ID_KEYWORD){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a ID KEYWORD, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "if")){ //check if first token is the word if
            throw new SyntaxException("Expected the word \"if\", got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //if we got here that means this is an if statement so start parsing and removing tokens
        //
        tokenlist.remove(0);
        //
        //repeat step 1 to check for [
        //
        if(tok.getTokenType() != TokenType.L_BRACKET){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a L BRACKET, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "[")){ //check if first token is the word if
            throw new SyntaxException("Expected a [, got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //now we throw away the brace and continue parsing
        //
        tokenlist.remove(0);
        ExprNode expr = ExprNode.parseExpr(tokenlist); //grab the next node as an expression node
        //
        //repeat step 1 to check for ]
        //
        if(tok.getTokenType() != TokenType.R_BRACKET){ //if not an ID_KEYWORD, reject
            throw new SyntaxException("Expected a R BRACKET, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
        }
        if(!Objects.equals(tok.getToken(), "]")){ //check if first token is the word if
            throw new SyntaxException("Expected a ], got: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
        }
        //
        //now we throw away the brace and continue parsing
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
        //now we throw away the brace and continue parsing
        //
        tokenlist.remove(0);
        //
        //now we have any amount of else ifs, so we loop until we can no longer parse an else if
        //
        ArrayList<ElseIfNode> elseIfList = new ArrayList<ElseIfNode>();
        boolean looping = true;
        while(looping) {
            try {
                ElseIfNode elseIf = ElseIfNode.parseElseIf(tokenlist);
                elseIfList.add(elseIf);
            } catch (SyntaxException e) {
                //
                //if the message contains either of those phrases it means the token type was wrong,
                //so we can continue parsing since that's expected,
                //the if block throws the exception if it doesn't since that means there was an actual error
                //
                String eMsg = e.getErrorMessage();
                if (!eMsg.contains("Expected a ID KEYWORD,") | !eMsg.contains("Expected the word \"elseif\",")){
                    throw e;
                }
                looping = false;
            }
        }
        //
        //done with else if loops, grab the final else loop if possible
        //
        ElseNode finalElse = null;
        try{
            finalElse = ElseNode.parseElse(tokenlist);
        }
        catch (SyntaxException e){
            //
            //if the message contains either of those phrases it means the token type was wrong,
            //so we can continue parsing since that's expected, finalElse is allowed to be null,
            //the if block throws the exception if it doesn't since that means there was an actual error
            //
            String eMsg = e.getErrorMessage();
            if (!eMsg.contains("Expected a ID KEYWORD,") | !eMsg.contains("Expected the word \"else\",")){
                throw e;
            }
        }
        //
        //Now make and return the node
        //
        return new IfStmtNode(expr, body, elseIfList, finalElse);
    }

    public String convertToJott(){return "";} //TODO

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree(){return true;}

}
