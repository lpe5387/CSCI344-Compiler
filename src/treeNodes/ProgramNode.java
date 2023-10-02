package treeNodes;

/**
 * This class is responsible for the program node for the parse tree
 *
 * @author Luka Eaton
 */

import provided.JottTree;
import provided.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProgramNode implements JottTree {
    
    private ArrayList<FuncDefNode> funcDefList;

    public ProgramNode(ArrayList<FuncDefNode> funcDefList){
        this.funcDefList = funcDefList;
    }

    public static ProgramNode parseProgram(ArrayList<Token> tokenlist){
        ArrayList<FuncDefNode> funcDefList = new ArrayList<>();
        while(!tokenlist.isEmpty()){
            FuncDefNode funcDef = FuncDefNode.ParseFuncDef(tokenlist);
            funcDefList.add(funcDef);
        }
        return new ProgramNode(funcDefList);
    }

    public String convertToJott(){return "";}

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree(){return true;}

}
