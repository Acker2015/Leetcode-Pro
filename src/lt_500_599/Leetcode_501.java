package lt_500_599;

import java.util.ArrayList;
import java.util.List;
import domain.TreeNode;
/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/submissions/
 * BST中出现次数最多的数(允许多个)
 * time O(n) space O(1)
 * @author Acker
 *
 */
public class Leetcode_501 {
	private Integer prev = null;		// 中序遍历的前置节点值
	private int count = 1;			// 记录节点的出现次数(BST，所以中序遍历为升序遍历，遍历过程中数值与之前不同就可以似的count置为1)
	private int max = 0;				// 记录重复的最大次数
	
	/**
	 * 中序遍历
	 * @param node	访问节点
	 * @param list	记录出现次数最大的节点
	 */
	private void traverse(TreeNode node, List<Integer> list) {
		if (node == null) return;
		traverse(node.left, list);
		if (prev != null) {
			if (node.val == prev) {
				count++;
			} else {
				count = 1;
			}
		}
		if (count > max) {
			max = count;
			list.clear();
			list.add(node.val);
		} else if (count == max) {
			list.add(node.val);
		}
		prev = node.val;
		traverse(node.right, list);
	}
	
	public int[] findMode(TreeNode root) {
		if (root == null) return new int[0];
		List<Integer> list = new ArrayList<>();
		traverse(root, list);
		int ret[] = new int[list.size()];
		for (int i = 0; i<list.size(); ++i) {
			ret[i] = list.get(i);
		}
		return ret;
    }
}
