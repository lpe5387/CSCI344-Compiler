/**
 * This class is responsible for the expression node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;

public class ExprNode implements JottTree {
    
    private BoolNode bool;
    private FuncCallNode func;
    private OpNode op;
    private IdNode id;
    private NumNode num;
    private StringLiteralNode str;

    private ExprNode expr;

    public ExprNode(StringLiteralNode str){
        this.str = str;
    }

    public ExprNode(NumNode num){
        this.num = num;
    }

    public ExprNode(BoolNode bool){
        this.bool = bool;
    }

    public ExprNode(IdNode id){
        this.id = id;
    }

    public ExprNode(FuncCallNode func){
        this.func = func;
    }

    public ExprNode(FuncCallNode func, OpNode op, ExprNode expr){
        this.func = func;
        this.op = op;
        this.expr = expr;
    }

    public ExprNode(NumNode num, OpNode op, ExprNode expr){
        this.num = num;
        this.op = op;
        this.expr = expr;
    }

    public ExprNode(IdNode id, OpNode op, ExprNode expr){
        this.id = id;
        this.op = op;
        this.expr = expr;
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
