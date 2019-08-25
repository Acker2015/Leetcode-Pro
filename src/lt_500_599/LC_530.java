package lt_500_599;

import domain.TreeNode;

/**
 * [530] Minimum Absolute Difference in BST
 *
 * 对于每一个节点 记录离他最近的更小节点值less，以及离他最近的更大的节点值more
 *
 * 往左走的时候(node.left), 更新more [go to the left child, update the more value]
 * 往右走的时候(node.right),更新less [go to the right child, update the less value]
 *
 */
public class LC_530 {
    private long minVal;
    private void dfs(TreeNode node, long less, long more) {
        if (node == null) return;
        long lessDis = Math.abs(less-node.val);
        long moreDis = Math.abs(more-node.val);
        minVal = Math.min(minVal, Math.min(lessDis, moreDis));
        // left -> update more
        dfs(node.left, less, node.val);
        // right -> update less
        dfs(node.right, node.val, more);
    }
    public int getMinimumDifference(TreeNode root) {
        minVal = Integer.MAX_VALUE;
        if (root == null || (root.left==null&&root.right==null)) {
            return -1;
        }
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return (int)minVal;
    }
}
