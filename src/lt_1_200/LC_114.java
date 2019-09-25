package lt_1_200;

import domain.TreeNode;

public class LC_114 {
    public static class Solution1 {
        /**
         * 返回的节点是新的平坦化子树的尾节点
         * (可能为null)
         */
        private TreeNode doFlatten(TreeNode node) {
            if (node == null) return null;
            TreeNode ansRight = node.right;
            TreeNode ansLeft = node.left;
            node.left = null;
            // 平坦化左子树 - 并返回平坦化的尾节点
            TreeNode leftTail = doFlatten(ansLeft);
            // 平坦化右子树 - 并返回平坦化的尾节点
            TreeNode rightTail = doFlatten(ansRight);
            if (leftTail != null) {
                // leftTail非空说明左子树非空，那么连接node->left subTree -> right subTree
                // 因为这时候头尾节点都是知道的
                node.right = ansLeft;
                leftTail.right = ansRight;
            } else {
                // 如果leftTail为空，那么只能连接node->right subtree
                node.right = ansRight;
            }
            // 返回此树调整为链表之后的尾节点
            if (rightTail != null) {
                return rightTail;
            }
            if (leftTail != null) {
                return leftTail;
            }
            return node;
        }

        public void flatten(TreeNode root) {
            doFlatten(root);
        }
    }

    public static class Solution2 {
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
         * fatten会将prev置为平坦化之后子树的头部节点
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

}
