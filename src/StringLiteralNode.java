/**
 * This class is responsible for the string literal node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import provided.Token;

public class StringLiteralNode {
    
    private Token token;

    public StringLiteralNode(Token token){
        this.token = token;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
