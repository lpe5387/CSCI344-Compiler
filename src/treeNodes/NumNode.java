package treeNodes;

/**
 * This class is responsible for the number node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */


public class NumNode implements JottTree {

    private Token token;

    public NumNode(Token token){
        this.token = token;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
