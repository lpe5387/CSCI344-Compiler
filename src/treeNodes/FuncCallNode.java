package treeNodes;

/**
 * This class is responsible for the function call node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import java.util.ArrayList;

public class FuncCallNode implements ExprNode, BodyStmtNode {

    private IdNode id;
    private ParamsNode params;

    public FuncCallNode(IdNode id, ParamsNode params) {
        this.id = id;
        this.params = params;
    }

    public static FuncCallNode parseFuncCall (ArrayList<Token> tokenArrayList) throws SyntaxException{
        IdNode idNode;
        ParamsNode paramsNode = null;
        Token token = tokenArrayList.get(0);

        if (token.getTokenType() == TokenType.FC_HEADER) {
            tokenArrayList.remove(0);
            token = tokenArrayList.get(0);
            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.parseId(tokenArrayList);

                // start of [
                token = tokenArrayList.get(0);
                if (token.getTokenType() != TokenType.L_BRACKET) {
                    throw new SyntaxException("Expected a [ Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenArrayList.remove(0);

                paramsNode = ParamsNode.parseParams(tokenArrayList);

                // start of ]
                token = tokenArrayList.get(0);
                if (token.getTokenType() != TokenType.R_BRACKET) {
                    throw new SyntaxException("Expected a ] Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenArrayList.remove(0);

            } else throw new SyntaxException("Expected an Id. Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());
        } else throw new SyntaxException("Expected a Function Header. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

        return new FuncCallNode(idNode, paramsNode);
    }

    public String convertToJott(){
        return "::" + this.id.convertToJott() + "[" + params.convertToJott() + "]";
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
