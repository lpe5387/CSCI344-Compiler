package treeNodes;

/**
 * This class is responsible for the function definition node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class FuncDefNode implements JottTree {
    
    private IdNode id;
    private FuncDefParamsNode funcDefParams;
    private FunctionReturnNode returnType;
    private BodyNode body;

    public FuncDefNode(IdNode id, FuncDefParamsNode funcDefParams, FunctionReturnNode returnType, BodyNode body){
        this.id = id;
        this.funcDefParams = funcDefParams;
        this.returnType = returnType;
        this.body = body;
    }

    public static FuncDefNode ParseFuncDef(ArrayList<Token> tokenlist){

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
