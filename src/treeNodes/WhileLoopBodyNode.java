package treeNodes;

/**
 * This class is responsible for identifying WhileLoopNodes in the body statement node
 *
 * @author lucie lim, Dara Prak
 */

public class WhileLoopBodyNode extends BodyStmtNode {

    private WhileLoopNode whileLoopNode;

    public WhileLoopBodyNode ( WhileLoopNode whileLoopNode, BodyStmtNode bodyStmt) {
        super(bodyStmt);
        this.whileLoopNode = whileLoopNode;
    }
}
