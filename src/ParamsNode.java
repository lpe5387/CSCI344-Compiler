/**
 * This class is responsible for the parameters node for the parse tree
 *
 * @author Luka Eaton, lucie lim
 */
public class ParamsNode implements JottTree {

    private boolean isEmpty;

    private ExprNode expr;

    private ParamsTNode paramsT;

    public ParamsNode(){
        this.isEmpty = true;
    }

    public ParamsNode(ExprNode expr, ParamsTNode paramsT){
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
