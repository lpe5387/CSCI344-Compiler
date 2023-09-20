package treeNodes;

/**
 * This class is responsible for the additional parameters node for the parse tree
 *
 * @author Luka Eaton
 */


public class ParamsTNode implements JottTree {

    private boolean isEmpty;

    private ExprNode expr;

    private ParamsTNode paramsT;

    public ParamsTNode(ExprNode expr, ParamsTNode paramsT){
        this.expr = expr;
        this.paramsT = paramsT;
        this.isEmpty = false;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}