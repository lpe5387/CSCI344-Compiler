package treeNodes;

/**
 * This class is responsible for the parameters node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.TokenType;
import exceptions.SyntaxException;

public class ParamsNode implements JottTree {

    private boolean isEmpty;

    private ExprNode expr;

    private ArrayList<ParamsTNode> paramsTList;

    public ParamsNode(){
        this.isEmpty = true;
    }

    public ParamsNode(ExprNode expr, ArrayList<ParamsTNode> paramsTList){
        this.expr = expr;
        this.paramsTList = paramsTList;
        this.isEmpty = false;
    }

    public static ParamsNode parseParams(ArrayList<Token> tokenlist) throws SyntaxException{
        ExprNode expr = ExprNode.parseExpr(tokenlist); //gets exprNode
        ArrayList<ParamsTNode> paramsTList = new ArrayList<>(); //initializes an array of ParamsTNodes
        while(!tokenlist.isEmpty() && tokenlist.get(0).getTokenType() == TokenType.COMMA){ //while token after first expr node/ParamsTNode is a comma that means  there is another ParamsTNode
            paramsTList.add(ParamsTNode.parseParamT(tokenlist)); //add next ParamsTNode to list
        }
        ParamsNode node = new ParamsNode(expr, paramsTList);
        return node;
    }

    public String convertToJott(){
        StringBuilder toString = new StringBuilder(this.expr.convertToJott());
        for (ParamsTNode i : this.paramsTList){
            toString.append(i.convertToJott());
        }
        return toString.toString();
    }

    public boolean getIsEmpty(){return this.isEmpty;};

    public ExprNode getExpr() {return this.expr;}

    public ArrayList<ParamsTNode> getParamsTList(){return this.paramsTList;}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
