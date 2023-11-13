package treeNodes;

/**
 * This class is responsible for the function definition parameters node for the parse tree
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

public class FuncDefParamsNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    private ArrayList<FuncDefParamsTNode> funcDefParamsTList;

    public FuncDefParamsNode(IdNode id, TypeNode type, ArrayList<FuncDefParamsTNode> funcDefParamsTList){
        this.id = id;
        this.type = type;
        this.funcDefParamsTList = funcDefParamsTList;
    }

    public static FuncDefParamsNode parseFuncDefParams(ArrayList<Token> tokenlist, ArrayList<String> funcDetails)
            throws SyntaxException, SemanticException {
        if(tokenlist.isEmpty()){
            throw new SyntaxException("Unexpected end of file");
        }
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.ID_KEYWORD){
            IdNode idNode = IdNode.parseId(tokenlist);

            String paramName = idNode.getToken().getToken();

            if(tokenlist.isEmpty()){
                throw new SyntaxException("Unexpected end of file");
            }
            token = tokenlist.get(0);
            if(token.getTokenType() == TokenType.COLON) {
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

                ArrayList<FuncDefParamsTNode> funcDefParamsTNodes = new ArrayList<>();
                while (tokenlist.get(0).getTokenType() == TokenType.COMMA) {

                    // dynamically adds parameter types to funcDetails during parse
                    funcDefParamsTNodes.add(FuncDefParamsTNode.parseFuncDefParamsT(tokenlist, funcDetails));
                }
                FuncDefParamsNode node = new FuncDefParamsNode(idNode, typeNode, funcDefParamsTNodes);
                return node;
            } else {
                throw new SyntaxException("Expected :, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
            }
        } else {
            return null; //if no ID then no params
        }
    }

    public String convertToJott(){
        StringBuilder toString = new StringBuilder(this.id.convertToJott());
        toString.append(":");
        toString.append(this.type.convertToJott());
        for (FuncDefParamsTNode i : this.funcDefParamsTList){
            toString.append(i.convertToJott());
        }
        return toString.toString();
    }

    public String convertToJava(String className){
        StringBuilder toString = new StringBuilder(this.type.convertToJava(className));
        toString.append( " " + this.id.convertToJava(className));
        for (FuncDefParamsTNode i : this.funcDefParamsTList){
            toString.append(i.convertToJava(className));
        }
        return toString.toString();
    }

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

    public IdNode getId() {
        return this.id;
    }

    public TypeNode getTypeNode() {return this.type;}

}
