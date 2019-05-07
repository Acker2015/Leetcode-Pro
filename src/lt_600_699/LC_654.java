package lt_600_699;


import domain.TreeNode;

/**
 * [654] Maximum Binary Tree
 *
 * 树的构建
 *
 */
public class LC_654 {
    private int findMaxPos(int[] nums, int l, int r) {
        int pos = l, max = nums[l];
        for (int i = l+1; i <= r; ++i) {
            if (nums[i] > max) {
                max = nums[i];
                pos = i;
            }
        }
        return pos;
    }
    private TreeNode build(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int pos = findMaxPos(nums, l, r);
        TreeNode node = new TreeNode(nums[pos]);
        node.left = build(nums, l, pos-1);
        node.right = build(nums, pos+1, r);
        return node;
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length-1);
    }
}
