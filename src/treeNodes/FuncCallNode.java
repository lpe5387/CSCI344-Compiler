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

public class FuncCallNode implements JottTree, ExprNode, BodyStmtNode {

    /**
     * did not handle the  [ ] tokens
     */

    private FuncHeaderNode funcHeaderNode;
    private IdNode id;
    private ParamsNode params;

    public FuncCallNode(FuncHeaderNode funcHeaderNode, IdNode id, ParamsNode params) {
        this.funcHeaderNode = funcHeaderNode;
        this.id = id;
        this.params = params;
    }

    public FuncCallNode ParseFuncCallNode (ArrayList<Token> tokenArrayList) throws SyntaxException{
        FuncHeaderNode funcHeaderNode;
        IdNode idNode;
        ParamsNode paramsNode = null;
        Token token = tokenArrayList.get(0);

        if (token.getTokenType() == TokenType.FC_HEADER) {
            funcHeaderNode = FuncHeaderNode.ParseFuncHeader(tokenArrayList);
            token = tokenArrayList.get(0);
            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.ParseId(tokenArrayList);
                token = tokenArrayList.get(0);

                //assumes we have params
                // [ should start here
                token = tokenArrayList.get(0);
                tokenArrayList.remove(0);
                if (token.getTokenType() == TokenType.ID_KEYWORD) {
                    paramsNode = ParamsNode.ParseParams(tokenArrayList);
                }
                // ] should end here
                token = tokenArrayList.get(0);
                tokenArrayList.remove(0);

            } else throw new SyntaxException("Expected an Id. Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());
        } else throw new SyntaxException("Expected a Function Header. Got: "+ token.getToken(),
                token.getFilename(), token.getLineNum());

        return new FuncCallNode(funcHeaderNode, idNode, paramsNode);
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
