package treeNodes;

/**
 * This class is responsible for the assignment node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.Token;
import provided.TokenType;
import SymbolTable.SymbolTable;

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

    public static AsmtNode parseAsmt(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        TypeNode typeNode;
        IdNode idNode;
        ExprNode exprNode;

        //before getting a token from the token list we check if the list is empty
        if (tokenList.isEmpty()) {
            throw new SyntaxException("Unexpected end of file");
        }

        Token token = tokenList.get(0);

        //before we look ahead we also check if theres enough tokens to look ahead
        if ( tokenList.size() < 2 ) {
            throw new SyntaxException("Unexpected end of file");
        }

        Token lookAhead = tokenList.get(1);

        //check the content of the token
        //checks <id>=<expr>
        if (token.getTokenType() == TokenType.ID_KEYWORD && lookAhead.getTokenType() == TokenType.ASSIGN) {
            idNode = IdNode.parseId(tokenList);

            //check if the name already exists in the symbol table
            if (SymbolTable.getVarDef(idNode.getToken().getToken()) == null) {
                throw new SemanticException("Assignment of variable without declaring type: " +
                        idNode.getToken().getToken(), idNode.getToken().getFilename(), idNode.getToken().getLineNum());
            }

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }
            // removes the "=" token
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.ASSIGN) {
                throw new SyntaxException("Expected a = Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            //make expr node
            exprNode = ExprNode.parseExpr(tokenList);

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }
            //removes ;
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            //Name of var exists in symbol table therefore update the instantiation status of var
            ArrayList<String> varDetails = SymbolTable.getVarDef(idNode.getToken().getToken());
            if (varDetails.get(1).equals("no")) {                               // if the var was not instantiated
                varDetails.set(1, "yes");                                       // update to yes in the table
            }

            return new AsmtNode(idNode, exprNode);

        } else if (token.getTokenType() == TokenType.ID_KEYWORD) {
            //checks <type><id>=<expr>

            typeNode = TypeNode.parseType(tokenList);
            idNode = IdNode.parseId(tokenList);

            //check if the name already exists in the symbol table
            if (SymbolTable.getVarDef(idNode.getToken().getToken()) != null) {
                throw new SemanticException("Variable name: " + idNode.getToken().getToken() + " of type: " +
                        typeNode.getToken().getToken() + ", already used ",
                        typeNode.getToken().getFilename(), typeNode.getToken().getLineNum());
            }

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }
            // remove the "="
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.ASSIGN) {
                throw new SyntaxException("Expected a = Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            exprNode = ExprNode.parseExpr(tokenList);

            //check if the tokenlist is not empty
            if (tokenList.isEmpty()) {
                throw new SyntaxException("Unexpected end of file");
            }
            // remove the ;
            token = tokenList.get(0);
            if (token.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxException("Expected a ; Got: " + token.getToken(),
                        token.getFilename(), token.getLineNum());
            }
            tokenList.remove(0);

            //name of var doesn't exist in symbol table therefore put it into the table
            ArrayList<String> varDetails = new ArrayList<>();
            varDetails.add(typeNode.getToken().getToken());                     // adds the type of the variable
            varDetails.add("yes");                                              // confirms instantiation
            SymbolTable.addVarDef(idNode.getToken().getToken(), varDetails);    // adds new asmt var to symbol table

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
    
    public boolean validateTree() throws SemanticException {
        // ensure type matches the actual assignment for new variable assignment
        String type = this.type.getToken().getToken();              // gets the type of the var

        if ( !type.equals( this.expr.evaluateType() ) ) {            // if var type != type of expression throw error
            throw new SemanticException("Variable type doesn't match with assignment type.\n " +
                    this.type.getToken().getToken() + " " + this.id.getToken().getToken() + " = " +
                    this.expr.toString(), this.id.getToken().getFilename(), this.id.getToken().getLineNum());
        }
        // ensure name isn't already taken: already handled in the parser when before we add keys to the symbol table
        return true;
    }

}
