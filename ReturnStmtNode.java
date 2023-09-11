public class ReturnStmtNode implements JottTree {

    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr){
        this.expr = expr;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
