/**
 * This class is responsible for the type node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

<<<<<<< HEAD
import provided.JottTree;
import provided.Token;

public class TypeNode implements JottTree {
=======
import provided.Token;

public class TypeNode {
>>>>>>> a1980726fc9301d03afc51fb61de385298a2ca1c
    
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
