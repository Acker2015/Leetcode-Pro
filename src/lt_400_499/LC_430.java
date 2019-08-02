package lt_400_499;


import domain.Node;

import java.util.Stack;

/**
 * [430] Flatten a Multilevel Doubly Linked List
 */
public class LC_430 {
    public class Solution1 {
        /**
         * solution1
         * 借助栈来实现层级迭代遍历
         * @param head
         * @return
         */
        public Node flatten(Node head) {
            if (head == null) {
                return head;
            }
            Node node = head;
            Stack<Node> stack = new Stack<>();
            while (node != null) {
                if (node.child != null) {
                    Node childNode = node.child;
                    node.child = null;  // 断开与孩子节点的连接
                    stack.push(node.next);
                    node.next = childNode;
                    childNode.prev = node;
                } else if (node.next == null && !stack.isEmpty()) {
                    // node.next不为空 防止node移动到空节点，导致与stack中父级节点断开
                    node.next = stack.pop();
                    if (node.next != null) {
                        node.next.prev = node;
                    }
                }
                node = node.next;
            }
            return head;
        }
    }

    /**
     * recursion 递归解法
     */
    public class Solution2 {
        // previousNode为前驱节点，node为当前节点
        // 返回最后一个不为null的节点，便于与父级链表继续连接
        private Node helper(Node previousNode, Node node) {
            while (node != null) {
                // connect
                previousNode.next = node;
                node.prev = previousNode;
                previousNode = previousNode.next;
                if (node.child == null) {
                    node = node.next;
                } else {
                    Node nextNode = node.next;
                    Node childNode = node.child;
                    childNode.prev = node;
                    node.child = null;  // 切断孩子节点
                    previousNode = helper(previousNode, childNode);
                    node = nextNode;
                }
            }
            return previousNode;
        }
        public Node flatten(Node head) {
            if (head == null) return null;
            Node virtualNode = new Node();
            helper(virtualNode, head);
            virtualNode.next.prev = null;
            return virtualNode.next;
        }
    }
}
