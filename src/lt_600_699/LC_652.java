package lt_600_699;


import domain.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * [652] Find Duplicate Subtrees
 */
public class LC_652 {
    /**
     * 前序+中序可以唯一确定一棵树
     * 这里使用L来代替左孩子空节点，R代替右孩子空节点，这样前序或者中序就可以确定唯一子串
     */
    private String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> retList) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String left = dfs(root.left, map, retList);
        String right = dfs(root.right, map, retList);
        left = left.length() <= 0 ? "L":left;
        right = right.length() <= 0 ? "R":right;
        // inorder string of sub tree
        builder.append(root.val).append(left).append(right);
        String subTreeStr = builder.toString();
        int num = map.getOrDefault(subTreeStr, 0);
        map.put(subTreeStr, num+1);
        if (num == 1) {
            retList.add(root);
        }
        return subTreeStr;
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> retList = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        dfs(root, map, retList);
        return retList;
    }
}
