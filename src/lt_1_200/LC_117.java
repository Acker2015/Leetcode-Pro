package lt_1_200;

import domain.Node;

public class LC_117 {
    /**
     * [117] Populating Next Right Pointers in Each Node II
     *
     * level traversal
     * 使用虚拟节点virtualHead记录每一层的开始节点，方便下一次层序遍历找到下一层的起始节点
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        // tmpNode作为每一层的虚拟歧视节点
        Node virtualHead = new Node(0), ansRoot = root, pre = virtualHead;
        while(ansRoot!=null) {
            if (ansRoot.left!=null) {
                pre.next = ansRoot.left;
                pre = pre.next;
            }
            if (ansRoot.right!=null) {
                pre.next = ansRoot.right;
                pre = pre.next;
            }
            ansRoot = ansRoot.next;
            if (ansRoot == null) {
                // 进入下一层，并且这层的初始虚拟头结点初始化
                ansRoot = virtualHead.next;
                pre = virtualHead;
                pre.next = null;
            }
        }
        return root;
    }
}
