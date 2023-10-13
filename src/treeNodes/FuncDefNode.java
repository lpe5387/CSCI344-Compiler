package treeNodes;

/**
 * This class is responsible for the function definition node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;
import java.util.Objects;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

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

    public static FuncDefNode parseFuncDef(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);
        if(Objects.equals(token.getToken(), "def")){
            tokenlist.remove(0);
            token = tokenlist.get(0);
            if(token.getTokenType() == TokenType.ID_KEYWORD){
                IdNode idNode =IdNode.parseId(tokenlist);
                token = tokenlist.get(0);
                if(token.getTokenType() == TokenType.L_BRACE) {
                    tokenlist.remove(0);
                    FuncDefParamsNode funcDefParams = FuncDefParamsNode.parseFuncDefParams(tokenlist);
                    token = tokenlist.get(0);
                    if(token.getTokenType() == TokenType.R_BRACE) {
                        tokenlist.remove(0);
                        token = tokenlist.get(0);
                        if (token.getTokenType() == TokenType.COLON) {
                            tokenlist.remove(0);
                            FunctionReturnNode returnType = FunctionReturnNode.ParseFuncReturn(tokenlist);
                            token = tokenlist.get(0);
                            if(token.getTokenType() == TokenType.L_BRACKET){
                                tokenlist.remove(0);
                                BodyNode body = BodyNode.parseBody(tokenlist);
                                token = tokenlist.get(0);
                                if(token.getTokenType() == TokenType.R_BRACKET){
                                    FuncDefNode node = new FuncDefNode(idNode, funcDefParams, returnType, body);
                                    return node;
                                } else {
                                    throw new SyntaxException("Expected }, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                                }
                            } else {
                                throw new SyntaxException("Expected {, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                            }
                        }  else {
                            throw new SyntaxException("Expected :, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                        }
                    } else {
                        throw new SyntaxException("Expected ], got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                    }
                } else {
                    throw new SyntaxException("Expected [, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
                }
            } else {
                throw new SyntaxException("Expected a ID/Keyword, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
            }
        } else {
            throw new SyntaxException("Expected \"def\", got " + token.getTokenType(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){
        String toString = "def " + this.id.convertToJott() + "["
                + this.funcDefParams.convertToJott() + "]:" + this.returnType.convertToJott()
                + "{" + this.body.convertToJott() + "}";
        return toString;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
