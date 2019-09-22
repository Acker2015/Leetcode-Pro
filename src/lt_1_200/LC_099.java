package lt_1_200;


import domain.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 有且只有两个节点位置颠倒，那么这两个节点就是中序遍历中逆序的两个节点
 * 如果只有一个逆序对 -> 1,2,4,3,5 -> 那么这两个点就是4，3
 * 如果有两个 -> 1,4,3,2,5 -> 那么这两个点就是4和2
 */
public class LC_099 {
    /**
     * time O(n), space O(1)
     * DSF inorder
     */
    public static class Solution1 {
        private TreeNode preNode;
        private TreeNode node1, node2;
        private void inorder(TreeNode node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            if (preNode != null && preNode.val > node.val) {
                if (node1==null) {
                    node1 = preNode;
                }
                node2 = node;
            }
            preNode = node;
            inorder(node.right);
        }
        public void recoverTree(TreeNode root) {
            if (root == null) return;
            this.node1 = null;
            this.node2 = null;
            inorder(root);
            if (node1 != null) {
                int tmp = node1.val;
                node1.val = node2.val;
                node2.val = tmp;
            }
        }
    }

    /**
     * 使用栈来模拟中序遍历
     */
    public static class Solution2 {
        public void recoverTree(TreeNode root) {
            if (root == null) return;
            Stack<TreeNode>  stack = new Stack<>();
            TreeNode node1 = null, node2 = null, lastNode = null;
            while (root!=null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                TreeNode ans = stack.pop();
                if (lastNode != null && lastNode.val > ans.val) {
                    if (node1 == null) {
                        node1 = lastNode;
                    }
                    node2 = ans;
                }
                lastNode = ans;
                root = ans.right;
            }
            if (node1 != null) {
                int tmp = node1.val;
                node1.val = node2.val;
                node2.val = tmp;
            }
        }
    }

    /**
     * 这个方法最粗暴直接
     * 直接中序遍历将节点序列list获取到
     * 然后将值的顺序进行排序
     * 最后将有序的值顺序写回节点list中
     */
    public static class Solution3 {
        private void inorder(TreeNode root, List<TreeNode> nodeList, List<Integer> valList) {
            if (root == null) {
                return;
            }
            inorder(root.left, nodeList, valList);
            nodeList.add(root);
            valList.add(root.val);
            inorder(root.right, nodeList,valList);
        }
        public void recoverTree(TreeNode root) {
            List<TreeNode> nodeList = new ArrayList<>();
            List<Integer> valList = new ArrayList<>();
            inorder(root, nodeList, valList);
            Collections.sort(valList);
            for (int i = 0; i < valList.size(); ++i) {
                nodeList.get(i).val = valList.get(i);
            }
        }
    }
}
