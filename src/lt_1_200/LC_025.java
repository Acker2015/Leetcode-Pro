package lt_1_200;


import domain.ListNode;

public class LC_025 {
	// 递归
	static class Solution1 {
		private int getListLength(ListNode node) {
			int len = 0;
			while (node != null) {
				len++;
				node=node.next;
			}
			return len;
		}

		private ListNode reverse(ListNode node, int left, int k) {
			if (left < k) {
				return node;
			}
			ListNode virtualNode = new ListNode(0);
			virtualNode.next = node;
			for (int i = 1; i < k; ++i) {
				ListNode tmpNode = node.next;
				node.next = tmpNode.next;
				tmpNode.next = virtualNode.next;
				virtualNode.next = tmpNode;
			}
			node.next = reverse(node.next, left - k, k);
			return virtualNode.next;
		}
		/**
		 * 递归
		 * @param head
		 * @param k
		 * @return
		 */
		public ListNode reverseKNodes(ListNode head, int k) {
			int len = getListLength(head);
			return reverse(head, len, k);
		}
	}

	// 迭代
	static class Solution2 {
		private int getLen(ListNode node) {
			int len = 0;
			while (node != null) {
				len++;
				node = node.next;
			}
			return len;
		}
		public ListNode reverseKGroup(ListNode node, int k) {
			int len = getLen(node);
			ListNode virtualNode = new ListNode(0);
			ListNode pre = virtualNode;

			while (len >= k) {
				// 保留下一次的pre节点
				ListNode nextPre = node;
				for (int i = 0; i < k; ++i) {
					ListNode ans = node.next;
					node.next = pre.next;
					pre.next = node;
					node = ans;
				}
				pre = nextPre;
				len -= k;
			}
			pre.next = node;
			return virtualNode.next;
		}
	}

	
	
	
	
	public static ListNode buildList(int[] arr) {
		ListNode node = new ListNode(0), pre = node;
		for (int i = 0; i < arr.length; ++i) {
			pre.next = new ListNode(arr[i]);
			pre = pre.next;
		}
		return node.next;
	}
	public static void print(ListNode node) {
		while(node != null) {
			System.out.print(node.val);
			node = node.next;
			if (node != null) System.out.print(" -> ");
		}
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		Solution1 solution = new Solution1();
		ListNode node = LC_025.buildList(arr);
		print(solution.reverseKNodes(node, 3));

	}

}
