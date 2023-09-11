public class FuncDefNode implements JottTree {
    
    private String id;
    private FuncDefParamsNode funcDefParams;
    private String returnType;
    private BodyNode body;

    private FuncDefNode nextFuncDef;

    public FuncDefNode(String id, FuncDefParamsNode funcDefParams, String returnType, BodyNode body, FuncDefNode nextFuncDef){
        this.id = id;
        this.funcDefParams = funcDefParams;
        this.returnType = returnType;
        this.body = body;
        this.nextFuncDef = nextFuncDef;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
