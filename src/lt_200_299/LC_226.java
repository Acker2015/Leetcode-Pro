package lt_200_299;

import domain.TreeNode;

/**
 * [226] Invert Binary Tree
 */
public class LC_226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = invertTree(rightNode);
        root.right = invertTree(leftNode);
        return root;
    }
}
