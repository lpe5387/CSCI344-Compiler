public class WhileLoopNode implements JottTree {
    
    private ExprNode expr;

    private BodyNode body;

    public WhileLoopNode(ExprNode expr, BodyNode body){
        this.expr = expr;
        this.body = body;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
