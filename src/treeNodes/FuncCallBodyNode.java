package treeNodes;

/**
 * This class is responsible for identifying FuncCallNodes in the body statement node
 *
 * @author Dara Prak, lucie lim
 */

public class FuncCallBodyNode extends BodyStmtNode {

    private FuncCallNode funcCallNode;

    public  FuncCallBodyNode (FuncCallNode funcCallNode, BodyStmtNode bodyStmt) {
        super(bodyStmt);
        this.funcCallNode = funcCallNode;
    }
}
