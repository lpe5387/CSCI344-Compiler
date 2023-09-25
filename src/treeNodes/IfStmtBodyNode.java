package treeNodes;

/**
 *
 * @author Dara Prak, lucie lim
 */

public class IfStmtBodyNode extends BodyStmtNode {

    private IfStmtNode ifStmt;

    public IfStmtBodyNode(IfStmtNode ifStmt, BodyStmtNode bodystmt) {
        super(bodystmt);
        this.ifStmt = ifStmt;
    }


}
