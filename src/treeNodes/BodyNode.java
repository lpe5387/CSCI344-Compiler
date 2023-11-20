package treeNodes;

/**
 * This class is responsible for the body node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import helpers.Indentation;
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

    public static BodyNode parseBody(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
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
            if(bodyStmt instanceof FuncCallNode)
                jottString += ";";
        }
        if(this.returnStmt == null) {
            return jottString;
        } else {
            jottString += this.returnStmt.convertToJott();
            return jottString;
        }
    }

    public String convertToJava(String className){
        String javaString = "";
        Indentation.shiftIndentForward();
        for(BodyStmtNode bodyStmt : this.bodyStmtList) {
            javaString += Indentation.addIndent() + bodyStmt.convertToJava(className);
            if(bodyStmt instanceof FuncCallNode)
                javaString += ";\n";
        }
        if(this.returnStmt != null) {
            javaString += Indentation.addIndent() + this.returnStmt.convertToJava(className);
        }
        Indentation.shiftIndentBackward();
        return javaString;
    }

    public String convertToC(){return "";}

    public String convertToPython(){
        String pythonString = "";
        Indentation.shiftIndentForward();
        for(BodyStmtNode bodyStmt : this.bodyStmtList) {
            pythonString += Indentation.addIndent() + bodyStmt.convertToPython();
            if(bodyStmt instanceof FuncCallNode)
                pythonString += "\n";
        }
        if(this.returnStmt != null) {
            pythonString += Indentation.addIndent() + this.returnStmt.convertToPython();
        }
        Indentation.shiftIndentBackward();
        return pythonString;
    }
    
    public boolean validateTree() throws SemanticException {
        for(BodyStmtNode bodyStmt : this.bodyStmtList){
            bodyStmt.validateTree();
        }
        if(this.returnStmt != null) this.returnStmt.validateTree();
        return true;
    }

    public ReturnStmtNode getReturnStmt(){return this.returnStmt;}

    public ArrayList<BodyStmtNode> getBodyStmtList() {
        return this.bodyStmtList;
    }

}
