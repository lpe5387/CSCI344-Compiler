package treeNodes;

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

/**
 * This interface is responsible for the body statement node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

public interface BodyStmtNode extends JottTree {

    public static BodyStmtNode parseBodyStmt(ArrayList<Token> tokenList) throws SyntaxException {
        Token first = tokenList.get(0);
        if (first.getTokenType() == TokenType.FC_HEADER) { // else, the first token should be ID_KEYWORD
            BodyStmtNode funcCall = FuncCallNode.parseFuncCall(tokenList);
            // after successful parseFuncCall, the next token should be a semicolon
            Token firstAfterFuncCall = tokenList.get(0);
            if(firstAfterFuncCall.getTokenType() == TokenType.SEMICOLON) { // if followed by ';', eat it
                tokenList.remove(0);
                return funcCall;
            } else {
                throw new SyntaxException("Expected ';' Got: "+ firstAfterFuncCall.getToken(),
                        firstAfterFuncCall.getFilename(), firstAfterFuncCall.getLineNum());
            }
        }

        else if (first.getToken().equals("if")) {
            return IfStmtNode.parseIfStmt(tokenList);
        }
        else if (first.getToken().equals("while")) {
            return WhileLoopNode.parseWhileLoop(tokenList);
        }
        else if (tokenList.size() >= 3 && (tokenList.get(1).getToken().equals("=") || tokenList.get(2).getToken().equals("="))) {
            return AsmtNode.parseAsmt(tokenList);
        } else if (first.getToken().equals("Double") || first.getToken().equals("Integer") ||
                first.getToken().equals("String") || first.getToken().equals("Boolean")) {
            return VarDecNode.parseVarDec(tokenList);
        } else {
            return null; // not a body statement
        }
    }
}
