package lt_200_299;

import domain.TreeNode;

/**
 * [LeetCode] 285. Inorder Successor in BST
 *
 * BST中某个节点的中序后继节点
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 * Note:
 * If the given node has no in-order successor in the tree, return null.
 * It's guaranteed that the values of the tree are unique.
 */
public class LC_285 {
    /**
     * inorder
     * 左中右
     * 最直白的方法是利用中序遍历来找到节点p的下一个位置即可
     *
     * 充分地利用到了 BST 的性质，我们首先看根节点值和p节点值的大小。
     * 1.如果根节点值大，说明p节点肯定在左子树中，那么此时我们先将 res(可能后继) 赋为 root，然后 root 移到其左子节点，
     * 2.如果根节点值小于等于p及节点值，说明p节点出现在右子树中，并且当前root不会是其后继节点，所以单纯的继续遍历右子树即可
     * 这样当 root 为空时，res 指向的就是p的后继节点
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (p.val < root.val) {
                res = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 上述解法的递归解法 (方法返回上述的res即可)
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (p.val < root.val) {
            TreeNode ans = inorderSuccessor2(root.left, p);
            return ans==null ? root: ans;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }
}
