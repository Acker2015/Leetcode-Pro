package other.O1Point3Acre.wish;

import domain.ListNode;

/**
 * describe:
 * 给了一个0/1数组，0代表绿灯，1代表红灯。反转一个区间的意思是说把这个区间里面的0变成1，1变成0，问经过一次反转最多能有多少个绿灯，然后反转区间的下标。
 * 比如数组是[0,1,1,0,1,1] 可以反转下标 [1, 5]  这个区间，得到 [0,0,0,1,0,0]  这个最多五个绿灯的数组
 * 返回[1, 5]即可（时间复杂度要求O(N)了）
 *
 * example
 * [0,1,1,0,1,1] -> [1, 5]
 * [0,1,0,0,0,1] -> [1, 1] or [5,5]
 */
public class TrafficLight {
    /**
     * 将0看成-1, 求最大字段和就可保证翻转之后区间的路灯最多
     * 双指针
     * @param lights
     * @return
     */
    public int[] maxGreenNum(int[] lights) {
        int len = lights.length;
        int i = 0, j = 0, maxSum = lights[i], sum = 0;
        int[] ret = new int[]{i,j};

        while (j < len) {
            sum += (lights[j] == 0 ? -1 : 1);
            if (sum <= 0) {
                i = ++j;
                sum = 0;
                continue;
            }
            if (sum > maxSum) {
                maxSum = sum;
                ret[0] = i;
                ret[1] = j;
            }
            j++;
        }
        return ret;
    }

    public static void main(String...args) {
        int[] lights = {0,1,0,0,0,1};
        int[] ret = new TrafficLight().maxGreenNum(lights);
        System.out.println("start="+ret[0]+", end="+ret[1]);
    }
}
