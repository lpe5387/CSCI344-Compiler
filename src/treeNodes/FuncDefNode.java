package treeNodes;

/**
 * This class is responsible for the function definition node for the parse tree
 *
 * @author Isaac Kim, Dara Prak, Luka Eaton
 */

import java.util.ArrayList;
import java.util.Objects;

import exceptions.SemanticException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;
import SymbolTable.SymbolTable;

public class FuncDefNode implements JottTree {
    
    private IdNode id;
    private FuncDefParamsNode funcDefParams;
    private FunctionReturnNode returnType;
    private BodyNode body;

    public FuncDefNode(IdNode id, FuncDefParamsNode funcDefParams, FunctionReturnNode returnType, BodyNode body){
        this.id = id;
        this.funcDefParams = funcDefParams;
        this.returnType = returnType;
        this.body = body;
    }

    public static FuncDefNode parseFuncDef(ArrayList<Token> tokenlist) throws SyntaxException, SemanticException {
        //this is my magnum opus
        if(tokenlist.isEmpty()){
            throw new SyntaxException("Unexpected end of file");
        }
        Token token = tokenlist.get(0);
        if(Objects.equals(token.getToken(), "def")){
            tokenlist.remove(0);
            if(tokenlist.isEmpty()){
                throw new SyntaxException("Unexpected end of file");
            }
            token = tokenlist.get(0);
            if(token.getTokenType() == TokenType.ID_KEYWORD){
                IdNode idNode = IdNode.parseId(tokenlist);

                String funcName = idNode.getToken().getToken();

                // holds details of the function to include in its symbol table entry
                ArrayList<String> funcDetails = new ArrayList<>();

                // adds function definition to symbol table, if the name is unique
                if(SymbolTable.getFuncDef(funcName) != null) {
                    throw new SemanticException("Function definition with this name already exists",
                            idNode.getToken().getFilename(),
                            idNode.getToken().getLineNum());
                }
                SymbolTable.addFuncDef(funcName, funcDetails); // currently no details, but entry will get updated later

                // sets current scope of the symbol table to this function
                SymbolTable.setCurrentScope(funcName);

                if(tokenlist.isEmpty()){
                    throw new SyntaxException("Unexpected end of file");
                }
                token = tokenlist.get(0);
                if(token.getTokenType() == TokenType.L_BRACKET) {
                    tokenlist.remove(0);

                    // dynamically adds parameter types to funcDetails during parse
                    FuncDefParamsNode funcDefParams = FuncDefParamsNode.parseFuncDefParams(tokenlist, funcDetails);

                    if(tokenlist.isEmpty()){
                        throw new SyntaxException("Unexpected end of file");
                    }
                    token = tokenlist.get(0);
                    if(token.getTokenType() == TokenType.R_BRACKET) {
                        tokenlist.remove(0);
                        if(tokenlist.isEmpty()){
                            throw new SyntaxException("Unexpected end of file");
                        }
                        token = tokenlist.get(0);
                        if (token.getTokenType() == TokenType.COLON) {
                            tokenlist.remove(0);
                            FunctionReturnNode returnType = FunctionReturnNode.ParseFuncReturn(tokenlist);

                            // add return type to symbol table for this function definition
                            funcDetails.add(returnType.getTypeNode().getToken().getToken());

                            if(tokenlist.isEmpty()){
                                throw new SyntaxException("Unexpected end of file");
                            }
                            token = tokenlist.get(0);
                            if(token.getTokenType() == TokenType.L_BRACE){
                                tokenlist.remove(0);
                                BodyNode body = BodyNode.parseBody(tokenlist);
                                if(tokenlist.isEmpty()){
                                    throw new SyntaxException("Unexpected end of file");
                                }
                                token = tokenlist.get(0);
                                if(token.getTokenType() == TokenType.R_BRACE){
                                    tokenlist.remove(0);
                                    FuncDefNode node = new FuncDefNode(idNode, funcDefParams, returnType, body);

                                    // updates function's entry in the symbol table with the complete details
                                    SymbolTable.addFuncDef(funcName, funcDetails);

                                    // tells symbol table we exited the scope of this function
                                    SymbolTable.setCurrentScope(null);

                                    return node;
                                } else {
                                    throw new SyntaxException("Expected }, got " + token.getToken(), token.getFilename(), token.getLineNum());
                                }
                            } else {
                                throw new SyntaxException("Expected {, got " + token.getToken(), token.getFilename(), token.getLineNum());
                            }
                        }  else {
                            throw new SyntaxException("Expected :, got " + token.getToken(), token.getFilename(), token.getLineNum());
                        }
                    } else {
                        throw new SyntaxException("Expected ], got " + token.getToken(), token.getFilename(), token.getLineNum());
                    }
                } else {
                    throw new SyntaxException("Expected [, got " + token.getToken(), token.getFilename(), token.getLineNum());
                }
            } else {
                throw new SyntaxException("Expected a ID/Keyword, got " + token.getToken(), token.getFilename(), token.getLineNum());
            }
        } else {
            throw new SyntaxException("Expected \"def\", got " + token.getToken(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){
        String toString;
        if(this.funcDefParams == null){
            toString = "def " + this.id.convertToJott() + "[]:" + this.returnType.convertToJott()
                    + "{" + this.body.convertToJott() + "}";
        }
        else{
            toString = "def " + this.id.convertToJott() + "["
                    + this.funcDefParams.convertToJott() + "]:" + this.returnType.convertToJott()
                    + "{" + this.body.convertToJott() + "}";
        }
        return toString;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree() {
//        if(this.returnType.getTypeNode().getToken().getTokenType() == this.body.getReturnStmt().get) {
//
//        }
        return true;
    }

    public IdNode getId() {
        return this.id;
    }

    public FunctionReturnNode getReturnType() {
        return this.returnType;
    }

    public FuncDefParamsNode getFuncDefParams() {
        return this.funcDefParams;
    }
}
