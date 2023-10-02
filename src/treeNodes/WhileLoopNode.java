package treeNodes;

/**
 * This class is responsible for the while loop node for the parse tree
 *
 * @author Luka Eaton, Lucie Lim
 */

import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class WhileLoopNode implements JottTree, BodyStmtNode {

    /**
     * Idk what to do about the brackets, do i keep the private attributes of it?
     * I think no because it seems very inefficient and repetitive
     */

    private Token whileToken;
    private Token leftBracket;
    private ExprNode expr;
    private Token rightBracket;
    private Token leftCurlyBracket;
    private BodyNode body;
    private Token rightCurlyBracket;

    public WhileLoopNode(Token whileToken, ExprNode expr, BodyNode body){
        this.whileToken = whileToken;

        this.expr = expr;
        this.body = body;
    }

    public static WhileLoopNode parseWhileLoopNode(ArrayList<Token> tokenlist) throws SyntaxException {
        // init to null because the var being returned might be uninit
        ExprNode exprNode = null;
        BodyNode bodyNode = null;
        //get the token from the token list
        Token token = tokenlist.get(0);

        //check the type of the token
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            //check if its "while"
            if (token.equals("while")) {
                //we know its a whileloop node so we remove that token from the list and send the token list to ExprNode
                tokenlist.remove(0);

                // start of [
                token = tokenlist.get(0);
                tokenlist.remove(0);
                exprNode = ExprNode.ParseExpr(tokenlist);
                // end of ]
                token = tokenlist.get(0);
                tokenlist.remove(0);

                // start of {
                token = tokenlist.get(0);
                tokenlist.remove(0);
                //the parseexpr shouldve removed the tokens for an expr so whats left should goto the bodynode parser
                bodyNode = BodyNode.ParseBodyNode(tokenlist);
                // end of }
                token = tokenlist.get(0);
                tokenlist.remove(0);

                /*
                * check how to print "
                 */
            } else throw new SyntaxException("Expected \"while\" Got: "+ token.getToken(),
                    token.getFilename(), token.getLineNum());
        }
        return new WhileLoopNode(token, exprNode, bodyNode);
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
