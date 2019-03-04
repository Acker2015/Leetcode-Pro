package lt_1_200;

import java.util.LinkedList;
import java.util.Queue;

import domain.TreeNode;

public class LC_104 {
	/**
     * DFS
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * BFS
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int height = 0;
        queue.add(root);
        while (queue.size() > 0) {
            int len = queue.size();
            for (int i = 0; i < len; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            height++;
        }
        return height;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
