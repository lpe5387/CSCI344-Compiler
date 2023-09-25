package treeNodes;
/*
This class is for the IdOpExpr subset of expression node

Author: Luka Eaton
 */
public class IdOpExprNode extends ExprNode{

    private IdNode id;
    private OpNode op;
    private ExprNode expr;

    public IdOpExprNode(IdNode id, OpNode op, ExprNode expr){
        this.id = id;
        this.op = op;
        this.expr = expr;
    }

}
