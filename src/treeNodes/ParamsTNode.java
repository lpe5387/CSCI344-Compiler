package treeNodes;

/**
 * This class is responsible for the additional parameters node for the parse tree
 *
 * @author Luka Eaton, Issac Kim
 */

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class ParamsTNode implements JottTree {

    private boolean isEmpty;

    private ExprNode expr;

    private ParamsTNode paramsT;

    private Token comma;

    public ParamsTNode(Token comma, ExprNode expr, ParamsTNode paramsT){
        this.comma = comma;
        this.expr = expr;
        this.paramsT = paramsT;
        this.isEmpty = false;
    }

    public static ParamsTNode ParseParamT(ArrayList<Token> tokenlist) throws SyntaxException{
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.COMMA) {
            tokenlist.remove(0);//needed to set up tokenlist for ParseExpr() <- logic error here, figure this out  later
            ExprNode expr = ExprNode.ParseExpr(tokenlist);// expr node maker
            ParamsTNode node = new ParamsTNode(token, expr, null);//base node
            if(tokenlist.get(0).getTokenType() == TokenType.COMMA) {
                node = new ParamsTNode(token, expr, ParseParamT(tokenlist));//if next token is of comma type, can be a second ParamsTNode <- logic error may be here, check later
            }
            return node;
        } else {
            throw new SyntaxException("Expected a Comma Operator, got " + token.getToken(), token.getFilename(), token.getLineNum());
        }
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}