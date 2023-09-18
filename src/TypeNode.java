/**
 * This class is responsible for the type node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import provided.JottTree;
import provided.Token;

public class TypeNode implements JottTree {
    
    private Token token;

    public TypeNode(Token token){
        this.token = token;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
