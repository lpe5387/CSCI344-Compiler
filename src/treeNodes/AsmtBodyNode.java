package treeNodes;

import provided.Token;
import java.util.ArrayList;
import exceptions.SyntaxException;

/**
 * This class is responsible for identifying AsmtNode in the body statement node
 *
 * @author Dara Prak, lucie lim
 */

public class AsmtBodyNode extends BodyStmtNode {

    private AsmtNode asmtNode;

    public AsmtBodyNode (AsmtNode asmtNode, BodyStmtNode bodyStmt) {
        super(bodyStmt);
        this.asmtNode = asmtNode;
    }

    //assumes the token being  sent is an asmtbody node
    public AsmtBodyNode parserAsmtBodyNode (ArrayList<Token> tokenList ) {
        AsmtNode asmtNode = parserAsmtNode(tokenList);
        //calls the parent parser from body stmt node
        BodyStmtNode bodyStmtNode = parseBodyStmtNode(tokenList);
        return new AsmtBodyNode(asmtNode, bodyStmtNode);
    }
}
