package lt_500_599;


import domain.TreeNode;

/**
 * [513] Find Bottom Left Tree Value
 */
public class LC_513 {
    private class WrapNode {
        int height;
        int val;
        WrapNode(int h, int v) {
            this.height = h;
            this.val = v;
        }
    }

    private void find(TreeNode root, WrapNode wrapNode, int depth) {
        if (root == null) return;
        if (depth > wrapNode.height) {
            wrapNode.height = depth;
            wrapNode.val = root.val;
        }
        find(root.left, wrapNode, depth+1);
        find(root.right, wrapNode, depth+1);

    }
    // DFS
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        WrapNode wrapNode = new WrapNode(0, root.val);
        find(root, wrapNode, 0);
        return wrapNode.val;
    }
}
