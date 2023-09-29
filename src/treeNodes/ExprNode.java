package treeNodes;

/**
 * This interface is responsible for the expression node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;
import provided.Token;
import exceptions.SyntaxException;
import provided.TokenType;

public interface ExprNode {

    public static ExprNode ParseExpr(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(1);
        ExprNode expr;
        // if the second token is a math op / rel op, we are dealing with an operation
        if(token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP){
            expr = OperationNode.parseOperation(tokenlist);
        }
        token = tokenlist.get(0);
        if(token.getToken().equals("True") || token.getToken().equals("False")) expr = BoolNode.ParseBool(tokenlist);
        else if(token.getTokenType() == TokenType.ID_KEYWORD) expr = IdNode.ParseId(tokenlist);
        else if(token.getTokenType() == TokenType.NUMBER) expr = NumNode.ParseNum(tokenlist);
        else if(token.getTokenType() == TokenType.STRING) expr = StringLiteralNode.ParseStringLiteral(tokenlist);
        else if(token.getTokenType() == TokenType.FC_HEADER) expr = FuncCallNode.ParseFuncCall(tokenlist);
        else throw new SyntaxException("Expected an Id, Number, Function call, Boolean, or String. Got: "+token.getToken(), token.getFilename(), token.getLineNum());
        return expr;
    }

}
