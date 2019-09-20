package secondround.sr1_300;

import domain.ListNode;

public class sr_023 {
    class Solution {
        private ListNode mergeTwo(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val > l2.val) {
                return mergeTwo(l2, l1);
            }
            ListNode head = l1;
            while (l2 != null) {
                if (l1.next == null) {
                    l1.next = l2;
                    break;
                }
                if (l1.next.val <= l2.val) {
                    l1 = l1.next;
                } else {
                    ListNode tmp = l2.next;
                    l2.next = l1.next;
                    l1.next = l2;
                    l1 = l1.next;
                    l2 = tmp;
                }
            }
            return head;
        }
        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left > right) return null;
            if (left == right) return lists[left];
            int mid = left + (right-left)/2;
            ListNode l1 = merge(lists, left, mid);
            ListNode l2 = merge(lists, mid+1, right);
            return mergeTwo(l1, l2);
        }

        /**
         * 类似归并排序
         * @param lists
         * @return
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length <= 0) return null;
            if (lists.length <= 1) return lists[0];
            return merge(lists, 0, lists.length-1);
        }
    }
}
