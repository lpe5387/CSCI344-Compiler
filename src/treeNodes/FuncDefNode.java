package treeNodes;

/**
 * This class is responsible for the function definition node for the parse tree
 *
 * @author Isaac Kim, Dara Prak, Luka Eaton
 */

import helpers.Indentation;
import helpers.SymbolTable;
import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.Objects;

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

                if (SymbolTable.reservedWords.contains(funcName)){
                    throw new SemanticException("Cannot use reserved word as a function name",
                            idNode.getToken().getFilename(), idNode.getToken().getLineNum());
                }

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
                            if(returnType.getVoidReturn() != null) funcDetails.add("Void");
                            else funcDetails.add(returnType.getTypeNode().getToken().getToken());

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

    public String convertToJava(String className){
        String toString;

        String main = "";
        if ( this.id.getToken().getToken().equals("main")) {
            main = "String[] args";
        }
        if(this.funcDefParams == null){
            toString = "public static " + this.returnType.convertToJava(className) + " " + this.id.convertToJava(className)
                    + "(" + main + "){\n" + this.body.convertToJava(className) + Indentation.addIndent() + "}\n";
        }
        else{
            toString = "public static " + this.returnType.convertToJava(className) + " " + this.id.convertToJava(className)
                    + "(" + this.funcDefParams.convertToJava(className) + "){\n" + this.body.convertToJava(className) + Indentation.addIndent() + "}\n";
        }
        return toString;
    }

    public String convertToC() throws SemanticException {
        String toString;
        if ( this.id.getToken().getToken().equals("main")) {
            toString = "int main(void){\n" + this.body.convertToC() + Indentation.addIndent() + "}\n";
        }
        else if(this.funcDefParams == null){
            toString = this.returnType.convertToC() + " " + this.id.convertToC()
                    + "(void){\n" + this.body.convertToC() + Indentation.addIndent() + "}\n";
        }
        else{
            toString = this.returnType.convertToC() + " " + this.id.convertToC()
                    + "(" + this.funcDefParams.convertToC() + "){\n" + this.body.convertToC() + Indentation.addIndent() + "}\n";
        }
        return toString;
    }

    public String convertToPython(){
        String toString;

        if(this.funcDefParams == null){
            toString = "def " + this.id.convertToPython() + "()" + ":\n" + this.body.convertToPython();
        }
        else{
            toString = "def " + this.id.convertToPython() + "(" + this.funcDefParams.convertToPython()+  "):\n"
                    + this.body.convertToPython();
        }

        // adds the main() at the bottom of the program
        if ( this.id.getToken().getToken().equals("main")) {
            toString += "\n\nmain()";
        }

        return toString;

    }
    
    public boolean validateTree() throws SemanticException {
        SymbolTable.setCurrentScope(this.id.getToken().getToken());
        if(this.funcDefParams != null) this.funcDefParams.validateTree();
        // this will validate any return statements in the body and ensure it's the correct type
        this.body.validateTree();
        String expectedReturnType;
        if(this.returnType.getVoidReturn() != null) expectedReturnType = "Void";
        else expectedReturnType = this.returnType.getTypeNode().getToken().getToken();
        ExprNode returnExpr = null;
        if(this.body.getReturnStmt() != null) returnExpr = this.body.getReturnStmt().getExprNode();
        // if this isn't a void return and the main function body doesn't have a return,
        // we check if there are any if-elseif-else statements that have a valid return path
        if(returnExpr == null && !expectedReturnType.equals("Void")) {
            boolean hasReturnPath = false;
            for(BodyStmtNode bodyStmt : this.body.getBodyStmtList()) {
                // if bodyStmt is an if statement, check if return path is valid and type matches
                if(bodyStmt instanceof IfStmtNode ifStmt) {
                    if(ifStmt.isReturnable()) {
                        hasReturnPath = true;
                        break;
                    }
                }
            }
            if(!hasReturnPath) {
                throw new SemanticException("Non-Void function has a path with no return",
                        this.id.getToken().getFilename(),
                        this.id.getToken().getLineNum());
            }
        }
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
