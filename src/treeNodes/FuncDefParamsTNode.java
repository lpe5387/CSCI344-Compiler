package treeNodes;

/**
 * This class is responsible for the additional function definition parameters node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class FuncDefParamsTNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    public FuncDefParamsTNode(IdNode id, TypeNode type){
        this.id = id;
        this.type = type;
    }

    public static FuncDefParamsTNode ParseFuncDefParamsT(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);//check if comma
        if(token.getTokenType() == TokenType.COMMA){

            tokenlist.remove(0);
            token = tokenlist.get(0);//check if second token is ID

            if(token.getTokenType() == TokenType.ID_KEYWORD){

                tokenlist.remove(0);//remove comma from tokenlist
                IdNode idNode = IdNode.parseId(tokenlist); //replace w/ actual iD parser
                token = tokenlist.get(0);

                if(token.getTokenType() == TokenType.COLON){

                    tokenlist.remove(0);
                    TypeNode typeNode = TypeNode.parseType(tokenlist);
                    FuncDefParamsTNode node = new FuncDefParamsTNode(idNode, typeNode);
                    return node;

                } else {

                }
            } else {

            }
        } else {

        }
        return null;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
