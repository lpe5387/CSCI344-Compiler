package treeNodes;

import provided.JottTree;

public class OperationNode implements JottTree, ExprNode {

    private ExprNode expr1;
    private OpNode op;
    private ExprNode expr2;

    public OperationNode(ExprNode expr1, OpNode op, ExprNode expr2){
        this.expr1 = expr1;
        this.op = op;
        this.expr2 = expr2;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}

    public boolean validateTree(){return true;}

}
