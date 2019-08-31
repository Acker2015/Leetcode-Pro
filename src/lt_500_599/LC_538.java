package lt_500_599;

import domain.TreeNode;

/**
 *
 * [538] Convert BST to Greater Tree
 * DFS, 右中左
 */
public class LC_538 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        /**
         * 右中左遍历
         * node表示节点
         * preSum 表示传入的需要增加的节点和
         *
         * return 子树的节点和
         */
        private int dfs(TreeNode node, int preSum) {
            if (node == null) {
                return 0;
            }
            int tmp = node.val;
            int rv = dfs(node.right, preSum);
            node.val += rv + preSum;    // 加上右子树所有节点值的和
            int lv = dfs(node.left, node.val);  // 想一下 这里为啥传入的是node.val
            return tmp + lv + rv;   // 返回子树和（左子树和+右子树和+当前节点值）

        }
        public TreeNode convertBST(TreeNode root) {
            dfs(root, 0);
            return root;
        }
    }
}
