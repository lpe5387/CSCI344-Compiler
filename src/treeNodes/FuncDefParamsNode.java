package treeNodes;

/**
 * This class is responsible for the function definition parameters node for the parse tree
 *
 * @author Luka Eaton
 */

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class FuncDefParamsNode implements JottTree {
    
    private IdNode id;
    private TypeNode type;

    private ArrayList<FuncDefParamsTNode> funcDefParamsTList;

    public FuncDefParamsNode(IdNode id, TypeNode type, ArrayList<FuncDefParamsTNode> funcDefParamsTList){
        this.id = id;
        this.type = type;
        this.funcDefParamsTList = funcDefParamsTList;
    }

    public static FuncDefParamsNode parseFuncDefParams(ArrayList<Token> tokenlist) throws SyntaxException {
        Token token = tokenlist.get(0);
        if(token.getTokenType() == TokenType.ID_KEYWORD){
            IdNode idNode = IdNode.parseId(tokenlist);
            token = tokenlist.get(0);
            if(token.getTokenType() == TokenType.COLON) {
                TypeNode type = TypeNode.parseType(tokenlist);
                ArrayList<FuncDefParamsTNode> funcDefParamsTNodes = new ArrayList<>();
                while (tokenlist.get(0).getTokenType() == TokenType.COMMA) {
                    funcDefParamsTNodes.add(FuncDefParamsTNode.ParseFuncDefParamsT(tokenlist));
                }
                FuncDefParamsNode node = new FuncDefParamsNode(idNode, type, funcDefParamsTNodes);
                return node;
            }
        } else {
            return null;
        }

    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
