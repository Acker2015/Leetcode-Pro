package lt_400_499;

import domain.TreeNode;

/**
 * [450] Delete Node in a BST
 * 第二种解法较好 - 递归 (第一种解法比较麻烦，分别记录父节点和当前节点)
 * 直接使用第二种解法的返回删除目标子树的头结点即可
 */
public class LC_450 {
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
        private TreeNode goLeft(TreeNode p, TreeNode q) {
            while (q.right != null) {
                p = q;
                q = q.right;
            }
            if (p.left == q) {
                p.left = q.left;
            } else {
                p.right = q.left;
            }
            q.left = null;
            return q;
        }
        private TreeNode goRight(TreeNode p, TreeNode q) {
            while (q.left != null) {
                p = q;
                q = q.left;
            }
            if (p.right == q) {
                p.right = q.right;
            } else {
                p.left = q.right;
            }
            q.right = null;
            return q;
        }
        private void delete(TreeNode parentNode, TreeNode node, int key) {
            if (node == null) {
                return;
            }
            if (node.val == key) {
                if (node.left == null && node.right == null) {
                    if (parentNode.left == node) {
                        parentNode.left = null;
                    } else {
                        parentNode.right = null;
                    }
                } else if (node.left == null) {
                    // 获取右孩子的最左节点
                    TreeNode subNode = goRight(node, node.right);
                    node.val = subNode.val;
                } else {
                    // 获取左孩子的最右节点
                    TreeNode subNode = goLeft(node, node.left);
                    node.val = subNode.val;
                }
                return;
            }
            if (node.val < key) {
                delete(node, node.right, key);
            } else {
                delete(node, node.left, key);
            }
        }

        /**
         * 1. 目标节点没有孩子，直接删除
         * 2. 目标节点没有左孩子，找右孩子最左节点
         * 3. 否则 找左孩子最右节点
         * @param root
         * @param key
         * @return
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            if (root.val == key && root.left == null && root.right == null) return null;
            delete(null, root, key);
            return root;
        }
    }


    /**
     * 通过递归的返回值来记录正确的孩子节点
     * Steps:
     * Recursively find the node that has the same value as the key, while setting the left/right nodes equal to the returned subtree
     * Once the node is found, have to handle the below 4 cases
     *      1. node doesn't have left or right - return null
     *      2. node only has left subtree - return the left subtree
     *      3. node only has right subtree- return the right subtree
     *      4. node has both left and right - find the minimum value in the right subtree,
     *          set that value to the currently found node, then recursively delete the minimum value in the right subtree
     */
    public class Solution2 {
        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        /**
         * 返回值表示删除指定key node之后的头结点
         * @param root
         * @param key
         * @return
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                // 1,2,3
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                // 4, find the minimum value in the right subtree
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);

            }
            return root;
        }
    }
}
