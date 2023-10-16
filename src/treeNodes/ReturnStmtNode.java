package treeNodes;

/**
 * This class is responsible for the return statement node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class ReturnStmtNode implements JottTree {

    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr){
        this.expr = expr;
    }

    public static ReturnStmtNode parseReturnStmt(ArrayList<Token> tokenList) throws SyntaxException {
        if(tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }
        Token first = tokenList.get(0);
        if(first.getToken().equals("return")) {
            tokenList.remove(0);
            ExprNode expr = ExprNode.parseExpr(tokenList);
            Token firstAfterExpr = tokenList.get(0);
            if(firstAfterExpr.getTokenType() == TokenType.SEMICOLON) { // if followed by ';', eat it
                tokenList.remove(0);
                return new ReturnStmtNode(expr);
            } else {
                throw new SyntaxException("Expected ';' Got: "+ firstAfterExpr.getToken(),
                        firstAfterExpr.getFilename(), firstAfterExpr.getLineNum());
            }
        } else {
            if(first.getTokenType() == TokenType.R_BRACE) {
                return null; // equivalent to epsilon in grammar
            } else {
                throw new SyntaxException("Expected Return Statement, Got: "+ first.getToken(),
                        first.getFilename(), first.getLineNum());
            }
        }
    }
    public String convertToJott() {
        return "return " + this.expr.convertToJott() + ";";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
