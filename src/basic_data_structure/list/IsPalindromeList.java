package basic_data_structure.list;


import domain.Node;

/**
 * 判断链表是否回文
 *
 * 方法一： 链表翻转，比较翻转链表和原链表是否相同 time:O(n), space:O(n)
 * 方法二：利用快慢指针，遍历时候将前半段入栈，然后比较是否相同 time:O(n) space:O(n)
 * 方法三：递归(recursive), 下边题目介绍
 */
public class IsPalindromeList {
    class Result {
        boolean result;
        Node node;
        Result(Node node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }
    private int listSize(Node node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
    /**
     * cracking the coding interview
     * P128
     * 直觉上可能就会想到要比较元素0和元素n，元素1和元素n-1, 元素2和元素n-2，直到中间元素
     * 例如：
     * 0 (1 ( 2 (3 (4) 3) 2) 1) 0
     * 为了运用这种方法，首先必须直到什么时候可以达到中间元素，这也就形成了递归的终止条件。
     * 每次递归调用传入length-2的长度，当长度为0或者1的时候，表示当前已经处于链表的中间位置。
     *
     * recurse(Node node, int length) {
     *     if (length == 0 || length == 1) {
     *         return sth;
     *     }
     *     recurse(node.next, length-2);
     * }
     *
     * 往深层递归，直到node到达中间节点，然后就可以在每一层递归可以依次返回中间点的next节点，与head节点比较
     * 这样才能比较 (t, n-t)节点对，(t-1, n-t+1)节点对，..., (0, n)节点对
     *
     * isPalindrome: list = 0 (1 ( 2 (3 (4) 3) 2) 1) 0. len = 9
     *      isPalindrome: list = 1 ( 2 (3 (4) 3) 2) 1) 0. len = 7
     *          isPalindrome: list = 2 (3 (4) 3) 2) 1) 0. len = 5
     *              isPalindrome: list = 3 (4) 3) 2) 1) 0. len = 3
     *                  isPalindrome: list = 4) 3) 2) 1) 0. len = 1 (此时head指向4的位置)
     *                  return 3b,true
     *              return 2b, true
     *          return 1b, true
     *     return 0b, true
     * return 0b, true
     *
     * @param head
     * @return
     */
    Result isPalindomeRecurse(Node head, int length) {
        if (head == null || length == 0) {
            return new Result(null, true);
        } else if (length == 1) {
            return new Result(head.next, true);
        } else if (length == 2) {
            return new Result(head.next.next, head.val==head.next.val);
        }
        Result res = isPalindomeRecurse(head.next, length-2);
        if (!res.result || res.node == null) {
            return res;
        } else {
            res.result = head.val == res.node.val;
            res.node = res.node.next;
            return res;
        }
    }
    public boolean isPalindrome(Node head) {
        Result p = isPalindomeRecurse(head, listSize(head));
        return p.result;
    }

}
