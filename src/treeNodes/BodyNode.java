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
    
    private static ArrayList<BodyStmtNode> bodyStmtList;
    private ReturnStmtNode returnStmt;

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
    public String convertToJott(){return "";} //TODO null check for ReturnStmtNode

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
