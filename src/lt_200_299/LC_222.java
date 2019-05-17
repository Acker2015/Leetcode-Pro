package lt_200_299;


import domain.TreeNode;

/**
 * [222] Count Complete Tree Nodes(https://leetcode.com/problems/count-complete-tree-nodes/description/)
 * binary-search
 */
public class LC_222 {
    private int getHeight(TreeNode node, boolean isLeft) {
        int h = 0;
        while (node!=null) {
            h++;
            node = isLeft ? node.left : node.right;
        }
        return h;
    }
    /**
     * 利用完全二叉树性质
     * 如果是满二叉树，那么节点个数为 2^height-1, height为数的高度
     * 如果不是满二叉树，则左右子树分别递归
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root, true);
        int rightHeight = getHeight(root, false);
        if (leftHeight == rightHeight) {
            return (int)Math.pow(2, leftHeight)-1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}
