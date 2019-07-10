package lt_500_599;

import domain.TreeNode;

/**
 * [543] Diameter of Binary Tree
 *
 * 直接DFS
 */
public class LC_543 {
    private int maxDiameter;
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);
        maxDiameter = Math.max(maxDiameter, leftHeight+rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        dfs(root);
        return maxDiameter;
    }
}
