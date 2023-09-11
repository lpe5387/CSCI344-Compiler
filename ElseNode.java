public class ElseNode implements JottTree {

    private BodyNode body;

    public ElseNode(BodyNode body){
        this.body = body;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
