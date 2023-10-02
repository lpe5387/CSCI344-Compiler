package treeNodes;

/**
 * This class is responsible for the variable declaration node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import java.util.ArrayList;

public class VarDecNode implements JottTree, BodyStmtNode {

    private TypeNode type;
    private IdNode id;
    private Token token;

    public VarDecNode(TypeNode type, IdNode id, Token token){
        this.type = type;
        this.id = id;
        this.token = token;
    }

    public VarDecNode parseVarDecNode ( ArrayList<Token> tokenList ) throws SyntaxException {
        TypeNode typeNode;
        IdNode idNode;
        Token token = tokenList.get(0);

        //see if we can make a typeNode from the 1st token
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            typeNode = TypeNode.parseType(tokenList);
            //get the next token and see if its an id type
            token = tokenList.get(0);
            // see if we can make an idNode
            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.ParseId(tokenList);
            } else throw new SyntaxException("Expected an Id. Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());
        } else throw new SyntaxException("Expected a Type. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

        //gets the ; token
        token = tokenList.get(0);
        //check if its ;
        if (token.getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                    token.getFilename(), token.getLineNum());
        }
        //remove the token from the list
        tokenList.remove(0);

        return new VarDecNode( typeNode, idNode, token );
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
