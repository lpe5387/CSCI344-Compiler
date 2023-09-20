package treeNodes;

/**
 * This class is responsible for the if statement node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class IfStmtNode implements JottTree {
    
    private ExprNode expr;

    private BodyNode body;

    private ElseIfNode elseIf;
    private ElseNode elseStmt;

    public IfStmtNode(ExprNode expr, BodyNode body, ElseIfNode elseIf, ElseNode elseStmt){
        this.expr = expr;
        this.body = body;
        this.elseIf = elseIf;
        this.elseStmt = elseStmt;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
