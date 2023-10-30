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
        //1st checks if there is an boolean expression in the while loop
        //expression node can be a id (boolean variable), boolean (true / false), function call, operation node that
        //has a rel op
        if (this.expr instanceof BoolNode) {
            //expression node can be boolean (true / false)
        } else if (this.expr instanceof IdNode) {
            //expression node can be a id (boolean variable)
            //check is this idnode is a boolean variable in the symbol table
        } else if (this.expr instanceof FuncCallNode) {
            //function call
            //check symbol table for the return value of the funccallnode: should be a bool return
        } else if (this.expr instanceof OperationNode) {
            //operation node that has a rel op
            OperationNode operationNode = (OperationNode)this.expr;
            //had to be exactly 1 count
            int relOpCount = 0;
            while (true) {
                if (operationNode.getOp().getToken().getTokenType() == TokenType.REL_OP) {
                    relOpCount++;
                }
                if (operationNode.getRight() instanceof OperationNode) {
                    operationNode = (OperationNode) operationNode.getRight();
                } else {
                    break;
                }
            }

            if ( relOpCount != 1 ) {
                throw new SemanticException("While loop condition is not a boolean expresion",
                        operationNode.getOp().getToken().getFilename(),
                        operationNode.getOp().getToken().getLineNum());
            }

        } else {
            throw new SemanticException("While loop condition is not a boolean expresion",
                    this.whileLoopStart.getFilename(),
                    this.whileLoopStart.getLineNum());
        }

        this.expr.validateTree();
        this.body.validateTree();

        return true;
    }


}
