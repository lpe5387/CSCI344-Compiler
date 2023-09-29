package treeNodes;

/**
 * This class is responsible for the parameters node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */

import provided.JottTree;
import java.util.ArrayList;

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

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
