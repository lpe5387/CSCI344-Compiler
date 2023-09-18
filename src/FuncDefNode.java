/**
 * This class is responsible for the function definition node for the parse tree
 *
 * @author Luka Eaton
 */


public class FuncDefNode implements JottTree {
    
    private IdNode id;
    private FuncDefParamsNode funcDefParams;
    private FunctionReturnNode returnType;
    private BodyNode body;

    private FuncDefNode nextFuncDef;

    public FuncDefNode(IdNode id, FuncDefParamsNode funcDefParams, FunctionReturnNode returnType, BodyNode body, FuncDefNode nextFuncDef){
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
