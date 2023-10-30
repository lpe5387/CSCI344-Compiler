package treeNodes;

/**
 * This interface is responsible for the expression node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

import exceptions.SemanticException;
import provided.JottTree;
import provided.Token;
import exceptions.SyntaxException;
import provided.TokenType;

public interface ExprNode extends JottTree {

    public static ExprNode parseExpr(ArrayList<Token> tokenlist) throws SyntaxException {
        if(tokenlist.size() >= 2) {
            Token token = tokenlist.get(1);
            ExprNode expr;
            // if the second token is a math op / rel op, we are dealing with an operation
            if (token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP) {
                expr = OperationNode.parseOperation(tokenlist, null);
                return expr;
            }
        }
        if(!tokenlist.isEmpty()) {
            ExprNode expr;
            Token token = tokenlist.get(0);
            if (token.getToken().equals("True") || token.getToken().equals("False"))
                expr = BoolNode.parseBool(tokenlist);
            else if (token.getTokenType() == TokenType.ID_KEYWORD) expr = IdNode.parseId(tokenlist);
            else if (token.getTokenType() == TokenType.NUMBER) expr = NumNode.parseNum(tokenlist);
            else if (token.getTokenType() == TokenType.STRING) expr = StringLiteralNode.parseStringLiteral(tokenlist);
            else if (token.getTokenType() == TokenType.FC_HEADER){
                expr = FuncCallNode.parseFuncCall(tokenlist);
                if(!tokenlist.isEmpty()){
                    token = tokenlist.get(0);
                    if(token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP){
                        expr = OperationNode.parseOperation(tokenlist, expr);
                    }
                }
            }
            else
                throw new SyntaxException("Expected an Id, Number, Function call, Boolean, or String. Got: " + token.getToken(), token.getFilename(), token.getLineNum());
            return expr;
        }
        else
            throw new SyntaxException("Unexpected end of file");
    }

    /**
     * Will check if the node is a boolean expression
     * @return true if boolean expression, false otherwise
     */
    public boolean isBooleanExpression() throws SemanticException;

}
