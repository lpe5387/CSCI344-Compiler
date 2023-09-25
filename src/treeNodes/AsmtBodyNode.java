package treeNodes;

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
}
