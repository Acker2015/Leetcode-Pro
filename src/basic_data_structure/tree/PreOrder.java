package basic_data_structure.tree;

import domain.TreeNode;

import java.util.Stack;

public class PreOrder {
    private void visit(TreeNode node) {
        System.out.println(node.val);
    }

    public void preorderRecursive(TreeNode node) {
        if (node == null) return;
        visit(node);
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    /**
     * 迭代解法
     * 使用栈来保存根节点，方便跟踪右子树
     * @param node
     */
    public void preorderIterative(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                visit(node);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
    }
}
