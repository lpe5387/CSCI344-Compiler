package treeNodes;
/*
This class is for the bool subset of the expression node

Author: Luka Eaton
 */
import java.util.ArrayList;
import provided.Token;
import exceptions.SyntaxException;
public class BoolExprNode extends ExprNode{

    private BoolNode bool;

    public BoolExprNode(BoolNode bool){
        this.bool = bool;
    }

    public static BoolExprNode ParseBoolExpr(ArrayList<Token> tokenList) throws SyntaxException {
        BoolNode bool = BoolNode.ParseBool(tokenList);
        return new BoolExprNode(bool);
    }

}
