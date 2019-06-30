package basic_data_structure.Recursive;

/**
 * 在数组A[0...n-1]中有所谓的魔术索引，满足条件A[i]=i.给定一个有序整数数组，元素各不相同，
 * 编写一个方法，在数组A中找出一个魔术索引，如果存在的话！
 *
 * follow-up 如果数组元素有重复值，又该如何处理？
 *
 * 首先思考有序数组中没有重复数的解法，brute-force遍历一遍O(n)解决
 *
 * 那么这里是否可以使用二分解决呢？尝试一下对于mid
 *      - 如果A[mid]=mid,那么mid为magic index
 *      - 如果A[mid]<mid,由于不存在重复元素，所以mid左侧不可能出现魔术索引,所以此时l=mid+1
 *      - 如果A[mid]>mid,由于不存在重复元素，所以mid右侧不可能出现魔术索引，所以此时r=mid-1
 * O(logn)解决
 *
 * 那么follow-up怎么解决？
 * 先看一个例子
 * index:   0    1   2   3   4   5   6   7   8   9   10
 * value:   -10  -5  2   2   2   3   4   7   9   12  13
 * 如果还是用上边的二分由于重复值的存在就不会一次决定结果在左边还是右边
 * 观察一下索引5处，在索引5处A[5]=3，那么即使有重复值的存在A[4]也不可能等于4，A[3]也不可能等于3，只有A[2]的地方才可能等于2
 * 所以遍历左边的时候只有遍历left -> Math.min(mid-1, A[mid])
 * 遍历右边的时候只需要遍历Math.max(mid+1, A[mid]) -> right
 */
public class MagicIndex {
    /**
     * solution1
     * brute-force 暴力解法
     * @param A
     * @return
     */
    public int findMagicIndex(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分
     * @param A
     * @return
     */
    public int fastMagicIndex(int[] A) {
        int left = 0, right = A.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (A[mid] == mid) {
                return mid;
            } else if (A[mid] < mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return A[left]==left ? left : -1;
    }

    /**
     * 此方法即使数组中的元素各不相同，这个方法的执行动作也会和二分解法几近相同
     * @param A
     * @param left
     * @param right
     * @return
     */
    public int fastMagicIndexWithComplicate(int[] A, int left, int right) {
        if (right > left || left < 0 || right >= A.length) {
            return -1;
        }
        int midIdx = left + (right-left)/2;
        int midVal = A[midIdx];
        if (midIdx == midVal) {
            return midIdx;
        }
        int leftFound = fastMagicIndexWithComplicate(A, left, Math.min(midIdx-1, midVal));
        if (leftFound >= 0) {
            return leftFound;
        }
        int rightFound = fastMagicIndexWithComplicate(A, Math.max(midIdx+1, midVal), right);
        if (rightFound >= 0) {
            return rightFound;
        }
        return -1;
    }

}
