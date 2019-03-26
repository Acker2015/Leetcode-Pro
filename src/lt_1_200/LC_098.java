package lt_1_200;

import domain.TreeNode;


/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 *
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class LC_098 {

    public boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    /**
     * 对于树，记录子树的最大与最小值
     * 1. 节点中的值有可能是Integer.MIN_VALUE or Integer.MAX_VALUE, 最大最小值要使用long
     * 2. 左子树所有节点值小于根节点值，右子树所有节点值大于根节点值。对子树进行递归的时候改变子树的取值区间
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
