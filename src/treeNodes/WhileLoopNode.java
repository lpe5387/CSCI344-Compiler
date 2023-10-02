package treeNodes;

/**
 * This class is responsible for the while loop node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class WhileLoopNode implements JottTree, BodyStmtNode {

    /**
     * Idk what to do about the brackets, do i keep the private attributes of it?
     * I think no because it seems very inefficient and repetitive
     */

    private ExprNode expr;
    private BodyNode body;

    public WhileLoopNode(ExprNode expr, BodyNode body){
        this.expr = expr;
        this.body = body;
    }

    public static WhileLoopNode parseWhileLoop (ArrayList<Token> tokenlist) throws SyntaxException {
        ExprNode exprNode = null;
        BodyNode bodyNode = null;
        Token token = tokenlist.get(0);

        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            if (token.getToken().equals("while")) {
                tokenlist.remove(0);

                // start of [
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.L_BRACKET) {
                    throw new SyntaxException("Expected a [ Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                exprNode = ExprNode.parseExpr(tokenlist);

                // end of ]
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.R_BRACKET) {
                    throw new SyntaxException("Expected a ] Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                // start of {
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.L_BRACE) {
                    throw new SyntaxException("Expected a { Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                bodyNode = BodyNode.parseBodyNode(tokenlist);

                // end of }
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.R_BRACE) {
                    throw new SyntaxException("Expected a } Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);
            } else throw new SyntaxException("Expected 'while' Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());
        }
        return new WhileLoopNode(exprNode, bodyNode);
    }

    public String convertToJott(){
         return "while [ " + this.expr.convertToJott() + " ] { " + this.body.convertToJott() + " }";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
