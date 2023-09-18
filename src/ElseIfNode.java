/**
 * This class is responsible for the else if node for the parse tree
 *
 * @author Luka Eaton
 */


public class ElseIfNode implements JottTree {
    
    private ExprNode expr;

    private BodyNode body;

    private ElseIfNode elseIf;

    public ElseIfNode(ExprNode expr, BodyNode body, ElseIfNode elseIf){
        this.expr = expr;
        this.body = body;
        this.elseIf = elseIf;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
