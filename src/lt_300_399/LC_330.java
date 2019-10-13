package lt_300_399;

/**
 * Greedy
 *
 * 如何贪心
 * [330] Patching Array
 *
 * 如果[0, i] 的元素可以表达范围是[1, x]
 * 1. 如果 x+1 >= nums[i+1], nums[i+1]加入之后可表达的范围就是 nums[i+1]之后的x范围，即nums[i+1]+x
 * 2. 如果 x+1 < nums[i+1], 此时需要先将(x+1)加入进来(patchNum+1), 此时能表达的范围就是 x + (x+1)
 *      然后继续判断新的范围上限与nums[i+1]的关系
 *
 * lastContinousIdx表示之前可以表达的最大范围
 * 初始化lastContinousIdx=0
 *
 * 比如 1 2 3 8 22
 * (1). lastContinousIdx+1 >= nums[0]=1, lastContinousIdx = lastContinousIdx + nums[0] = 1
 * (2). 到达nums[1]=2之后，lastContinousIdx+1 >= nums[1]=2,那么最大可表达的就是lastContinousIdx=lastContinousIdx+nums[1]=3
 * (3). 当遍历nums[2]=3的时候，由于之前已经能表达了3，此时之前的lastContinousIdx最长能表达长度为3，
 *      所以遇到3之后最长能表达lastContinousIdx=lastContinousIdx+nums[2]=3+3=6
 * (4). 继续遍历到nums[3]=8，发现之前的最大表达区间为6, lastContinousIdx+1<nums[3]，can't reach 8,
 *      所以需要将下一个数字7 (lastContinousIdx+1)加入(patchNum++),
 *      加入7之后能够表达的范围就是lastContinousIdx=lastContinousIdx+(lastContinousIdx+1)=13
 * (5). 继续遍历8，13 >= 8, 所以此时的最大范围为8+13=21
 * 继续遍历22，由于之前可以遍历到21了，遇到22正好可以连接上。加入22之后能够表达的范围就是22+21=43
 *
 */
public class LC_330 {
    public int minPatches(int[] nums, int n) {
        if (n <= 0) return 0;
        int patchNum = 0;
        long lastContinousIdx = 0;
        int i = 0;
        while (i < nums.length || lastContinousIdx < n) {
            if (lastContinousIdx >= n) break;
            if (i < nums.length) {
                if (lastContinousIdx+1 >= nums[i]) {
                    lastContinousIdx += nums[i];
                    i++;
                } else {
                    // add (lastContinousIdx+1)
                    patchNum++;
                    lastContinousIdx += (lastContinousIdx+1);
                }
            } else {
                patchNum++;
                lastContinousIdx += (lastContinousIdx+1);
            }
        }
        return patchNum;
    }

    public static void main(String[] args) {
        int[] nums = {1,7,21,31,34,37,40,43,49,87,90,92,93,98,99};
        LC_330 lc_330 = new LC_330();
        System.out.println(lc_330.minPatches(nums, 12));
    }
}
