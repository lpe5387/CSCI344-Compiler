package treeNodes;
/*
This class is for the FuncCallOpExpr subset of expression node

Author: Luka Eaton
 */
public class FuncCallOpExprNode extends ExprNode{

    private FuncCallNode funcCall;
    private OpNode op;
    private ExprNode expr;

    public FuncCallOpExprNode(FuncCallNode funcCall, OpNode op, ExprNode expr){
        this.funcCall = funcCall;
        this.op = op;
        this.expr = expr;
    }

}
