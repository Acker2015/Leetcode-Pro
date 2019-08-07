package lt_400_499;

import domain.TreeNode;

/**
 * 直接通过先序遍历来解决
 * [449] Serialize and Deserialize BST
 */
public class LC_449 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        private void preOrder(StringBuilder builder, TreeNode node){
            if (node == null) return;
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(node.val);
            preOrder(builder, node.left);
            preOrder(builder, node.right);
        }
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builderPre = new StringBuilder();
            preOrder(builderPre, root);
            return builderPre.toString();
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data==null || data.length() <= 0) return null;
            String[] splitArr =  data.split(",");
            int[] nodeArr = new int[splitArr.length];
            for (int i = 0; i < splitArr.length; ++i) {
                nodeArr[i] = Integer.parseInt(splitArr[i]);
            }
            return rebuildTree(nodeArr, 0, nodeArr.length-1);
        }

        private TreeNode rebuildTree(int[] nodeValues, int left, int right) {
            if (left > right) {
                return null;
            }
            TreeNode node = new TreeNode(nodeValues[left]);
            int j = left + 1;
            while (j < nodeValues.length && nodeValues[j] <= nodeValues[left]) {
                j++;
            }
            node.left = rebuildTree(nodeValues, left+1, j-1);
            node.right = rebuildTree(nodeValues, j, right);
            return node;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}
