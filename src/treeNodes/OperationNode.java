package treeNodes;
/**
 * This class is intended to hold any operation with a left and right operand.
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class OperationNode implements JottTree, ExprNode {

    private ExprNode left;
    private OpNode op;
    private ExprNode right;

    public OperationNode(ExprNode left, OpNode op, ExprNode right){
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public static OperationNode parseOperation(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);
        Token token1 = tokenlist.get(1);
        ExprNode left;
        if(token.getTokenType() == TokenType.ID_KEYWORD) left = IdNode.parseId(tokenlist);
        else if(token.getTokenType() == TokenType.NUMBER) left = NumNode.parseNum(tokenlist);
        else if(token.getTokenType() == TokenType.FC_HEADER) left = FuncCallNode.parseFuncCall(tokenlist);
        else if(token.getTokenType() == TokenType.MATH_OP && token1.getTokenType() == TokenType.NUMBER){
            Token signedNum = new Token(token.getToken()+token1.getToken(), token1.getFilename(), token1.getLineNum(), TokenType.NUMBER);
            tokenlist.remove(0);
            tokenlist.remove(0);
            left = new NumNode(signedNum);
        }
        else throw new SyntaxException("Expected an Id, Number, or Function call. Got: "+token.getToken(), token.getFilename(), token.getLineNum());
        OpNode op = OpNode.parseOp(tokenlist);
        ExprNode right = ExprNode.parseExpr(tokenlist);
        return new OperationNode(left, op, right);
    }

    public String convertToJott(){
        return ExprNode.convertToJott(this.left) + this.op.convertToJott() + ExprNode.convertToJott(this.right);
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree(){return true;}

}
