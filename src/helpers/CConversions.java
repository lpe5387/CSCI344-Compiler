package helpers;

import exceptions.SemanticException;
import treeNodes.ParamsNode;

public class CConversions {

    public static String printC(ParamsNode params) throws SemanticException {
        String cString = "printf(\"";
        if (params.getExpr().evaluateType().equals("Integer") || (params.getExpr().evaluateType().equals("Boolean")) ){
            cString += "%d\"," + params.getExpr().convertToC() + ")";
        } else if (params.getExpr().evaluateType().equals("Double")) {
            cString += "%f\"," + params.getExpr().convertToC() + ")";
        }  else {
            cString += "%s\"," + params.getExpr().convertToC() + ")";
        }
        return cString;
    }

    public static String concatC( ParamsNode params ) throws SemanticException {
        String cString = "";
        //math for byte size string
        int byteSize = params.getExpr()

    }

}
