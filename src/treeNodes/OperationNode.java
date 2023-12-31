package treeNodes;
/**
 * This class is intended to hold any operation with a left and right operand.
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
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

    public String convertToJava(String className){
        return this.left.convertToJava(className) + " " + this.op.convertToJava(className) + " " +
                this.right.convertToJava(className);
    }

    public String convertToC() throws SemanticException {return this.left.convertToC() + " " + this.op.convertToC() + " " +
            this.right.convertToC();}

    public String convertToPython(){
        return this.left.convertToPython() + " " + this.op.convertToPython() + " " +
                this.right.convertToPython();
    }

    public boolean validateTree() throws SemanticException {
        this.left.validateTree();
        this.op.validateTree();
        this.right.validateTree();
        // Don't need to store the return of evaluateType(), if it doesn't throw a SemanticException we are all good
        evaluateType();
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

    public String evaluateType() throws SemanticException {
        String left = this.left.evaluateType();
        String right = this.right.evaluateType();
        if(left.equals(right)){
            if(isBooleanExpression()) return "Boolean";
            return left;
        }
        else throw new SemanticException("Types '" + left + "' and '" + right + "' cannot be in the same expression.", this.op.getToken().getFilename(), this.op.getToken().getLineNum());
    }

}
