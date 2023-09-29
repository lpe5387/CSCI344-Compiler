package treeNodes;

/**
 * This class is responsible for the additional function definition parameters node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class FuncDefParamsTNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    public FuncDefParamsTNode(IdNode id, TypeNode type){
        this.id = id;
        this.type = type;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
