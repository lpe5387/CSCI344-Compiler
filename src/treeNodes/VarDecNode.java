package treeNodes;

/**
 * This class is responsible for the variable declaration node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import exceptions.SemanticException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import java.util.ArrayList;
import SymbolTable.SymbolTable;

public class VarDecNode implements BodyStmtNode {

    private TypeNode type;
    private IdNode id;

    public VarDecNode(TypeNode type, IdNode id){
        this.type = type;
        this.id = id;
    }

    public static VarDecNode parseVarDec ( ArrayList<Token> tokenList ) throws SyntaxException {
        TypeNode typeNode;
        IdNode idNode;

        //check if the tokenlist is not empty
        if (tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        Token token = tokenList.get(0);

        //see if we can make a typeNode from the 1st token
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            typeNode = TypeNode.parseType(tokenList);

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }

            //get the next token and see if its an id type
            token = tokenList.get(0);

            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.parseId(tokenList);
            } else throw new SyntaxException("Expected an Id. Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());

        } else throw new SyntaxException("Expected a Type. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

        //check if the token list is not empty
        if (tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        //gets the ; token
        token = tokenList.get(0);
        //check if its ;
        if (token.getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                    token.getFilename(), token.getLineNum());
        }
        //remove the token from the list
        tokenList.remove(0);

        return new VarDecNode( typeNode, idNode);
    }

    public String convertToJott(){
        return this.type.convertToJott() + this.id.convertToJott() + ";";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree throws SemanticException(){
        //variable cannot be already taken
        if(this.){
            return true;
        } else {
            throw new SemanticException("Variable already declared", "holder", 0);
        }
        return true;
    }

}
