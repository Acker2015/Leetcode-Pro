package lt_400_499;


import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * easy -> medium
 * 参考two sum的解法，使用hashmap辅助求解
 * [437] Path Sum III
 */
public class LC_437 {
    /**
     * 记录前缀和，通过寻找(preSum-target)来帮助搜索路径和为target的sum
     */
    private int DFS(TreeNode root, int preSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        preSum += root.val;
        int ret = map.getOrDefault(preSum-target, 0);
        map.put(preSum, map.getOrDefault(preSum, 0)+1);
        ret += DFS(root.left, preSum, target, map);
        ret += DFS(root.right, preSum, target, map);
        map.put(preSum, map.get(preSum)-1);
        return ret;
    }
    /**
     * 原理类似two sum的解题思路
     * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) ,
     * and whenever reach a node, we check if prefix sum
     *  - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
     */
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return DFS(root, 0, sum, map);
    }
}
