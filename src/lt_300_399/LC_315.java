package lt_300_399;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [315] Count of Smaller Numbers After Self
 *
 *
 * Solution1: 通过BST左子树的所有节点都比当前节点小的性质，在父节点直接记录左子树节点个数来统计数组右侧比当前节点值小的个数-注意重复数据的处理
 * BST 从后往前建树
 * 那么如果求索引i右边更小的数值的个数呢？
 * 直接BST中递归遍历， preSum记录右侧比当前节点小的个数
 *      每个节点记录
 *          ans:左子树的节点个数(左子树所有节点都比当前节点小)
 *          repeat: 记录当前节点值的出现次数
 *      1. 如果nums[i]等于当前节点，直接返回preSum,并记录当前节点的重复个数+1
 *      2. 如果nums[i]小于当前节点，那么更新当前节点的ans+1，继续访问左子树 preSum不变
 *      3. 如果nums[i]大于当前节点，preSum+ans+repeat 因为nums[i]>当前节点所以preSum需要更新，继续访问右子树
 *
 *
 * Solution2: 使用merge_sort的思想来计算
 * 在每一轮merge的过程中. [left,.., i, ..., mid, j, ..., right], [left, mid]以及[mid+1, right]都是各自有序的
 * 如果nums[i]>nums[j], 说明[i, mid]之间的每一个数都比nums[j]大，所以对应位置的smallerCount需要+1
 * 具体实现的时候为了防止重复数的出现，需要将val-index（数值索引关系）同时记录
 */
public class LC_315 {
    private class Node {
        int val;
        int ans;    // record the sum of node in left sub-tree （左子树节点个数）
        int repeat; // record the repeat num of the node.val (当前节点值的重复次数 >= 1)
        Node left, right;
        public Node(int val, int ans) {
            this.val = val;
            this.ans = 0;
            this.repeat = 1;
            this.left = null;
            this.right = null;
        }
    }
    private int insert(Node parent, Node root, int aim, int preSum) {
        if (root == null) {
            if (parent.val <= aim) {
                parent.right = new Node(aim, 0);
            } else {
                parent.left = new Node(aim, 0);
            }
            return preSum;
        }
        if (root.val == aim) {
            root.repeat+=1;
            return preSum + root.ans; // 加上左子树(全部比aim小)的节点个数
        } else if (root.val < aim) {
            return insert(root, root.right, aim, preSum+root.ans+root.repeat); //加上左子树(全部比aim小)的节点个数,和当前节点重复个数
        } else {
            root.ans += 1;
            return insert(root, root.left, aim, preSum);
        }
    }
    public List<Integer> countSmaller1(int[] nums) {
        Node root = new Node(Integer.MIN_VALUE, 0);
        Integer[] small = new Integer[nums.length];
        for (int i = nums.length-1; i >= 0; --i) {
            small[i] = insert(root, root.right, nums[i], 0);
        }
        return Arrays.asList(small);
    }




    /*-----------------------------------------------------------------------------华丽的分割线----------------------------------------------------------------------------------------------*/
    private class NumWrap {
        int val;
        int index;
        public NumWrap(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    private void merge(NumWrap[] numWraps, int[] smallCount, int left, int mid, int right) {
        int i =left, j = mid + 1, ans = 0, idx = 0;
        NumWrap[] temp = new NumWrap[right-left+1];
        while (i <= mid && j <= right) {
            if (numWraps[i].val <= numWraps[j].val) {
                smallCount[numWraps[i].index] += ans;
                temp[idx++] = numWraps[i++];
            } else {
                ans += 1; // [i, mid]之间的数都比numWraps[j]要大，所以这里记录个数即可
                temp[idx++] = numWraps[j++];
            }
        }
        while (i <= mid) {
            smallCount[numWraps[i].index] += ans;
            temp[idx++] = numWraps[i++];
        }
        while (j <= right) {
            temp[idx++] = numWraps[j++];
        }
        for (int k = 0; k < temp.length; ++k) {
            numWraps[left+k] = temp[k];
        }
    }
    private void mergeSort(NumWrap[] numWraps, int[] smallCount, int left, int right) {
        if (left >= right) return;
        int mid = left + ((right-left)>>1);
        mergeSort(numWraps, smallCount, left, mid);
        mergeSort(numWraps, smallCount, mid+1, right);
        merge(numWraps, smallCount, left, mid, right);
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> retList = new ArrayList<>();
        if (nums.length <= 0) {
            return new ArrayList<>();
        }
        NumWrap[] numWraps = new NumWrap[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            numWraps[i] = new NumWrap(nums[i], i);
        }
        int[] smallCount = new int[nums.length];
        mergeSort(numWraps, smallCount, 0, nums.length-1);
        for (int cnt: smallCount) {
            retList.add(cnt);
        }
        return retList;
    }





























    public static void main(String ...args) {
        int[] nums1 = {4,5,4,2,6,1};
        int[] nums = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        List<Integer> ret = new LC_315().countSmaller(nums);
        ret.forEach(System.out::println);
    }
}
