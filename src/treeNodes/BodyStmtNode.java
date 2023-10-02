package treeNodes;

import exceptions.SyntaxException;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

/**
 * This interface is responsible for the body statement node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

public interface BodyStmtNode {

    public static BodyStmtNode parseBodyStmt(ArrayList<Token> tokenList) throws SyntaxException {
        Token first = tokenList.get(0);
        if (first.getTokenType().equals(TokenType.FC_HEADER)) { // else, the first token should be ID_KEYWORD
            BodyStmtNode funcCall = parseFuncCall(tokenList);
            // after successful parseFuncCall, the next token should be a semicolon
            Token firstAfterFuncCall = tokenList.get(0);
            if(firstAfterFuncCall.getTokenType() == TokenType.SEMICOLON) {
                tokenList.remove(0);
                return funcCall;
            } else {
                throw new SyntaxException("Expected ';' Got: "+ firstAfterFuncCall.getToken(),
                        firstAfterFuncCall.getFilename(), firstAfterFuncCall.getLineNum());
            }
        }
        String firstString = first.getToken();
        if (firstString.equals("if")) {
            return parseIfStmt(tokenList);
        }
        if (firstString.equals("while")) {
            return parseWhileLoop(tokenList);
        }
        Token second = tokenList.get(1);
        Token third = tokenList.get(2);
        if (second.getToken().equals("=") || third.getToken().equals("=")) {
            return parseAsmt(tokenList);
        } else {
            return parseVarDec(tokenList);
        }
    }

}
