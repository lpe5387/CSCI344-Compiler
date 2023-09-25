package treeNodes;

/**
 * This class is responsible for the expression node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;
import java.util.ArrayList;
import provided.Token;
import exceptions.SyntaxException;
public abstract class ExprNode implements JottTree {

    public static ExprNode ParseExpr(ArrayList<Token> tokenList){
        return null;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
