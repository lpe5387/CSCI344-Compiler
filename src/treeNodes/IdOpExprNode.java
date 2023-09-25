package treeNodes;

import java.util.ArrayList;

/*
This class is for the IdOpExpr subset of expression node

Author: Luka Eaton
 */
import java.util.ArrayList;
import provided.Token;
import exceptions.SyntaxException;
public class IdOpExprNode extends ExprNode{

    private IdNode id;
    private OpNode op;
    private ExprNode expr;

    public IdOpExprNode(IdNode id, OpNode op, ExprNode expr){
        this.id = id;
        this.op = op;
        this.expr = expr;
    }

    public static IdOpExprNode ParseIdOpExprNode(ArrayList<Token> tokenList) throws SyntaxException {
        IdNode id = IdNode.ParseId(tokenList);
        OpNode op = OpNode.ParseOp(tokenList);
        ExprNode expr = ExprNode.ParseExpr(tokenList);
        return new IdOpExprNode(id, op, expr);
    }

}
