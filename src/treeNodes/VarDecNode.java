package treeNodes;

/**
 * This class is responsible for the variable declaration node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import helpers.SymbolTable;
import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class VarDecNode implements BodyStmtNode {

    private TypeNode type;
    private IdNode id;

    public VarDecNode(TypeNode type, IdNode id){
        this.type = type;
        this.id = id;
    }

    public static VarDecNode parseVarDec ( ArrayList<Token> tokenList ) throws SyntaxException, SemanticException {
        TypeNode typeNode;
        IdNode idNode;

        //variable declaration for SymbolTable entry
        ArrayList<String> varDetails = new ArrayList<String>();

        //check if the tokenlist is not empty
        if (tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        Token token = tokenList.get(0);

        //see if we can make a typeNode from the 1st token
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            typeNode = TypeNode.parseType(tokenList);

            //get the type of the variable then parse it to String before adding it to varDetails
            varDetails.add(token.getTokenType().toString());
            varDetails.add("no");

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }

            //get the next token and see if its an id type
            token = tokenList.get(0);

            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.parseId(tokenList);

                if (SymbolTable.reservedWords.contains(idNode.getToken().getToken())){
                    throw new SemanticException("Cannot use reserved word as a variable name",
                            idNode.getToken().getFilename(), idNode.getToken().getLineNum());
                }

                //add variable to SymbolTable
                if(SymbolTable.getVarDef(token.getToken()) == null){
                    SymbolTable.addVarDef(token.getToken(), varDetails);
                } else {
                    throw new SemanticException("Variable already defined in scope ", token.getFilename(), token.getLineNum());
                }

            } else throw new SyntaxException("Expected an Id. Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());

        } else throw new SyntaxException("Expected a Type. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

        //check if the token list is not empty
        if (tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        //gets the token
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

    public String convertToJava(String className){ return this.type.convertToJava(className) + " " +
            this.id.convertToJava(className) + ";\n";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree() {
        return true;
    }

}
