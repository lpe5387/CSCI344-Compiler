package treeNodes;

/**
 * This class is responsible for the function return node for the parse tree
 *
 * @author Luka Eaton
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import java.util.ArrayList;

public class FunctionReturnNode implements JottTree {
    
    private Token voidReturn;
    private TypeNode type;

    public FunctionReturnNode(Token voidReturn){
        this.voidReturn = voidReturn;
    }

    public FunctionReturnNode(TypeNode type){
        this.type = type;
    }

    public static FunctionReturnNode ParseFuncReturn(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);
        if(token.getToken().equals("Void")){
            tokenlist.remove(0);
            return new FunctionReturnNode(token);
        }
        TypeNode type = TypeNode.parseType(tokenlist);
        return new FunctionReturnNode(type);
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
