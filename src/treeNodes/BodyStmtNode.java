package treeNodes;

import exceptions.SemanticException;
import exceptions.SyntaxException;
import helpers.SymbolTable;
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

    public static BodyStmtNode parseBodyStmt(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        if(tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }
        Token first = tokenList.get(0);
        if (first.getTokenType() == TokenType.FC_HEADER) { // else, the first token should be ID_KEYWORD
            BodyStmtNode funcCall = FuncCallNode.parseFuncCall(tokenList);
            // after successful parseFuncCall, the next token should be a semicolon
            if(tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }
            Token firstAfterFuncCall = tokenList.get(0);
            if(firstAfterFuncCall.getTokenType() == TokenType.SEMICOLON) { // if followed by ';', eat it
                tokenList.remove(0);
                return funcCall;
            } else {
                throw new SyntaxException("Expected ';' Got: "+ firstAfterFuncCall.getToken(),
                        firstAfterFuncCall.getFilename(), firstAfterFuncCall.getLineNum());
            }
        } else if(first.getToken().equals("if")) {
            return IfStmtNode.parseIfStmt(tokenList);
        } else if(first.getToken().equals("while")) {
            return WhileLoopNode.parseWhileLoop(tokenList);
        } else if(tokenList.size() >= 3 && (tokenList.get(1).getToken().equals("=") ||
                tokenList.get(2).getToken().equals("="))) {
            // if the first token is a variable that's in the symbol table, or the first token
            // is a valid datatype, we know it's an assignment node
            if(SymbolTable.getVarDef(first.getToken()) != null ||
                    first.getToken().equals("Double") || first.getToken().equals("Integer") ||
                    first.getToken().equals("String") || first.getToken().equals("Boolean")){
                return AsmtNode.parseAsmt(tokenList);
            }
            return null;
        } else if(first.getToken().equals("Double") || first.getToken().equals("Integer") ||
                first.getToken().equals("String") || first.getToken().equals("Boolean")) {
            return VarDecNode.parseVarDec(tokenList);
        } else {
            return null; // not a body statement
        }
    }

}
