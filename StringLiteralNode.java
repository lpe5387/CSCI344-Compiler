public class StringLiteralNode {
    
    private String value;

    public StringLiteralNode(String value){
        this.value = value;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
