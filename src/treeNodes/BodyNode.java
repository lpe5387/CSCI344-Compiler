package treeNodes;

/**
 * This class is responsible for the body node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class BodyNode implements JottTree {
    
    private ArrayList<BodyStmtNode> bodyStmtList;
    private ReturnStmtNode returnStmt; // null if no explicit return statement (epsilon in grammar)

    public BodyNode(ArrayList<BodyStmtNode> bodyStmtList, ReturnStmtNode returnStmt){
        this.bodyStmtList = bodyStmtList;
        this.returnStmt = returnStmt;
    }

    public static BodyNode parseBody(ArrayList<Token> tokenList) throws SyntaxException {
        BodyStmtNode bodyStmt;
        ArrayList<BodyStmtNode> stmtList = new ArrayList<>();
        while(true) {
            bodyStmt = BodyStmtNode.parseBodyStmt(tokenList);
            if(bodyStmt == null) {
                break;
            }
            stmtList.add(bodyStmt);
        }
        ReturnStmtNode returnStmt = ReturnStmtNode.parseReturnStmt(tokenList);
        return new BodyNode(stmtList, returnStmt);
    }
    public String convertToJott() {
        String jottString = "";
        for(BodyStmtNode bodyStmt : this.bodyStmtList) {
            jottString += bodyStmt.convertToJott();
        }
        if(this.returnStmt == null) {
            return jottString;
        } else {
            jottString += this.returnStmt.convertToJott();
            return jottString;
        }
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
