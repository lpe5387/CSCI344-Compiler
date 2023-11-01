package treeNodes;

/**
 * This class is responsible for the additional parameters node for the parse tree
 *
 * @author Luka Eaton, Issac Kim, Lucie Lim
 */

import java.util.ArrayList;

import exceptions.SemanticException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class ParamsTNode implements JottTree {

    private ExprNode expr;

    private Token comma;

    public ParamsTNode(Token comma, ExprNode expr){
        this.comma = comma;
        this.expr = expr;
    }

    public static ParamsTNode parseParamT(ArrayList<Token> tokenlist) throws SyntaxException{
        if(!tokenlist.isEmpty()) {
            Token token = tokenlist.get(0);
            if (token.getTokenType() == TokenType.COMMA) {
                tokenlist.remove(0);
                ExprNode expr = ExprNode.parseExpr(tokenlist);// expr node maker
                ParamsTNode node = new ParamsTNode(token, expr);//base node
                return node;
            } else {
                throw new SyntaxException("Expected a Comma Operator, got " + token.getToken(), token.getFilename(), token.getLineNum());
            }
        }
        else throw new SyntaxException("Unexpected end of file");
    }

    public String convertToJott(){
        return ", " + this.expr.convertToJott();
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree() throws SemanticException {
        this.expr.validateTree();
        return true;
    }

}