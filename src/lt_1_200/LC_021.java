package lt_1_200;

import domain.ListNode;

/**
 * 合并有序链表
 * 解法一： 类似归并排序
 * 解法二： 递归实现(recursion)
 * @author Acker
 *
 */
public class LC_021 {
	/**
	 * solution 1
	 * time O(m+n)
	 * implement with recursion
	 * divide the problem into merging the subList of l1 and l2 recursively
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
	
	/**
	 * solution2
	 * compare the val of l1 and l2, to get the new sorted list
	 * time O(m+n)
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1==null || l2 ==null) return l1==null ? l2 : l1;
        ListNode head = new ListNode(0), p = head, tmpNode;
        while(l1!=null && l2!=null) {
            if(l1.val <= l2.val) {
                tmpNode = l1;
                l1 = l1.next;
            } else {
                tmpNode = l2;
                l2 = l2.next;
            }
            tmpNode.next = null;
            p.next = tmpNode;
            p = tmpNode;
        }
        p.next = l1==null ? l2 : l1;
        return head.next;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}
