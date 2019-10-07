package lt_200_299;


import domain.ListNode;

public class LC_234 {

    public static class Solution1 {
        /**
         * 后半段倒序，然后跟前半部分比较即可
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode p = head, q = head.next;
            ListNode virNode = new ListNode(0);
            while (q!= null && q.next != null) {
                p = p.next;
                q = q.next.next;
            }
            q = p.next;
            p.next = null;
            while (q != null) {
                ListNode tmp = q.next;
                q.next = virNode.next;
                virNode.next = q;
                q = tmp;
            }
            q = virNode.next;
            p = head;
            while (p != null && q != null) {
                if (p.val != q.val) {
                    return false;
                }
                p = p.next;
                q = q.next;
            }
            return true;
        }
    }

    /**
     * 不用翻转，直接快慢指针定位中间位置
     * 然后递归比较
     *
     */
    public static class Solution2 {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode p = head, q = head.next;
            ListNode virNode = new ListNode(0);
            while (q!= null && q.next != null) {
                p = p.next;
                q = q.next.next;
            }
            q = p.next;
            preNode = head;
            return compare(q);

        }
        private ListNode preNode;
        private boolean compare(ListNode node) {
            if (node == null || preNode == null) {
                return true;
            }
            // 深度到尾部
            boolean isP = compare(node.next);
            if (isP && node.val == preNode.val) {
                preNode = preNode.next;
                return true;
            } else {
                return false;
            }
        }
    }

}
