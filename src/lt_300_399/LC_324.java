package lt_300_399;

import java.util.Arrays;
import java.util.Random;

/**
 * [324] Wiggle Sort II
 *
 * solution1:
 * 一种比较naive的做法是先对数组进行排序,
 * 分为三组
 *      (1). 中位数m
 *      (2). 比m大的数
 *      (3). 比m小的数
 * 然后从左往右奇数索引位置放大于中位数的数,
 * 然后从右往左在偶数索引位置放小于中位数的数,
 * 剩下的位置都放中位数.
 * 其时间复杂度为O(nlog(n)), 空间复杂度为O(n).
 *  https://leetcode.com/problems/wiggle-sort-ii/discuss/77678/3-lines-Python-with-Explanation-Proof
 *
 * solution2:
 * 对上述算法做两个改变
 * 1. 找中位数可以通过找到数组第K个数来实现，平均时间复杂度为O(n)
 * 2. 需要解决的一个问题是如何可以互不干扰的放到正确位置，即需要原地操作
 *
 * 问题1可以借助快排的partition来解决
 * 问题2可以通过虚拟索引，将大于m的数映射到奇数索引处(左->右),将小于m的数映射到偶数索引处(右->左)
 *      真是索引和虚拟索引为映射关系为 (2*i+1)%(n|1)
 *      0  1  2  3  4  5  -> 真实索引
 *      1  3  5  0  2  4  -> 虚拟索引
 *
 *      所以安排两个指针left, right
 *      left从左开始，指向大于m的真实索引位置
 *      right从右开始，指向小于m的真是索引位置
 *      其实这里的思路又是partition algorithm的思想，具体看实现
 * https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
 *
 */
public class LC_324 {

    static class Solution1 {
        /**
         * 我们可以先给数组排序，然后在做调整。
         * 调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，
         * 然后从前半段的末尾取一个，在从后半的末尾取一个，这样保证了第一个数小于第二个数，
         * 然后从前半段取倒数第二个，从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，
         * 以此类推直至都取完。
         * @param nums
         */
        public void wiggleSort(int[] nums) {
            int n = nums.length, k = (n - 1) / 2, j = n-1;
            int[] tmp = new int[nums.length];
            for (int i = 0; i < n; ++i) {
                tmp[i] = nums[i];
            }
            Arrays.sort(tmp);
            for (int i = 0; i < n; ++i) {
                nums[i] = (i&1)>0 ? tmp[j--] : tmp[k--];
            }
        }
    }

    static class Solution2 {
        public void wiggleSort(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);
            int mid = nums[len/2];
            int[] ans = new int[len];
            for (int i = 0; i < len; ++i) {
                ans[i] = mid;
            }
            // left表示奇数索引(左->右)，right表示偶数索引(右->左)
            int left = 1, right = len%2==0 ? len-2:len-1;
            for (int i = 0; i < len; ++i) {
                if (nums[i] > mid) {
                    ans[left] = nums[i];
                    left += 2;
                } else if (nums[i] < mid) {
                    ans[right] = nums[i];
                    right -= 2;
                }
            }
            for (int i = 0; i < len; ++i) {
                nums[i] = ans[i];
            }
        }
    }



/*
Solution2的解释
Roughly speaking I put the smaller half of the numbers on the even indexes and the larger half on the odd indexes.

def wiggleSort(self, nums):
    nums.sort()
    half = len(nums[::2])
    nums[::2], nums[1::2] = nums[:half][::-1], nums[half:][::-1]
Alternative, maybe nicer, maybe not:

def wiggleSort(self, nums):
    nums.sort()
    half = len(nums[::2]) - 1
    nums[::2], nums[1::2] = nums[half::-1], nums[:half:-1]
Explanation / Proof
I put the smaller half of the numbers on the even indexes and the larger half on the odd indexes, both from right to left:

Example nums = [1,2,...,7]      Example nums = [1,2,...,8]

Small half:  4 . 3 . 2 . 1      Small half:  4 . 3 . 2 . 1 .
Large half:  . 7 . 6 . 5 .      Large half:  . 8 . 7 . 6 . 5
--------------------------      --------------------------
Together:    4 7 3 6 2 5 1      Together:    4 8 3 7 2 6 1 5
I want:

Odd-index numbers are larger than their neighbors.
Since I put the larger numbers on the odd indexes, clearly I already have:

Odd-index numbers are larger than or equal to their neighbors.
Could they be "equal to"? That would require some number M to appear both in the smaller and the larger half.
It would be the largest in the smaller half and the smallest in the larger half.
Examples again, where S means some number smaller than M and L means some number larger than M.

Small half:  M . S . S . S      Small half:  M . S . S . S .
Large half:  . L . L . M .      Large half:  . L . L . L . M
--------------------------      --------------------------
Together:    M L S L S M S      Together:    M L S L S L S M
You can see the two M are quite far apart. Of course M could appear more than just twice, for example:

Small half:  M . M . S . S      Small half:  M . S . S . S .
Large half:  . L . L . M .      Large half:  . L . M . M . M
--------------------------      --------------------------
Together:    M L M L S M S      Together:    M L S M S M S M
You can see that with seven numbers, three M are no problem. And with eight numbers, four M are no problem.
Should be easy to see that in general, with n numbers, floor(n/2) times M is no problem.
Now, if there were more M than that, then my method would fail. But... it would also be impossible:

If n is even, then having more than n/2 times the same number clearly is unsolvable, because you'd have to put two of them next to each other, no matter how you arrange them.
If n is odd, then the only way to successfully arrange a number appearing more than floor(n/2) times is if it appears exactly floor(n/2)+1 times and you put them on all the even indexes.
    And to have the wiggle-property, all the other numbers would have to be larger.
    But then we wouldn't have an M in both the smaller and the larger half.
    So if the input has a valid answer at all, then my code will find one.
*/



    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private int findKthNum(int[] nums, int l, int r, int k) {
        int tmp = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] > tmp) j--;
            swap(nums, i, j);
            while (i < j && nums[i] <= tmp) i++;
            swap(nums, i, j);

        }
        nums[i] = tmp;
        if (i - l + 1 == k) {
            return nums[i];
        } else if (i - l + 1 > k) {
            return findKthNum(nums, l, i-1, k);
        } else {
            return findKthNum(nums, i+1, r, k - (i-l+1));
        }
    }
    // map to virtual index
    private int vIndex(int i, int n) {
        return (1+2*i)%(n|1);
    }
    private void shuffle(int[] nums) {
        int len = nums.length;
        Random random = new Random();
        for (int i = 1; i < len; ++i) {
            swap(nums, i, random.nextInt(i));
        }
    }
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        shuffle(nums);
        // find median
        int mid = findKthNum(nums, 0, len-1, (len+1)/2);
        System.out.println(mid);
        // partition algorithm
        int left = 0, right = len-1, i = 0;
        while (i <= right) {
            if (nums[vIndex(i, len)] > mid) {
                swap(nums, vIndex(i++, len), vIndex(left++, len));
            } else if (nums[vIndex(i, len)] < mid){
                swap(nums, vIndex(i, len), vIndex(right--, len));
            } else {
                i++;
            }
        }
    }
}
