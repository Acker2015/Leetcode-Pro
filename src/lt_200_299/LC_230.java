package lt_200_299;

import domain.TreeNode;

/**
 * [230] Kth Smallest Element in a BST
 */
public class LC_230 {
    private int subTreeNodeNum(TreeNode node) {
        if (node == null) return 0;
        return 1 + subTreeNodeNum(node.left) + subTreeNodeNum(node.right);
    }

    /**
     * BST tree - binarySearch
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k < 0) return -1;
        int leftNum = subTreeNodeNum(root.left);
        if (leftNum == k-1) {
            return root.val;
        } else if (leftNum > k - 1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k-leftNum-1);
        }
    }
}
