package treeNodes;

/**
 * This class is responsible for the additional function definition parameters node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import helpers.SymbolTable;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class FuncDefParamsTNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    public FuncDefParamsTNode(IdNode id, TypeNode type){
        this.id = id;
        this.type = type;
    }

    public static FuncDefParamsTNode parseFuncDefParamsT(ArrayList<Token> tokenlist, ArrayList<String> funcDetails)
            throws SyntaxException, SemanticException {
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

                IdNode idNode = IdNode.parseId(tokenlist); //replace w/ actual iD parser

                String paramName = idNode.getToken().getToken();

                if(tokenlist.isEmpty()){
                    throw new SyntaxException("Unexpected end of file");
                }
                token = tokenlist.get(0);

                if(token.getTokenType() == TokenType.COLON){

                    tokenlist.remove(0);
                    TypeNode typeNode = TypeNode.parseType(tokenlist);

                    String type = typeNode.getToken().getToken();

                    if(SymbolTable.getVarDef(paramName) != null) {
                        throw new SemanticException("Function parameter with this name already exists",
                                idNode.getToken().getFilename(),
                                idNode.getToken().getLineNum());
                    }

                    // in the function symbol table, adds the type of this parameter to the entry of the containing function
                    funcDetails.add(type);

                    // in the variable symbol table, adds this parameter as a local variable to the containing function
                    ArrayList<String> varDetails = new ArrayList<>();
                    varDetails.add(type);
                    varDetails.add("yes");
                    SymbolTable.addVarDef(paramName, varDetails);

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

    public String convertToJava(String className){
        String toString = ", " + this.type.convertToJava(className) + " " + this.id.convertToJava(className);
        return toString;
    }

    public String convertToC(){
        String toString = ", " + this.type.convertToC() + " " + this.id.convertToC();
        return toString;
    }

    public String convertToPython(){
        String toString = ", " + this.id.convertToPython();
        return toString;
    }
    
    public boolean validateTree(){return true;}

    public TypeNode getTypeNode() {return this.type;}
}
