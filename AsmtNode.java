public class AsmtNode implements JottTree {
    
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

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}


}
