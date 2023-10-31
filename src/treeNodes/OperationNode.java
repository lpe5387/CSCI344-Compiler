package treeNodes;
/**
 * This class is intended to hold any operation with a left and right operand.
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class OperationNode implements ExprNode {

    private ExprNode left;
    private OpNode op;
    private ExprNode right;

    public OperationNode(ExprNode left, OpNode op, ExprNode right){
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public static OperationNode parseOperation(ArrayList<Token> tokenlist, ExprNode funcCall) throws SyntaxException {
        if(!tokenlist.isEmpty()) {
            Token token = tokenlist.get(0);
            ExprNode left;
            if(funcCall != null) left = funcCall;
            else if (token.getTokenType() == TokenType.ID_KEYWORD) left = IdNode.parseId(tokenlist);
            else if (token.getTokenType() == TokenType.NUMBER) left = NumNode.parseNum(tokenlist);
            else if (token.getTokenType() == TokenType.FC_HEADER) left = FuncCallNode.parseFuncCall(tokenlist);
            else
                throw new SyntaxException("Expected an Id, Number, or Function call. Got: " + token.getToken(), token.getFilename(), token.getLineNum());
            OpNode op = OpNode.parseOp(tokenlist);
            ExprNode right = ExprNode.parseExpr(tokenlist);
            return new OperationNode(left, op, right);
        }
        else throw new SyntaxException("Unexpected end of file");
    }

    public String convertToJott(){
        return this.left.convertToJott() + this.op.convertToJott() + this.right.convertToJott();
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree(){

        return true;
    }

    public ExprNode getRight() {
        return this.right;
    }

    public OpNode getOp() {
        return this.op;
    }

    public boolean isBooleanExpression() throws SemanticException {
        int relOpCount = 0;
        OperationNode operationNode = this;
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
        // if relOpCount > 1, this isn't a valid expression
        if( relOpCount > 1 ) throw new SemanticException("Operation expression cannot contain multiple REL_OP's.", this.op.getToken().getFilename(), this.op.getToken().getLineNum());
        // if relOpCount == 1, we know this is a proper boolean expression
        if ( relOpCount != 1 ) return false;
        return true;
    }
}
