package lt_1_200;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class LC_113 {
    /**
     * Path Sum II
     * 裸DFS
     * 节点为负数，不能用remain<0来剪枝
     */
    List<List<Integer>> retList;
    private void dfs(int remain, TreeNode root, LinkedList<Integer> helpList) {
        if (root == null) return;
        helpList.add(root.val);
        // 叶子节点 && 路径sum相等
        if (root.left == null && root.right==null && root.val == remain) {
            retList.add(new LinkedList<>(helpList));
        }
        // 继续深搜左子树
        if (root.left!=null) {
            dfs(remain-root.val, root.left, helpList);
        }
        // 继续深搜右子树
        if (root.right!=null) {
            dfs(remain-root.val, root.right, helpList);
        }
        helpList.remove(helpList.size()-1);
    }

    /**
     * DFS
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        retList = new LinkedList<>();
        dfs(sum, root, new LinkedList<>());
        return retList;
    }
}
