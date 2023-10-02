package treeNodes;

/**
 * This class is responsible for the variable declaration node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class VarDecNode implements JottTree, BodyStmtNode {

    private TypeNode type;
    private IdNode id;

    public VarDecNode(TypeNode type, IdNode id){
        this.type = type;
        this.id = id;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
