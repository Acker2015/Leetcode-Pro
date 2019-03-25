package lt_1_200;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/25
 */

/**
 *
 * [95] Unique Binary Search Trees II
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 */
public class LC_095 {
    /**
     * generate function means to build the BST of the range [l, r]
     * the the BST can build by [l, k-1], k, [k+1, r]
     *
     * left sub-trees and right sub-trees can build the list of tree root.
     */
    private List<TreeNode> generate(int l, int r) {
        List<TreeNode> list = new LinkedList<>();
        if (l > r) {
            list.add(null);
            return list;
        }
        for (int i = l; i <= r; ++i) {
            List<TreeNode> leftList = generate(l, i-1);
            List<TreeNode> rightList = generate(i+1, r);
            for (TreeNode lNode: leftList) {
                for (TreeNode rNode: rightList) {
                    TreeNode node = new TreeNode(i);
                    node.left = lNode;
                    node.right = rNode;
                    list.add(node);
                }
            }
        }
        return list;
    }
    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
}
