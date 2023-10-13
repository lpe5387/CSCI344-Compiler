package treeNodes;

/**
 * This interface is responsible for the expression node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import exceptions.SyntaxException;
import provided.TokenType;

public interface ExprNode extends JottTree {

    public static ExprNode parseExpr(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(1);
        ExprNode expr;
        // if the second token is a math op / rel op, we are dealing with an operation
        if(token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP){
            expr = OperationNode.parseOperation(tokenlist);
        }
        token = tokenlist.get(0);
        Token token1 = tokenlist.get(1);
        if(token.getToken().equals("True") || token.getToken().equals("False")) expr = BoolNode.parseBool(tokenlist);
        else if(token.getTokenType() == TokenType.ID_KEYWORD) expr = IdNode.parseId(tokenlist);
        else if(token.getTokenType() == TokenType.NUMBER) expr = NumNode.parseNum(tokenlist);
        else if(token.getTokenType() == TokenType.STRING) expr = StringLiteralNode.parseStringLiteral(tokenlist);
        else if(token.getTokenType() == TokenType.FC_HEADER) expr = FuncCallNode.parseFuncCall(tokenlist);
        else throw new SyntaxException("Expected an Id, Number, Function call, Boolean, or String. Got: "+token.getToken(), token.getFilename(), token.getLineNum());
        return expr;
    }

}
