package treeNodes;

/**
 * This class is responsible for the program node for the parse tree
 *
 * @author Luka Eaton
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class ProgramNode implements JottTree {
    
    private ArrayList<FuncDefNode> funcDefList;

    public ProgramNode(ArrayList<FuncDefNode> funcDefList){
        this.funcDefList = funcDefList;
    }

    public static ProgramNode parseProgram(ArrayList<Token> tokenlist) throws SyntaxException {
        ArrayList<FuncDefNode> funcDefList = new ArrayList<>();
        while(!tokenlist.isEmpty()){
            FuncDefNode funcDef = FuncDefNode.parseFuncDef(tokenlist);
            funcDefList.add(funcDef);
        }
        return new ProgramNode(funcDefList);
    }

    public String convertToJott(){
        String programString = "";
        for(FuncDefNode funcDef : funcDefList){
            programString += funcDef.convertToJott();
        }
        return programString;
    }

    public String convertToJava(String className){return "";}

    public String convertToC(){return "";}

    public String convertToPython(){return "";}
    
    public boolean validateTree() throws SemanticException {
        boolean main = false;
        //ensures we have a main function def
        for(FuncDefNode funcDef : funcDefList){
            funcDef.validateTree();
            //check for the main func in the list of func defs
            if (funcDef.getId().getToken().getToken().equals("main") ) {
                // check for the return type: void
                // != null means that there is a void value
                if ( funcDef.getReturnType().getVoidReturn() != null) {
                    // check num of params: should be empty
                    // null = no param nodes
                    if ( funcDef.getFuncDefParams() == null) {
                        main = true;
                    } else {
                        throw new SemanticException("Main is not suppose to have parameters",
                                funcDef.getFuncDefParams().getId().getToken().getFilename(),
                                funcDef.getFuncDefParams().getId().getToken().getLineNum());
                    }
                } else {
                    throw new SemanticException("Main needs to return void type",
                            funcDef.getReturnType().getTypeNode().getToken().getFilename(),
                            funcDef.getReturnType().getTypeNode().getToken().getLineNum());
                }
            }
        }

        if (!main) {
            throw new SemanticException("No main function defined");
        }

        return true;
    }



}
