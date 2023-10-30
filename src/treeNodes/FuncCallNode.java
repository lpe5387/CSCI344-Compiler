package treeNodes;

/**
 * This class is responsible for the function call node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import SymbolTable.SymbolTable;
import exceptions.SemanticException;
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
        //check if the tokenlist is not empty
        if (tokenArrayList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }
        Token token = tokenArrayList.get(0);

        if (token.getTokenType() == TokenType.FC_HEADER) {
            tokenArrayList.remove(0);

            //check if the tokenlist is not empty
            if (tokenArrayList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }

            token = tokenArrayList.get(0);

            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                idNode = IdNode.parseId(tokenArrayList);

                //check if the tokenlist is not empty
                if (tokenArrayList.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }

                // start of [
                token = tokenArrayList.get(0);
                if (token.getTokenType() != TokenType.L_BRACKET) {
                    throw new SyntaxException("Expected a [ Got: " + token.getToken(),
                            token.getFilename(), token.getLineNum());
                }
                tokenArrayList.remove(0);

                paramsNode = ParamsNode.parseParams(tokenArrayList);

                //check if the tokenlist is not empty
                if (tokenArrayList.isEmpty()) {
                    throw new SyntaxException("Unexpected end of file");
                }
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

    public boolean isBooleanExpression() throws SemanticException {
        ArrayList<String> results = SymbolTable.getFuncDef(this.id.getToken().getToken());
        if(results == null){
            throw new SemanticException("Function '" + this.id.getToken().getToken() + "' does not exist.", this.id.getToken().getFilename(), this.id.getToken().getLineNum());
        }
        if(!results.get(results.size()-1).equals("Boolean")){
            throw new SemanticException("Function '" + this.id.getToken().getToken() + "' does not return the type 'Boolean'.", this.id.getToken().getFilename(), this.id.getToken().getLineNum());
        }
        return true;
    }
}
