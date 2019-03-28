package lt_1_200;

import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/28
 */
public class LC_106 {
    /**
     * Construct Binary Tree from Inorder and Postorder Traversal
     */
    Map<Integer, Integer> inorderMap = new HashMap<>();
    private TreeNode build(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        if (inL > inR) return null;
        if (inL == inR) return new TreeNode(inorder[inL]);
        int mid = inorderMap.get(postorder[postR]);
        int num = mid - inL;
        TreeNode node = new TreeNode(postorder[postR]);
        node.left = build(inorder, inL, mid-1, postorder, postL, postL+num-1);
        node.right = build(inorder, mid+1, inR, postorder, postL+num, postR-1);
        return node;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length==0 || inorder.length!=postorder.length) return null;
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
}
