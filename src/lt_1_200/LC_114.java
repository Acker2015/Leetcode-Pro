package lt_1_200;

import domain.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class LC_114 {
    /**
     *
     * [114] Flatten Binary Tree to Linked List
     *
     * 返回子树平坦化的尾节点（非空）
     * 对于节点root
     * 1. 记录右子树节点为tmp
     * 2. 将左子树根置为root节点的右子树，并将root节点左子树切断。 即root.right=root.left; root.left=null
     * 3. 递归平坦化左子树，返回左子树平坦化之后的尾节点 leftTailNode
     * 4. 左子树平坦化尾节点连接root的右子树节点tmp, leftTailNode.right = tmp
     * 5. 平坦化右子树，并返回平坦化右子树的尾节点 作为此次迭代树的平滑化尾节点
     * 首先将左子树打平，并记录打平之后的尾节点，
     */
    private TreeNode flat(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode leftTailNode = root.right==null ? root : flat(root.right);
        leftTailNode.right = tmp;
        TreeNode rightTailNode = tmp==null ? leftTailNode : flat(tmp);
        return rightTailNode;
    }
    public void flatten(TreeNode root) {
        flat(root);
    }
}
