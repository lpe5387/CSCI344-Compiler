public class ExprNode implements JottTree {
    
    private String bool;
    private FuncCallNode func;
    private String op;
    private String id;
    private String num;
    private String str;

    private ExprNode expr;

    public ExprNode(String value, String checkType){
        if(checkType.equals("bool")) this.bool = value;
        else if(checkType.equals("id")) this.id = value;
        else if(checkType.equals("num")) this.num = value;
        else if(checkType.equals("str_literal")) this.str = value;
    }

    public ExprNode(FuncCallNode func){
        this.func = func;
    }

    public ExprNode(FuncCallNode func, String op, ExprNode expr){
        this.func = func;
        this.op = op;
        this.expr = expr;
    }

    public ExprNode(String value, String op, ExprNode expr, String checkType){
        if(checkType.equals("id")) this.id = value;
        else if(checkType.equals("num")) this.num = value;
        this.op = op;
        this.expr = expr;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
