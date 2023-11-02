package treeNodes;

/**
 * This class is responsible for the additional function definition parameters node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import java.util.ArrayList;

import SymbolTable.SymbolTable;
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

    public static FuncDefParamsTNode parseFuncDefParamsT(ArrayList<Token> tokenlist, ArrayList<String> funcDetails)
            throws SyntaxException {
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

                String paramName = idNode.getToken().getToken();

                if(tokenlist.isEmpty()){
                    throw new SyntaxException("Unexpected end of file");
                }
                token = tokenlist.get(0);

                if(token.getTokenType() == TokenType.COLON){

                    tokenlist.remove(0);
                    TypeNode typeNode = TypeNode.parseType(tokenlist);

                    String type = typeNode.getToken().getToken();

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

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

    public TypeNode getTypeNode() {return this.type;}
}
