package lt_200_299;


import domain.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC_297 {
    private final static String EMPTY_NODE = "nil";
    private final static String SPILIT = ",";

    /**
     * BFS
     * serialize and deserialize
     */
    static class Solution1 {
        private void append(StringBuilder builder, TreeNode node) {
            builder.append(SPILIT);
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
                if (!desArr[idx].equals(EMPTY_NODE)) {
                    node.left = new TreeNode(Integer.parseInt(desArr[idx]));
                    queue.offer(node.left);
                }
                idx++;
                if (idx+1 >= desArr.length) {
                    return null;
                }
                if (!desArr[idx].equals(EMPTY_NODE)) {
                    node.right = new TreeNode(Integer.parseInt(desArr[idx]));
                    queue.offer(node.left);
                }
                idx++;
            }
            return head;
        }
    }


    /**
     * DFS
     * pre-order serialize and deserialize
     */
    static class Solution2 {
        /**
         * pre-order serialize
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            buildString(root, builder);
            return builder.toString();
        }
        private void buildString(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append(SPILIT).append(EMPTY_NODE);
            } else {
                if (builder.length() > 0) {
                    builder.append(SPILIT);
                }
                builder.append(root.val);
                buildString(root.left, builder);
                buildString(root.right, builder);
            }
        }

        /**
         * pre-order deserialize
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
            if (data == null || data.length() <= 0) return null;
            String[] arr = data.split(SPILIT);
            this.idx = 0;
            return buildTree(arr);
        }
        private int idx;
        private TreeNode buildTree(String[] arr) {
            String val = arr[idx++];
            if (val.equals(EMPTY_NODE)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = buildTree(arr);
            node.right = buildTree(arr);
            return node;
        }
    }
}
