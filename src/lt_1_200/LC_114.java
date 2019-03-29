package lt_1_200;

import domain.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class LC_114 {
    /**
     *
     * [114] Flatten Binary Tree to Linked List
     *
     * 返回子树平坦化的尾节点（非空）
     * 对于节点root
     * 1. 记录右子树节点为tmp
     * 2. 将左子树根置为root节点的右子树，并将root节点左子树切断。 即root.right=root.left; root.left=null
     * 3. 递归平坦化左子树，返回左子树平坦化之后的尾节点 leftTailNode
     * 4. 左子树平坦化尾节点连接root的右子树节点tmp, leftTailNode.right = tmp
     * 5. 平坦化右子树，并返回平坦化右子树的尾节点 作为此次迭代树的平滑化尾节点
     * 首先将左子树打平，并记录打平之后的尾节点，
     */
    private TreeNode flat(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode leftTailNode = root.right==null ? root : flat(root.right);
        leftTailNode.right = tmp;
        TreeNode rightTailNode = tmp==null ? leftTailNode : flat(tmp);
        return rightTailNode;
    }
    public void flatten1(TreeNode root) {
        flat(root);
    }



    /**
     * prev记录每次递归的头结点
     */
    TreeNode prev = null;
    /**
     *
     * 解法一
     *
     * 右-左-中 遍历
     * 之所以这样遍历是因为方便将右边子树平坦化之后，再平坦化左边子树，通过prev连接即可
     *
     * fattenen会将prev置为平坦化之后子树的头部节点
     *
     * 这样右序遍历之后，左子树的最后一个节点遇到的prev就是右子树平坦化之后的头部节点
     *
     */
    public void flatten(TreeNode root) {
        if (root==null) return;
        flatten(root.right);    // 此轮过后，prev为root.right子树的头部节点
        flatten(root.left);     // 利用prev来平坦化左子树
        root.right = prev; // 此时的prev就是左右子树平坦化之后的头节点,平坦化根节点
        root.left = null;
        prev = root;            // prev置为root这棵树平坦化的头结点
    }
}
