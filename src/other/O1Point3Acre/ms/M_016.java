package other.O1Point3Acre.ms;


import domain.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树俯视图
 *
 * 以根节点为原点0，左子树-1，右子树+1
 */
public class M_016 {
    public List<Integer> topDownView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.val);
        int left = 0, right = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<TreeNode, Integer> cur = queue.poll();
                int coor = cur.getValue();
                TreeNode node = cur.getKey();
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, coor-1));
                    if (coor-1 < left) {
                        left = coor-1;
                        list.add(node.left.val);
                    }
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, coor+1));
                    if (coor+1 > right) {
                        right = coor+1;
                        list.add(node.right.val);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        root.right.left.right.right = new TreeNode(8);

        new M_016().topDownView(root).forEach(System.out::println);
    }
}
