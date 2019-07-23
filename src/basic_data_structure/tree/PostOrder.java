package basic_data_structure.tree;

import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostOrder {
    private void visit(TreeNode node) {
        System.out.println(node.val);
    }

    public void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        visit(node);
    }

    /**
     * 迭代版本
     *
     * 由于根节点最后访问，所以使用map来标识栈中的节点的右子树是否已经访问
     * @param node
     */
    private void postorderIterative(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Boolean> rightMap = new HashMap<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                rightMap.put(node, true);
                node = node.left;
            } else {
                if (rightMap.get(stack.peek())) {
                    rightMap.put(stack.peek(), false);
                    node = stack.peek().right;
                } else {
                    node = stack.pop();
                    visit(node);
                    node = null;
                }
            }
        }
    }
}
