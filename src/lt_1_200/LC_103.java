package lt_1_200;

import domain.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/28
 */
public class LC_103 {
    /**
     * Binary Tree Zigzag Level Order Traversal
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> retList = new LinkedList<>();
        if (root == null) return retList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 1;
        int len;
        while((len=queue.size()) > 0) {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < len; ++i) {
                TreeNode ans = queue.poll();
                list.add(ans.val);
                if (ans.left!=null) queue.offer(ans.left);
                if (ans.right!=null) queue.offer(ans.right);
            }
            if (height%2==0) {
                Collections.reverse(list);
            }
            retList.add(list);
            height++;
        }
        return retList;
    }
}
