package basic_data_structure.tree;

import domain.TreeNode;

import java.util.Stack;

public class InOrder {
    private void visit(TreeNode node) {
        System.out.println(node.val);
    }

    public void inorderRecursive(TreeNode node) {
        if (node == null) return;
        inorderRecursive(node.left);
        visit(node);
        inorderRecursive(node.right);
    }

    public void inorderInterative(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                visit(node);
                node = node.right;
            }
        }
    }
}
