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

    public static FuncDefParamsTNode parseFuncDefParamsT(ArrayList<Token> tokenlist) throws SyntaxException {
        if(tokenlist.isEmpty()){
            throw new SyntaxException("Unexpected end of file");
        }
        Token token = tokenlist.get(0);//check if comma
        if(token.getTokenType() == TokenType.COMMA){

            tokenlist.remove(0);
            if(tokenlist.isEmpty()){
                throw new SyntaxException("Unexpected end of file");
            }
            token = tokenlist.get(0);//check if second token is ID

            if(token.getTokenType() == TokenType.ID_KEYWORD){

                tokenlist.remove(0);//remove comma from tokenlist
                IdNode idNode = IdNode.parseId(tokenlist); //replace w/ actual iD parser
                if(tokenlist.isEmpty()){
                    throw new SyntaxException("Unexpected end of file");
                }
                token = tokenlist.get(0);

                if(token.getTokenType() == TokenType.COLON){

                    tokenlist.remove(0);
                    TypeNode typeNode = TypeNode.parseType(tokenlist);
                    FuncDefParamsTNode node = new FuncDefParamsTNode(idNode, typeNode);
                    return node;

                } else {
                    throw new SyntaxException("Expected :, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                }
            } else {
                throw new SyntaxException("Expected a ID/Keyword, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
            }
        } else {
            throw new SyntaxException("Expected a comma, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){
        String toString = ", " + this.id.convertToJott() + ":" + this.type.convertToJott();
        return toString;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
