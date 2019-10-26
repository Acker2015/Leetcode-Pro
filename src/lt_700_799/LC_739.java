package lt_700_799;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. Daily Temperatures
 *
 * map, stack
 */
public class LC_739 {
    /**
     * solution1
     * 由于数组中温度是30-100
     *
     * 那么倒序遍历将温度对应的索引记住，
     * 比如遇到温度75
     *      1. mem中遍历找到第一个比75大的索引
     *      2. mem中将所有小于75的温度处的记录值置为-1 (因为75的存在，所以往右找第一个大的索引只要找到75，就不会继续向后找)
     *      3. mem[75]处的值设置为当前遍历索引位置
     */
    public static class Solution1 {
        // 求右边比他大的第一个温度
        public int[] dailyTemperatures(int[] T) {
            int maxt = 101;
            int[] mem = new int[maxt];
            int[] ret = new int[T.length];
            Arrays.fill(mem, -1);
            for (int i = T.length-1; i >= 0; --i) {
                int tmp = T[i];
                for (int j = tmp+1; j < maxt; ++j) {
                    if (mem[j] >= 0) {
                        ret[i] = mem[j]-i;
                        break;
                    }
                }
                // 将更远的低温度清除
                for (int j = 30; j < tmp; ++j) {
                    mem[j] = -1;
                }
                mem[tmp] = i;
            }
            return ret;
        }
    }

    /**
     * 使用stack来实现
     * 从后往前遍历温度数组
     * 每次遇到新的温度，将栈中小于等于ta的温度出栈，那么剩余的栈顶元素的位置就是右边第一个大于它的温度
     */
    public static class Solution2 {
        public int[] dailyTemperatures(int[] T) {
            Stack<int[]> stack = new Stack<>();
            int j = T.length-1;
            int[] ret = new int[T.length];
            while (j >= 0) {
                while (!stack.isEmpty() && T[j] >= stack.peek()[0]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ret[j] = stack.peek()[1]-j;
                }
                stack.push(new int[]{T[j], j});
                j--;
            }
            return ret;
        }
    }

    /**
     * solution3
     * 同样是栈的思想，这次是正向遍历栈中存放索引
     * 遇到一个新的温度nt，那么对于栈中温度小于nt的温度就相当于找到的右边第一个大的温度
     *
     * 依次出栈即可
     */
    public static class Solution3 {
        public int[] dailyTemperatures(int[] T) {
            Stack<Integer> stack = new Stack<>();
            int[] ret = new int[T.length];
            for (int i = 0; i < T.length; ++i) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int idx = stack.pop();
                    ret[idx] = i-idx;
                }
                stack.push(i);
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        Solution1 solution = new Solution1();
        Arrays.stream(solution.dailyTemperatures(T)).forEach(System.out::println);
    }
}
