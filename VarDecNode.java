public class VarDecNode implements JottTree {

    private String type;
    private String id;

    public VarDecNode(String type, String id){
        this.type = type;
        this.id = id;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
