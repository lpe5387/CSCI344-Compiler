package SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for storing all the defined functions, and variables within their scopes.
 *
 * @author Dara Prak, Luka Eaton, Lucie Lim, Andrew Dantone
 */

public class SymbolTable {
    private static HashMap<String, ArrayList<String>> funcDefs = new HashMap<>();
    private static HashMap<String, HashMap<String, ArrayList<String>>> varDefs = new HashMap<>();
    private static String currentScope = null;

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

    public static void setCurrentScope(String scope) {
        currentScope = scope;
    }

}
