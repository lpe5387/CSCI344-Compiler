package treeNodes;

/**
 * This class is responsible for the program node for the parse tree
 *
 * @author Luka Eaton
 */


public class ProgramNode implements JottTree {
    
    private FuncDefNode funcDef;

    public ProgramNode(FuncDefNode funcDef){
        this.funcDef = funcDef;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
