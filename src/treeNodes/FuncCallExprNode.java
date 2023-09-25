package treeNodes;
/*
This class is for the function call subset of expression node

Author: Luka Eaton
 */
public class FuncCallExprNode extends ExprNode{

    private FuncCallNode funcCall;

    public FuncCallExprNode(FuncCallNode funcCall){
        this.funcCall = funcCall;
    }

}
