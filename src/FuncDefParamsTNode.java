/**
 * This class is responsible for the additional function definition parameters node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class FuncDefParamsTNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    private FuncDefParamsTNode funcDefParamsT;

    public FuncDefParamsTNode(IdNode id, TypeNode type, FuncDefParamsTNode funcDefParamsT){
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
