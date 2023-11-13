package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class is responsible for storing all the defined functions, and variables within their scopes.
 *
 * @author Dara Prak, Luka Eaton, Lucie Lim, Andrew Dantone, Issac Kim
 */

public class SymbolTable {
    private static HashMap<String, ArrayList<String>> funcDefs = new HashMap<>();

    private static HashMap<String, HashMap<String, ArrayList<String>>> varDefs = new HashMap<>();
    private static String currentScope = null;

    public static HashSet<String> reservedWords = new HashSet<>();

    public static ArrayList<String> getFuncDef(String funcName) {
        return funcDefs.get(funcName);
    }

    public static ArrayList<String> getVarDef(String varName) {
        return varDefs.get(currentScope).get(varName);
    }

    /**
     *
     * @param funcName name of the function
     * @param funcDetails parameter types, then return type
     */
    public static void addFuncDef(String funcName, ArrayList<String> funcDetails) {
        funcDefs.put(funcName, funcDetails);
        varDefs.put(funcName, new HashMap<String, ArrayList<String>>());
    }

    public static void addVarDef(String varName, ArrayList<String> varDetails) {
        HashMap<String, ArrayList<String>> varMap = varDefs.get(currentScope);
        varMap.put(varName, varDetails);
    }

    public static String getCurrentScope(){ return currentScope; }

    public static void setCurrentScope(String scope) {
        currentScope = scope;
    }

    public static void bootUp(){
        ArrayList<String> print = new ArrayList<>();
        print.add("Any");
        print.add("Void");
        ArrayList<String> concat = new ArrayList<>();
        concat.add("String");
        concat.add("String");
        concat.add("String");
        ArrayList<String> length = new ArrayList<>();
        length.add("String");
        length.add("Integer");
        addFuncDef("print", print);
        addFuncDef("concat", concat);
        addFuncDef("length", length);
        reservedWords.add("if");
        reservedWords.add("else");
        reservedWords.add("elseif");
        reservedWords.add("while");
        reservedWords.add("def");
        reservedWords.add("Integer");
        reservedWords.add("Double");
        reservedWords.add("Boolean");
        reservedWords.add("String");
        reservedWords.add("Void");
        reservedWords.add("return");
        reservedWords.add("True");
        reservedWords.add("False");
    }

}
