package lt_1_200;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/27
 */
public class LC_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> retList = new LinkedList<>();
        if (root == null) return retList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while((len=queue.size()) > 0) {
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < len; ++i) {
                TreeNode ans = queue.poll();
                list.add(ans.val);
                if (ans.left!=null) queue.offer(ans.left);
                if (ans.right!=null) queue.offer(ans.right);
            }
            retList.add(list);
        }
        return retList;
    }
}
