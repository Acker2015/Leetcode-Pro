package lt_1_200;

import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/28
 */
public class LC_105 {
    /**
     * Construct Binary Tree from Preorder and Inorder Traversal
     */
    Map<Integer, Integer> inorderMap = new HashMap<>();
    private TreeNode build(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preL > preR) return null;
        int rootVal = preorder[preL];
        int mid = inorderMap.get(rootVal);
        int num = mid - inL;
        TreeNode node = new TreeNode(rootVal);
        node.left = build(preorder, preL+1, preL+num, inorder, inL, mid-1);
        node.right = build(preorder, preL+num+1, preR, inorder, mid+1, inR);
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length <= 0 && preorder.length != inorder.length) {
            return null;
        }
        for (int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
}
