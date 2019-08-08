package lt_400_499;

import java.util.Stack;

/**
 * [456] 132 Pattern
 * stack
 */
public class LC_456 {
    /**
     * Solution1
     * 此解法自己画图更加清晰
     * s1,s2,s3 -> s1<s3<s2
     * 从头遍历，对于每一个range对[s1, s2]入栈
     * 对于新遍历的元素s3
     * 1. if s3<=s1, 将新的range对入栈[s3, s3]
     * 2. else if s3 > s1 && s3 < s2, 满足132约束，return true.
     * 3. else (s3>=s2) 那么更新栈顶的范围，并对栈中的其他range对做一下处理
     *      假设此时栈顶的range对为[c, d], 次栈顶为[a, b], 已知的是c < a（根据1，只有满足这个才会入栈）
     *     DO:
     *      3.1 如果 a<d<b, return true
     *      3.2 如果 d<=b, 同时保留两个range对，结束循环
     *      3.3 如果 d>=b, 说明[c,d]包含[a,b], 将[a,b]从栈中删掉，继续执行循环
     */
    public boolean find132pattern1(int[] nums) {
        Stack<int[]> stack = new Stack<>();
        for (int num: nums) {
            if (stack.isEmpty()) {
                stack.push(new int[]{num, num});
            } else if (num <= stack.peek()[0]) {
                if (stack.peek()[0] == stack.peek()[1]) {
                    stack.pop();
                }
                stack.push(new int[]{num, num});
            } else if (num > stack.peek()[0] && num < stack.peek()[1]) {
                return true;
            }  else {
                // num >= pair[1]
                int[] pair = stack.pop();
                pair[1] = num;
                while (!stack.isEmpty()) {
                    // known, pair[0] <= cur[0]
                    int[] cur = stack.pop();
                    if (cur[0] < num && num < cur[1]) {
                        return true;
                    } else if (num <= cur[0]) {
                        stack.push(cur);
                        break;  // end
                    }
                    // otherwise， the cur array would be discarded
                }
                stack.push(pair);
            }
        }
        return false;
    }

    /**
     * Solution2
     * one-pass, iterate from end to start
     * (s1, s2, s3) -> s1<s3<s2  跟踪s3的位置， 使得s3尽可能大
     * we keep track of highest value of s3 for each valid (s2 > s3) pair while searching for a valid s1 candidate to the left.
     */
    public boolean find132pattern(int[] nums) {
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        // iterate s1
        for (int i = nums.length-1; i >= 0; --i) {
            if (nums[i] < s3) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                s3 = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String ...args) {
        LC_456 lc_456 = new LC_456();
        //int[] nums = new int[]{4,5,6,7,1,4,5};
        int[] nums = {-1,3,2,0};
        System.out.println(lc_456.find132pattern(nums));
    }
}
