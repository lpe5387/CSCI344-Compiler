package treeNodes;

/**
 * This class is responsible for the function return node for the parse tree
 *
 * @author Luka Eaton
 */


public class FunctionReturnNode implements JottTree {
    
    private String voidReturn;
    private TypeNode type;

    public FunctionReturnNode(String voidReturn){
        this.voidReturn = voidReturn;
    }

    public FunctionReturnNode(TypeNode type){
        this.type = type;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
