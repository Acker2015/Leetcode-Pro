package lt_300_399;

import java.util.Random;

import domain.ListNode;
/**
 * 算法首先创建一个长度为K的数组（蓄水池）用来存放结果，初始化为S的前k个元素，然后从k+1个元素开始迭代直到数组结束。
 * 算法生成一个随机数j∈[1, i]，如果 j ≤ k，那么蓄水池的第 j 个元素被替换为S的第i个元素。
 * @author Acker
 *
 */
public class LC_382 {
	Random random;
	ListNode head;
	
	/** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
	public LC_382(ListNode head) {
	    this.random = new Random();
	    this.head = head;
	}
	
	/** Returns a random node's value. */
	public int getRandom() {
	    int count = 1;
	    ListNode cur = this.head;
	    ListNode aimNode = this.head;
	    while(cur != null) {
	    		// 1/i
	    		if (random.nextInt(count++) == 0) {
	    			aimNode = cur;
	    		}
	    		cur = cur.next;
	    }
	    return aimNode.val;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
