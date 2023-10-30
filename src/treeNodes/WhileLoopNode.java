package treeNodes;

/**
 * This class is responsible for the while loop node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class WhileLoopNode implements BodyStmtNode {

    private ExprNode expr;
    private BodyNode body;
    private Token whileLoopStart;

    public WhileLoopNode(ExprNode expr, BodyNode body, Token whileLoopStart){
        this.expr = expr;
        this.body = body;
        this.whileLoopStart = whileLoopStart;
    }

    public static WhileLoopNode parseWhileLoop (ArrayList<Token> tokenlist) throws SyntaxException, SemanticException {
        ExprNode exprNode = null;
        BodyNode bodyNode = null;
        Token whileLoopStart = null;

        //check if the tokenlist is not empty
        if (tokenlist.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        Token token = tokenlist.get(0);

        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            if (token.getToken().equals("while")) {
                whileLoopStart = tokenlist.get(0);
                tokenlist.remove(0);

                if (tokenlist.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }
                // start of [
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.L_BRACKET) {
                    throw new SyntaxException("Expected a [ Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                exprNode = ExprNode.parseExpr(tokenlist);

                if (tokenlist.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }
                // end of ]
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.R_BRACKET) {
                    throw new SyntaxException("Expected a ] Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                if (tokenlist.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }

                // start of {
                token = tokenlist.get(0);
                if (token.getTokenType() != TokenType.L_BRACE) {
                    throw new SyntaxException("Expected a { Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenlist.remove(0);

                bodyNode = BodyNode.parseBody(tokenlist);

                if (tokenlist.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }

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
        return new WhileLoopNode(exprNode, bodyNode, whileLoopStart);
    }

    public String convertToJott(){
         return "while [ " + this.expr.convertToJott() + " ] { " + this.body.convertToJott() + " }";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree() throws SemanticException {
        if(!this.expr.isBooleanExpression()){
            throw new SemanticException("While loop condition is not a valid boolean expresion",
                    this.whileLoopStart.getFilename(),
                    this.whileLoopStart.getLineNum());
        }
        this.expr.validateTree();
        this.body.validateTree();
        return true;
    }


}
