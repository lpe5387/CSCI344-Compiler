public class FuncCallNode implements JottTree {
    
    private IdNode id;
    private ParamsNode params;

    public FuncCallNode(IdNode id, ParamsNode params){
        this.id = id;
        this.params = params;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
