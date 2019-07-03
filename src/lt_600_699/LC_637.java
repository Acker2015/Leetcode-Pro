package lt_600_699;


import domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [637] Average of Levels in Binary Tree
 */
public class LC_637 {
    /**
     * BFS
     * intuition
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> retList = new ArrayList<>();
        if (root == null) return retList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            retList.add(sum/size);
        }
        return retList;
    }
}
