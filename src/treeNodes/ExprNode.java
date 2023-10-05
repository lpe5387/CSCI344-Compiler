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
        else if(token.getTokenType() == TokenType.MATH_OP && token1.getTokenType() == TokenType.NUMBER){
            Token signedNum = new Token(token.getToken()+token1.getToken(), token1.getFilename(), token1.getLineNum(), TokenType.NUMBER);
            tokenlist.remove(0);
            tokenlist.remove(0);
            expr = new NumNode(signedNum);
        }
        else throw new SyntaxException("Expected an Id, Number, Function call, Boolean, or String. Got: "+token.getToken(), token.getFilename(), token.getLineNum());
        return expr;
    }

    //TODO: make a convert to jott funct
    public static String convertToJott(ExprNode expr){
        if(expr instanceof IdNode){
            IdNode id = (IdNode)expr;
            return id.convertToJott();
        }
        else if(expr instanceof NumNode){
            NumNode num = (NumNode)expr;
            return num.convertToJott();
        }
        else if(expr instanceof BoolNode){
            BoolNode bool = (BoolNode)expr;
            return bool.convertToJott();
        }
        else if(expr instanceof FuncCallNode){
            FuncCallNode funcCall = (FuncCallNode) expr;
            return funcCall.convertToJott();
        }
        else if(expr instanceof StringLiteralNode){
            StringLiteralNode stringLiteral = (StringLiteralNode) expr;
            return stringLiteral.convertToJott();
        }
        else{
            OperationNode operation = (OperationNode)expr;
            return operation.convertToJott();
        }
    }

}
