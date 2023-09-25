package treeNodes;

/**
 * This class is responsible for identifying VarDecNodes in the body statement node
 *
 * @author lucie lim, Dara Prak
 */

public class VarDecBodyNode extends BodyStmtNode {

    private VarDecNode varDecNode;

    public VarDecBodyNode (VarDecNode varDecNode, BodyStmtNode bodyStmt) {
        super(bodyStmt);
        this.varDecNode = varDecNode;
    }
}
