package treeNodes;
/*
This class is for the NumOpExpr subset of the expression node

Author: Luka Eaton
 */
public class NumOpExprNode extends ExprNode{

    private NumNode num;
    private OpNode op;
    private ExprNode expr;

    public NumOpExprNode(NumNode num, OpNode op, ExprNode expr){
        this.num = num;
        this.op = op;
        this.expr = expr;
    }

}
