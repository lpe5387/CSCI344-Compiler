package treeNodes;

/**
 * This class is responsible for the type node for the parse tree
 *
 * @author Luka Eaton, lucie lim, Andrew Dantone
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class TypeNode implements JottTree {
    
    private Token token;
    private TypeNodeNames name;

    public TypeNode(Token token){
        this.token = token; //save token for error handling
        //Assign the name of the type
        if (token.getToken().equals(TypeNodeNames.Double.name())){
            this.name = TypeNodeNames.Double;
        }
        else if (token.getToken().equals(TypeNodeNames.Integer.name())){
            this.name = TypeNodeNames.Integer;
        }
        else if (token.getToken().equals(TypeNodeNames.String.name())){
            this.name = TypeNodeNames.String;
        }
        else if (token.getToken().equals(TypeNodeNames.Boolean.name())){
            this.name = TypeNodeNames.Boolean;
        }
    }

    public static TypeNode parseType(ArrayList<Token> tokenlist) throws SyntaxException{
        if(!tokenlist.isEmpty()) {
            Token tok = tokenlist.get(0); //grab the token
            if (tok.getTokenType() != TokenType.ID_KEYWORD) { //if not an ID_KEYWORD, reject
                throw new SyntaxException("Expected a ID KEYWORD, got: " + tok.getTokenType(), tok.getFilename(), tok.getLineNum());
            }
            //Check if the text matches a valid name
            else if (tok.getToken().equals(TypeNodeNames.Double.name()) || tok.getToken().equals(TypeNodeNames.Integer.name())
                    || tok.getToken().equals(TypeNodeNames.String.name()) || tok.getToken().equals(TypeNodeNames.Boolean.name())) {
                tokenlist.remove(0); //pop the token from the list
                return new TypeNode(tok); //make the node and return it
            } else { //the token text doesn't match a legal name, reject
                throw new SyntaxException("Invalid ID KEYWORD: " + tok.getToken(), tok.getFilename(), tok.getLineNum());
            }
        }
        else throw new SyntaxException("Expected an expression. Reached EOF");
    }

    public Token getToken() {return token;}

    public TypeNodeNames getName(){return this.name;}

    public String convertToJott(){
        if (this.getName().equals(TypeNodeNames.Double)){
            return TypeNodeNames.Double.name();
        }
        else if (this.getName().equals(TypeNodeNames.Integer)){
            return TypeNodeNames.Integer.name();
        }
        else if (this.getName().equals(TypeNodeNames.String)){
            return TypeNodeNames.String.name();
        }
        else if (this.getName().equals(TypeNodeNames.Boolean)){
            return TypeNodeNames.Boolean.name();
        }
        else {return "ERROR";} //there has tto be a better thing to do than this
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
