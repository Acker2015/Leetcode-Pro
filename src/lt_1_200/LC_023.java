package lt_1_200;

import domain.ListNode;
/**
 * 思想类似归并排序
 * 分治， 不断切分分治，最后划分为合并两个有序链表
 * @author Acker
 *
 */
public class LC_023 {
	/**
	 * merge two sorted list
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeList(l1, l2.next);
            return l2;
        }
    }
	/**
	 * 分组
	 * @param lists
	 * @param begin
	 * @param end
	 * @return
	 */
    public ListNode partition(ListNode[] lists, int begin, int end) {
        if (begin == end) return lists[begin];
        int mid = begin + (end-begin)/2;
        ListNode l1 = partition(lists, begin, mid);
        ListNode l2 = partition(lists,mid+1, end);
        return mergeList(l1, l2);
    } 
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) return null;
        return partition(lists, 0, lists.length-1);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
