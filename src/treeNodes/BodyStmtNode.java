package treeNodes;

/**
 * This class is responsible for the body statement node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class BodyStmtNode implements JottTree {

    private IfStmtNode ifStmt;
    private WhileLoopNode whileLoop;
    private AsmtNode asmt;
    private VarDecNode varDec;
    private FuncCallNode func;

    private BodyStmtNode bodyStmt;

    // default constructor for a body statement
    public BodyStmtNode( BodyStmtNode bodystmt) {
        this.bodyStmt = bodystmt;
    }

    public BodyStmtNode(VarDecNode varDec, BodyStmtNode bodyStmt){
        this.varDec = varDec;
        this.bodyStmt = bodyStmt;
    }

    public BodyStmtNode(FuncCallNode func, BodyStmtNode bodyStmt){
        this.func = func;
        this.bodyStmt = bodyStmt;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
