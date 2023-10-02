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
        ExprNode left;
        if(token.getTokenType() == TokenType.ID_KEYWORD) left = IdNode.ParseId(tokenlist);
        else if(token.getTokenType() == TokenType.NUMBER) left = NumNode.ParseNum(tokenlist);
        else if(token.getTokenType() == TokenType.FC_HEADER) left = FuncCallNode.ParseFuncCall(tokenlist);
        else throw new SyntaxException("Expected an Id, Number, or Function call. Got: "+token.getToken(), token.getFilename(), token.getLineNum());
        OpNode op = OpNode.ParseOp(tokenlist);
        ExprNode right = ExprNode.ParseExpr(tokenlist);
        return new OperationNode(left, op, right);
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree(){return true;}

}