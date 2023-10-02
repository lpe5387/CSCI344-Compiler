package treeNodes;

/**
 * This class is responsible for the if statement node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

import java.util.ArrayList;

public class IfStmtNode implements JottTree, BodyStmtNode {
    
    private ExprNode expr;

    private BodyNode body;

    private ArrayList<ElseIfNode> elseIfList;
    private ElseNode elseStmt;

    public IfStmtNode(ExprNode expr, BodyNode body, ArrayList<ElseIfNode> elseIfList, ElseNode elseStmt){
        this.expr = expr;
        this.body = body;
        this.elseIfList = elseIfList;
        this.elseStmt = elseStmt;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
