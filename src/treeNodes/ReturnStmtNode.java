package treeNodes;

/**
 * This class is responsible for the return statement node for the parse tree
 *
 * @author Luka Eaton, Dara Prak
 */

import helpers.SymbolTable;
import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class ReturnStmtNode implements JottTree {

    private ExprNode expr;

    private Token returnStart;

    public ReturnStmtNode(ExprNode expr, Token token){
        this.expr = expr;
        this.returnStart = token;
    }

    public static ReturnStmtNode parseReturnStmt(ArrayList<Token> tokenList) throws SyntaxException {
        if(tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }
        Token first = tokenList.get(0);
        if(first.getToken().equals("return")) {
            tokenList.remove(0);
            ExprNode expr = ExprNode.parseExpr(tokenList);
            Token firstAfterExpr = tokenList.get(0);
            if(firstAfterExpr.getTokenType() == TokenType.SEMICOLON) { // if followed by ';', eat it
                tokenList.remove(0);
                return new ReturnStmtNode(expr, first);
            } else {
                throw new SyntaxException("Expected ';' Got: "+ firstAfterExpr.getToken(),
                        firstAfterExpr.getFilename(), firstAfterExpr.getLineNum());
            }
        } else {
            if(first.getTokenType() == TokenType.R_BRACE) {
                return null; // equivalent to epsilon in grammar
            } else {
                throw new SyntaxException("Expected Return Statement, Got: "+ first.getToken(),
                        first.getFilename(), first.getLineNum());
            }
        }
    }
    public String convertToJott() {
        return "return " + this.expr.convertToJott() + ";";
    }

    public String convertToJava(String className){return "return " + this.expr.convertToJava(className) + ";\n";}

    public String convertToC() throws SemanticException {return "return " + this.expr.convertToC() + ";\n";}

    public String convertToPython(){return "return " + this.expr.convertToPython() + "\n";}
    
    public boolean validateTree() throws SemanticException {
        this.expr.validateTree();
        // check if return matches function return type
        ArrayList<String> funcDetails = SymbolTable.getFuncDef(SymbolTable.getCurrentScope());
        if(!funcDetails.get(funcDetails.size()-1).equals("Any")){
            if(!funcDetails.get(funcDetails.size()-1).equals(this.expr.evaluateType())){
                throw new SemanticException("Incorrect return type", this.returnStart.getFilename(), this.returnStart.getLineNum());
            }
        }
        return true;
    }

    public ExprNode getExprNode() {
        return this.expr;
    }

}
