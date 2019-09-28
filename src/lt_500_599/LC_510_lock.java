package lt_500_599;

import domain.TreeNode;

/**
 * [LeetCode] 510. Inorder Successor in BST II
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 * You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.
 *
 * Follow up:
 * Could you solve it without looking up any of the node's values?
 *
 * how to search for the successor node?
 * 1. if node has right child treeNode p, then the leftest treeNode of p would be successor of node.
 * 2. if node has not right child treeNode, then the successor may exist at the
 *
 */
public class LC_510_lock {
    TreeNode inorderSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            TreeNode ans = node.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return ans;
        } else {
            // 往父节点向上找第一个大于当前节点的父节点(BST性质，父节点大于左孩子)
            while (node.parent != null) {
                if (node.parent.left == node) {
                    return node.parent;
                }
                node = node.parent;
            }
            return null;
        }
    }
}
