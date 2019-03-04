package lt_1_200;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import domain.TreeNode;

public class LC_101 {
	public boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left==right;
        }
        return left.val==right.val 
        		&& isSymmetricHelp(left.left, right.right) 
            && isSymmetricHelp(left.right, right.left);
    }
    /**
     * 递归
     * 每次都比较节点的左子树和右子树是否各自为mirror tree
     */
    public boolean isSymmetric(TreeNode root) {	
        return root == null || isSymmetricHelp(root.left, root.right);
    }


    /** 		
     * 
     * @param left
     * @param right
     * @param stack
     * @return
     */
    public boolean judgeSub(TreeNode left, TreeNode right, Stack<TreeNode> stack) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        stack.push(left);
        stack.push(right);
        return true;
    }
    /**
     * 迭代
     * 初始化，判断根节点的左右节点是否一致， 如果不一致就直接返回false
     * 
     * 循环直到栈为空
     * 每次出栈两次(出栈的两个节点就是树中左右镜像的对应节点)
     * 然后分别判断这两个节点的 (n1.left, n2.right) & (n1.right, n2.left)是否匹配
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if(root==null)  return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (!judgeSub(root.left, root.right, stack)) {
            return false;
        }
        while (!stack.empty()) {
            TreeNode n1 = stack.pop(), n2 = stack.pop();
            if (!judgeSub(n1.left, n2.right, stack)) {
                return false;
            }
            if (!judgeSub(n1.right, n2.left, stack)) {
                return false;
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
