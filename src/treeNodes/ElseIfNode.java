package treeNodes;

/**
 * This class is responsible for the else if node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class ElseIfNode implements JottTree {
    
    private ExprNode expr;

    private BodyNode body;


    public ElseIfNode(ExprNode expr, BodyNode body){
        this.expr = expr;
        this.body = body;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
