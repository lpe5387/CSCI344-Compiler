package treeNodes;

/**
 * This class is responsible for the assignment node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class AsmtNode implements BodyStmtNode {
    
    private TypeNode type;
    private IdNode id;
    private ExprNode expr;

    public AsmtNode(IdNode id, ExprNode expr){
        this.id = id;
        this.expr = expr;
    }

    public AsmtNode(TypeNode type, IdNode id, ExprNode expr){
        this.type = type;
        this.id = id;
        this.expr = expr;
    }

    public static AsmtNode parseAsmt(ArrayList<Token> tokenList) throws SyntaxException {
        TypeNode typeNode;
        IdNode idNode;
        ExprNode exprNode;
        Token token = tokenList.get(0);
        Token lookAhead = tokenList.get(1);

        //check the content of the token
        if (token.getTokenType() == TokenType.ID_KEYWORD && lookAhead.getTokenType() == TokenType.ASSIGN) {
            idNode = IdNode.parseId(tokenList);

            // removes the "=" token
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.ASSIGN) {
                throw new SyntaxException("Expected a = Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            //make expr node
            exprNode = ExprNode.parseExpr(tokenList);

            //removes ;
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            return new AsmtNode(idNode, exprNode);

        } else if (token.getTokenType() == TokenType.ID_KEYWORD) {
            typeNode = TypeNode.parseType(tokenList);
            idNode = IdNode.parseId(tokenList);

            // remove the "="
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.ASSIGN) {
                throw new SyntaxException("Expected a = Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            exprNode = ExprNode.parseExpr(tokenList);

            // remove the ;
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            return new AsmtNode(typeNode, idNode, exprNode);

        } else throw new SyntaxException("Expected a type or id. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

    }

    public String convertToJott(){
        if (this.type == null) {
            return this.id.convertToJott() + " = " + this.expr.convertToJott() + ";";
        }
        return this.type.convertToJott() + this.id.convertToJott() + " = " + this.expr.convertToJott() + ";";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
