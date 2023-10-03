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
        while(!tokenList.isEmpty()) {
            bodyStmt = BodyStmtNode.parseBodyStmt(tokenList);
            stmtList.add(bodyStmt);
        }
        ReturnStmtNode.parseReturnStmt(tokenList);
    }
    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
