package treeNodes;

/**
 * This class is responsible for the body node for the parse tree
 *
 * @author Luka Eaton
 */


public class BodyNode implements JottTree {
    
    private BodyStmtNode bodyStmt;
    private ReturnStmtNode returnStmt;

    public BodyNode(BodyStmtNode bodyStmt, ReturnStmtNode returnStmt){
        this.bodyStmt = bodyStmt;
        this.returnStmt = returnStmt;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
