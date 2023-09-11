public class FuncDefParamsTNode implements JottTree {
    
    private String id;
    private String type;

    private FuncDefParamsTNode funcDefParamsT;

    public FuncDefParamsTNode(String id, String type, FuncDefParamsTNode funcDefParamsT){
        this.id = id;
        this.type = type;
        this.funcDefParamsT = funcDefParamsT;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
