package treeNodes; /**
 * This class is responsible for the ID node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import SymbolTable.SymbolTable;
import java.util.ArrayList;

import exceptions.SemanticException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class IdNode implements ExprNode {
 
    private Token token;

    public IdNode(Token token){
        this.token = token;
    }

    /**
     * Function used to Parse an id into an treeNodes.IdNode for the parse tree.
     * @param tokenlist the list of tokens generated by the Tokenizer
     * @return treeNodes.IdNode
     * @throws SyntaxException
     */
    public static IdNode parseId(ArrayList<Token> tokenlist) throws SyntaxException {
        if(!tokenlist.isEmpty()) {
            Token token = tokenlist.get(0);
            if (token.getTokenType() == TokenType.ID_KEYWORD) {
                IdNode node = new IdNode(token);
                tokenlist.remove(0);
                return node;
            } else {
                throw new SyntaxException("Expected an ID/Keyword, got " + token.getTokenType(), token.getFilename(), token.getLineNum());
            }
        }
        else throw new SyntaxException("Unexpected end of file");
    }

    public boolean isBooleanExpression() throws SemanticException {
        ArrayList<String> results = SymbolTable.getVarDef(this.token.getToken());
        if(results ==  null){
            throw new SemanticException("Variable '" + this.token.getToken() + "' does not exist.", this.token.getFilename(), this.token.getLineNum());
        }
        if(!results.get(0).equals("Boolean")){
            throw new SemanticException("Variable '" + this.token.getToken() + "' is not of type 'Boolean'.", this.token.getFilename(), this.token.getLineNum());
        }
        if(results.get(1).equals("no")){
            throw new SemanticException("Variable '" + this.token.getToken() + "' is not instantiated.", this.token.getFilename(), this.token.getLineNum());
        }
        return true;
    }

    public String convertToJott(){
        return this.token.getToken();
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

    public Token getToken() {
        return this.token;
    }
}
