package lt_200_299;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class LC_257 {
    private List<String> retList = new LinkedList<>();
    private void dfs(TreeNode node, List<String> helperList) {
        if (node.left == null && node.right==null) {
            helperList.add(String.valueOf(node.val));
            retList.add(String.join("->", helperList));
            helperList.remove(helperList.size()-1);
            return;
        }
        helperList.add(String.valueOf(node.val));
        if (node.left != null) {
            dfs(node.left, helperList);
        }
        if (node.right != null) {
            dfs(node.right, helperList);
        }
        helperList.remove(helperList.size()-1);
    }
    public List<String> binaryTreePaths(TreeNode root) {
        retList.clear();
        if (root == null) {
            return retList;
        }
        dfs(root, new LinkedList<>());
        return retList;
    }
}
