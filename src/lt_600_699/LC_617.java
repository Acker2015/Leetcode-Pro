package lt_600_699;

import domain.TreeNode;

/**
 *
 */
public class LC_617 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null || t2 == null) {
            return t1==null ? t2: t1;
        }
        TreeNode node = new TreeNode(t1.val+t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
