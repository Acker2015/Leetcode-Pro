package lt_400_499;


import domain.TreeNode;
// [404] Sum of Left Leaves
public class LC_404 {
    private int leftLeavesSum(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (isLeft && root.left == null && root.right ==null) {
            return root.val;
        }
        return leftLeavesSum(root.left, true) + leftLeavesSum(root.right, false);
    }
    /**
     * Solution1
     * DFS
     * 通过记录此节点是否为来源父节点的左孩子还是右孩子来辅助计算
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root==null) return 0;
        return leftLeavesSum(root, false);
    }




    private boolean isLeaf(TreeNode node) {
        return node!=null && node.left==null && node.right == null;
    }
    /**
     * Solution2
     * DFS
     * 判断当前节点的左孩子是否为左叶子
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }
}
