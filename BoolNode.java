/**
 * This class is responsible for the boolean node for the parse tree
 *
 * @author Luka Eaton
 */
public class BoolNode {
    
    private String value;

    public BoolNode(String value){
        this.value = value;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
