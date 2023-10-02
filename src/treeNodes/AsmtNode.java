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

public class AsmtNode implements JottTree, BodyStmtNode {
    
    private TypeNode type;
    private IdNode id;
    private ExprNode expr;
    private Token token;

    public AsmtNode(IdNode id, Token token, ExprNode expr){
        this.id = id;
        this.token = token;
        this.expr = expr;
    }

    public AsmtNode(TypeNode type, IdNode id, Token token, ExprNode expr){
        this.type = type;
        this.id = id;
        this.token = token;
        this.expr = expr;
    }

    public static AsmtNode parseAsmtNode(ArrayList<Token> tokenlist) throws SyntaxException {
        TypeNode typeNode;
        IdNode idNode;
        ExprNode exprNode;
        Token token = tokenlist.get(0);
        Token lookAhead = tokenlist.get(1);

        //check the content of the token
        if (token.getTokenType() == TokenType.ID_KEYWORD && lookAhead.getTokenType() == TokenType.ASSIGN) {
            idNode = IdNode.ParseId(tokenlist);

            // gets and removes the "=" token
            token = tokenlist.get(0);
            tokenlist.remove(0);

            //make expr node
            exprNode = ExprNode.ParseExpr(tokenlist);

            //removes ;
            tokenlist.remove(0);

            return new AsmtNode(idNode, token, exprNode);

        } else if (token.getTokenType() == TokenType.ID_KEYWORD) {
            typeNode = TypeNode.parseType(tokenlist);
            idNode = IdNode.ParseId(tokenlist);

            // get the "="
            token = tokenlist.get(0);
            tokenlist.remove(0);

            exprNode = ExprNode.ParseExpr(tokenlist);

            // remove the ;
            tokenlist.remove(0);

            return new AsmtNode(typeNode, idNode, token, exprNode);

        } else throw new SyntaxException("Expected a type or id. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
