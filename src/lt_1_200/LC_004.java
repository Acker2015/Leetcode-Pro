package lt_1_200;


public class LC_004 {
    private int findKth(int[] A, int i, int[] B, int j, int k) {
        if (i >= A.length) {
            return B[j+k-1];
        }
        if (j >= B.length) {
            return A[i+k-1];
        }
        if (k == 1) {
            return Math.min(A[i], B[j]);
        }
        // 如果A中元素个数不足K/2个，那么即使都算上的话，在B中也会选择超过k/2个元素。反之同理。所以初始化为最大值
        int A_mid = Integer.MAX_VALUE, B_mid = Integer.MAX_VALUE;
        if (i + k/2 - 1 < A.length) {
            A_mid = A[i+k/2-1];
        }
        if (j + k/2 - 1 < B.length) {
            B_mid = B[j+k/2-1];
        }
        // 如果A_mid < B_mid, 说明A数组中前K/2个数肯定都在 {两个有序数组中寻找第K个数} 之内
        if (A_mid < B_mid) {
            return findKth(A, i+k/2, B, j, k - k/2);
        } else {
            return findKth(A, i, B, j + k/2, k - k/2);
        }
    }

    /**
     * solution1
     *
     * 问题转化为在两个有序数组中找第K个数
     * 那么如何实现在两个有序数组中寻找第K个数呢？假设两个数组分别是A，B
     * 从最简单的第一层算起，两个数组的起始索引都是0。在A中找K/2个数A_mid=A[k/2-1], 在B中寻找第K/2个数B_mid=B[k/2-1]. 通过比较这两个数的大小来进行问题分治
     * 如果A_mid < B_mid, 说明A数组中前K/2个数肯定都在 {两个有序数组中寻找第K个数} 之内
     * A: 0, 1, ..., K/2-1(A_mid)
     * B: 0, 1, ..., K/2-1(B_mid)
     *
     * 假设A中存在一个索引x(x <= k/2-1), 不在{两个有序数组中寻找第K个数} 之内，由于数组有序， 那么A中索引大于x的都不在{两个有序数组中寻找第K个数} 之内
     * 也就相当于{两个有序数组中寻找第K个数}会在在B中会选出多于K/2的元素，那么由于B_mid>A_mid>A[x], 所以假设失败
     * 得证：如果A_mid < B_mid, 说明A数组中前K/2个数肯定都在 {两个有序数组中寻找第K个数} 之内
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m+n+1)/2;
        int r = (m+n+2)/2;
        // 如果m+n是奇数，那么l==r, 如果m+n是偶数，那么l和r代表中间的两个位置
        return (findKth(nums1,0,nums2,0,l)+findKth(nums1,0,nums2,0, r)) / 2.0;
    }




    /**
     * solution2
     * [4] Median of Two Sorted Arrays
     *
     * time O(log(min(m,n)))
     * 二分 在nums1中找到一个分割点i,在num2中找到一个分割点j
     * 使得i+j正好可以平分nums1和nums2的总集合
     * 即 (i+j)*2 = m+n
     * nums1[i-1] <= nums2[j]
     * nums2[j-1] <= nums1[i]
     *
     * 这样就能保证
     * 集合 S1->{nums1[0],...,nums1[i-1],nums2[0],...,nums2[j-1]}中的任一个数
     * 总体小于
     * 集合 S2->{nums1[i],...,nums[m-1],nums2[j],...,nums2[n-1]}中的任一个数
     * 由于两个集合各占一半，所以中位数来自于两个集合的解
     * 1. (m+n)%2==1 -> median = Max-Of-S1
     * 2. (m+n)%2==0 -> median = (Max-Of-S1 + Min-Of-S2)/2.0
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // make sure length of nums1 is greater than the length of nums2
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m, half = (m+n+1)/2;
        while (left <= right) {
            int i = (right-left)/2 + left;
            int j = half - i;
            if (i > left && nums1[i-1] > nums2[j]) {
                right = i-1; // i is too big
            } else if (i < right && nums2[j-1] > nums1[i]) {
                left = i+1; // i is too small
            } else {
                // nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i]
                int leftMax=0;
                if (i == 0) leftMax = nums2[j-1];
                else if (j==0) leftMax = nums1[i-1];
                else leftMax = Math.max(nums1[i-1], nums2[j-1]);
                if ((m+n)%2==1) return leftMax;
                int rightMin=0;
                if (i==m) rightMin = nums2[j];
                else if (j == n) rightMin = nums1[i];
                else rightMin = Math.min(nums1[i], nums2[j]);
                return (leftMax+rightMin)/2.0;
            }
        }
        return 0.0;
    }
    public static void main(String ...args) {
        int[] nums1 = {1, 3, 5, 6, 7, 8, 9,10};
        int[] nums2 = {2, 4};
        LC_004 lc_004 = new LC_004();
        System.out.println(lc_004.findMedianSortedArrays(nums1, nums2));
    }
}
