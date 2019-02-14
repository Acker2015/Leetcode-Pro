package lt_600_699;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import domain.TreeNode;
/**
 * BST的顺序遍历(正序或者倒序)
 * 使用栈来遍历树结构，由于是BST，这里正好利用这一点来使用two pointers收尾遍历二叉搜索树
 * @author Acker
 *
 */
public class LC_653 {
	// define iterator 定义BST的顺序遍历方式，前后指向迭代器
	public class BSTIterator {
		Stack<TreeNode> stack;
		TreeNode node;
		boolean forward;  // 标识正向遍历还是反向遍历
		public BSTIterator(TreeNode root, boolean forw) {
			node = root;
			forward = forw;
		}
		public boolean hasNext() {
			return node!=null || !stack.isEmpty();
		}
		// 寻找正向或者反向下一个节点的核心代码
		public int next() {
			while (node!=null || !stack.isEmpty()) {
				if (node!=null) {
					stack.push(node);
					node = forward ? node.left : node.right;
				} else {
					TreeNode tmpNode =stack.pop();
					node = forward ? tmpNode.right : tmpNode.left;
					return tmpNode.val;
				}
			}
			return -1; // impossible
		}
	}
	public boolean findTarget(TreeNode root, int k) {
		if (root == null) return false;
		BSTIterator bl = new BSTIterator(root, true);
		BSTIterator br = new BSTIterator(root, false);
		for (int leftVal = bl.next(), rightVal = br.next(); leftVal < rightVal;) {
			int sum = leftVal + rightVal;
			if (sum == k) {
				return true;
			} else if (sum < k) {
				leftVal = bl.next();
			} else {
				rightVal = br.next();
			}
		}
        return false;
    }
	
	/**
	 * 方法二 dfs+hashmap
	 * time O(n) 
	 * space O(n)
	 * @param root
	 * @param k
	 * @return
	 */
	public boolean findTarget2(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return dfs(root, set, k);
	}
	private boolean dfs(TreeNode node, Set<Integer> set, int k) {
		if (node == null) return false;
		if (set.contains(k - node.val)) {
			return true;
		}
		set.add(node.val);
		return dfs(node.left, set, k) || dfs(node.right, set, k);
	}
	/**
	 * BST中序遍历，存储有序数组，对有序数据进行two pointers遍历
	 * @param root
	 * @param k
	 * @return
	 */
	public boolean findTarget3(TreeNode root, int k) {
		List<Integer> list = new ArrayList<>();
		inorder(root, list);
		int l = 0, r = list.size()-1;
		while (l < r) {
			int sum = list.get(l) + list.get(r);
			if (sum == k) return true;
			else if (sum < k){
				l++;
			} else {
				r--;
			}
		}
		return false;
	}
	private void inorder(TreeNode node, List<Integer> list) {
		if (node == null) return;
		inorder(node.left, list);
		list.add(node.val);
		inorder(node.right, list);
	}
	
	/**
	 * 方法四 dfs+二分查找
	 * @param root
	 * @param k
	 * @return
	 */
	public boolean findTarget4(TreeNode root, int k) {
		return preOrder(root, root, k);
		
	}
	private boolean preOrder(TreeNode root, TreeNode node, int k) {
		if (node == null) return false;
		return bi_search(node, root, k - node.val) 
				|| preOrder(root, node.left, k)
				|| preOrder(root, node.right, k);
	}
	private boolean bi_search(TreeNode orgNode, TreeNode node, int aim) {
		if (node == null || orgNode == node) return false;
		return (orgNode!=node && node.val==aim) 
				|| (aim<node.val && bi_search(orgNode, node.left, aim))
				|| (aim>node.val && bi_search(orgNode, node.right, aim));
	}
	
	
	public static void main(String[] args) {
		

	}

}
