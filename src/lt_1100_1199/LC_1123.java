package lt_1100_1199;

import domain.TreeNode;

/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 *
 * 直接常规DFS
 */
public class LC_1123 {
    private class Tuple {
        TreeNode node;
        int d;
    }
    private Tuple dfs(TreeNode node) {
        if (node == null) {
            return new Tuple();
        }
        Tuple left = dfs(node.left);
        Tuple right = dfs(node.right);
        int d = Math.max(left.d, right.d) + 1;
        if (left.d == right.d) {
            left.node = node;
        } else if (left.d < right.d) {
            left.node = right.node;
        }
        left.d = d;
        return left;
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }
}
