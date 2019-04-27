package lt_200_299;


import domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LC_297 {
    private final static String EMPTY_NODE = "nil";
    private void append(StringBuilder builder, TreeNode node) {
        builder.append(",");
        builder.append(node==null ? EMPTY_NODE: String.valueOf(node.val));
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sbd = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sbd.append(String.valueOf(root.val));
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            append(sbd, node.left);
            append(sbd, node.right);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sbd.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data==null || data.length() <= 0) return null;
        int idx = 0;
        String[] desArr = data.split(",");
        TreeNode head = new TreeNode(Integer.parseInt(desArr[idx++]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (idx+1 >= desArr.length) {
                return null;
            }
            if (desArr[idx] != EMPTY_NODE) {
                node.left = new TreeNode(Integer.parseInt(desArr[idx]));
                queue.offer(node.left);
            }
            idx++;
            if (idx+1 >= desArr.length) {
                return null;
            }
            if (desArr[idx] != EMPTY_NODE) {
                node.right = new TreeNode(Integer.parseInt(desArr[idx]));
                queue.offer(node.left);
            }
            idx++;
        }
        return head;
    }
}
