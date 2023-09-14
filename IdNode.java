/**
 * This class is responsible for the ID node for the parse tree
 *
 * @author Luka Eaton
 */
public class IdNode implements JottTree {
 
    private String value;

    public IdNode(String value){
        this.value = value;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}