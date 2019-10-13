package lt_1200_1299;

/**
 * 1224-Maximum Equal Frequency
 *
 * 初看这个题目可能没有任何思路，但是仔细想想其实能够满足条件的前缀无非就两种?
 *
 * a. [1,1,2,2,3,3,3] 多一个元素3，并且3已经出现在之前的序列
 * b. [1,1,2,2,3,3,4] 多一个元素4，并且4未出现在之前的序列中
 *
 * 所以这题是否可以通过(相同元素的最大个数maxRepeatNum) 和 (maxRepeatNum出现的最大次数numOfMaxRepeat) 来解决呢？
 *
 * 记录三个变量
 * 1. 相同元素的最大个数maxRepeatNum
 * 2. maxRepeatNum出现的最大次数numOfMaxRepeat
 * 3. 遍历前缀中不同元素的个数k
 *
 * 那么：
 * 对于上述a情况需要满足的条件是->
 *      a1: numOfMaxRepeat==1，即最大个数maxRepeatNum的出现次数只能有一次 (忽略此条件可能会产生干扰项)
 *      a2: k*maxRepeatNum-(k-1) == 前缀元素总个数
 * 对于上述b情况需要满足的条件是->
 *      b1: numOfMaxRepeat*maxRepeatNum + 1 == 前缀元素总个数
 *
 * 特殊情况：
 * 对于出现prefix中的元素出现次数都是一次的时候需要特殊判断一下，因为这个时候一直是满足条件的
 *
 * time: O(N)
 *
 * https://leetcode.com/problems/maximum-equal-frequency/discuss/403678/C%2B%2BO(n)consider-for-four-situations
 */
public class LC_1224_c158 {
    public int maxEqualFreq(int[] nums) {
        if (nums.length <= 1) return 1;
        int[] mem = new int[100001];
        int maxRet = 0;             // 记录结果
        int maxRepeatNum = 0;       // 最大重复元素的个数
        int numOfMaxRepeat = 0;     // 出现次数为maxRepeatNum的元素个数
        int k = 0;                  // prefix中不同元素的个数


        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            mem[num]++;
            if (mem[num] == 1) {
                k += 1;
            }
            if (mem[num] > maxRepeatNum) {
                maxRepeatNum = mem[num];
                numOfMaxRepeat = 1;
            } else if (mem[num] == maxRepeatNum) {
                numOfMaxRepeat++;
            }
            // 多出来的一个元素跟其他元素相同，如[1, 1, 2, 2, 3, 3, 3] 最后一个3是多余的
            // 只有最大个数出现的次数为1的时候，才需要满足这个公式
            if (numOfMaxRepeat==1 && k*maxRepeatNum - (k-1) == i+1) {
                maxRet = i+1;
            }
            // 多出来的一个元素跟其他元素不同，如[1, 1, 2, 2, 3, 3, 4] 最后一个4是多余的
            if (numOfMaxRepeat*maxRepeatNum+1 == i+1) {
                maxRet = i+1;
            }
            // 特殊处理当maxRepeatNum=1的时候，这种情况一直满足条件
            if (maxRepeatNum == 1) {
                maxRet = i+1;
            }
        }
        return maxRet;
    }

    public static void main(String[] args) {
        int[] nums = {10,2,8,9,3,8,1,5,2,3,7,6};
        LC_1224_c158 solution = new LC_1224_c158();
        assert solution.maxEqualFreq(nums) == 8;

        int[] newnums = {1,1,1,2,3,3,3,4,4,4,5,5,5,42,97,5,46,53,100,63,27,12,83,82,21,77,58,93,86,5,72,16,23,99,88,47,96,16,26,89,41,19,40,42,78,43,29,51,50,92,76,76,54,7,46,93,26,56,94,34,100,26,97,60,73,46,31,26,2,50,15,55,42,64,30,72,18,8,58,50,81,84,60,91,2,3,48,65,65,5,49,31,9,78,94,32,11,33,31,53,19,92,14,94,27,65,92,14};
        System.out.println(solution.maxEqualFreq(newnums));

    }
}
