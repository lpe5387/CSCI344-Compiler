package helpers;

import exceptions.SemanticException;
import treeNodes.ExprNode;
import treeNodes.ParamsNode;

public class CConversions {

    public static String printC(ParamsNode params) throws SemanticException {
        String cString = "printf(\"";
        if (params.getExpr().evaluateType().equals("Integer") || (params.getExpr().evaluateType().equals("Boolean")) ){
            cString += "%d\\n\"," + params.getExpr().convertToC() + ")";
        } else if (params.getExpr().evaluateType().equals("Double")) {
            cString += "%f\\n\"," + params.getExpr().convertToC() + ")";
        }  else {
            cString += "%s\\n\"," + params.getExpr().convertToC() + ")";
        }
        return cString;
    }

}
