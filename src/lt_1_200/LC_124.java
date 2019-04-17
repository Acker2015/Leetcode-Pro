package lt_1_200;

import domain.TreeNode;

public class LC_124 {
    /**
     * dfs
     * [124] Binary Tree Maximum Path Sum
     * define maxSinglePath of node is max sum of path that is from node to its subTree(single path)
     *
     * maxPathSum = node.val + Max(maxSinglePath of node.left, 0) + Math.max(maxSinglePath of node.right, 0)
     * dfs每次返回子树的最大路径的和即可 maxSinglePath
     * return node.val + Max(maxSinglePath of node.left, maxSinglePath of node.right, 0)
     *
     */
    private int maxPath;
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int maxLeftPath = dfs(root.left);
        int maxRightPath = dfs(root.right);
        int curMaxPath = root.val;
        if (maxLeftPath > 0) {
            curMaxPath += maxLeftPath;
        }
        if (maxRightPath > 0) {
            curMaxPath += maxRightPath;
        }
        maxPath = Math.max(maxPath, curMaxPath);
        int ans = Math.max(maxLeftPath, maxRightPath);
        return root.val + (ans < 0 ? 0: ans);
    }
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        maxPath = root.val;
        dfs(root);
        return maxPath;
    }

    public static void main(String ...args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-9);
        LC_124 lc_124 = new LC_124();
        System.out.println(lc_124.maxPathSum(root));
    }
}
