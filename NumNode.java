/**
 * This class is responsible for the number node for the parse tree
 *
 * @author Luka Eaton
 */
public class NumNode {

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
