package lt_400_499;

import domain.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [429] N-ary Tree Level Order Traversal
 * intuition
 * level-traversal
 */
public class LC_429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> ans = new ArrayList<>();
            while (size-- > 0) {
                Node node = queue.poll();
                ans.add(node.val);
                if (node.children == null) continue;
                for (Node child: node.children) {
                    queue.offer(child);
                }
            }
            list.add(ans);
        }
        return list;
    }
}
