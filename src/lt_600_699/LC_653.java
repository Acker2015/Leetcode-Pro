package lt_600_699;

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
		
		public int next() {
			while (node!=null || !stack.isEmpty()) {
				if (node!=null) {
					stack.push(node);
					node = forward ? node.left : node.right;
				} else {
					node = forward ? node.right : node.left;
					return stack.pop().val;
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
	public static void main(String[] args) {
		

	}

}
