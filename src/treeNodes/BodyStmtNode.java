package treeNodes;

/**
 * This class is responsible for the body statement node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import provided.JottTree;

public abstract class BodyStmtNode implements JottTree {

    private IfStmtNode ifStmt;
    private WhileLoopNode whileLoop;
    private AsmtNode asmt;
    private VarDecNode varDec;
    private FuncCallNode func;
    private BodyStmtNode bodyStmt;

    public BodyStmtNode(WhileLoopNode whileLoop, BodyStmtNode bodyStmt){
        this.whileLoop = whileLoop;
        this.bodyStmt = bodyStmt;

    }

    public BodyStmtNode(AsmtNode asmt, BodyStmtNode bodyStmt){
        this.asmt = asmt;
        this.bodyStmt = bodyStmt;
    }

    public BodyStmtNode(VarDecNode varDec, BodyStmtNode bodyStmt){
        this.varDec = varDec;
        this.bodyStmt = bodyStmt;
    }

    public BodyStmtNode(FuncCallNode func, BodyStmtNode bodyStmt){
        this.func = func;
        this.bodyStmt = bodyStmt;
    }

    public static BodyStmtNode parseBodyStmt {

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
