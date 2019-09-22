package lt_1_200;

import domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [145] Binary Tree Postorder Traversal
 * 迭代法
 *
 * 使用栈进行迭代
 */
public class LC_145 {
    /**
     * stack
     * 遇到每一个节点(都有三种状态)
     * 1. 左子树未访问，那么就访问左子树
     * 2. 左子树已经访问，右子树没有访问，那么就访问右子树
     * 3. 左右子树均访问(或者为null)，那么就输出节点
     *
     * for each node A while traversing
     * 1. if left subtree of A has not been visited, then visit the left node of A firstly.
     * 2. if left subtree has been visited and the right subtree not, then visit right node of A
     * 3. if left subtree and right subtree all have been visited, then should visit the node and output its value.
     */
    static class Tuple {
        TreeNode node;
        int state;  // 0表示左右子树均未访问，1表示左已经访问右子树未访问，2表示左右均访问
        public Tuple(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> retList = new ArrayList<>();
        if (root == null) {
            return retList;
        }
        Stack<Tuple> stack = new Stack<>();
        stack.push(new Tuple(root, 0));
        while (!stack.isEmpty()) {
            Tuple tuple = stack.peek();
            if (tuple.state == 0) {
                // mark the left subtree has been visited
                tuple.state = 1;
                if (tuple.node.left != null) {
                    stack.push(new Tuple(tuple.node.left, 0));
                }
            } else if (tuple.state == 1) {
                // mark the left and right subtree have been visited
                tuple.state = 2;
                if (tuple.node.right != null) {
                    stack.push(new Tuple(tuple.node.right, 0));
                }
            } else {
                retList.add(tuple.node.val);
                stack.pop();
            }
        }
        return retList;
    }
}
