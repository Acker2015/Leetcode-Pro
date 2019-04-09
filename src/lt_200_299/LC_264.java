package lt_200_299;


import java.util.PriorityQueue;

public class LC_264 {
    private int[] factors = {2,3,5};

    /**
     * solution1
     * ugly number都是由更小ugly number乘上2,3,5得来
     * 所以就可以借助优先队列，每次取出最小的ugly number U，并将产生的三个ugly Number放入(2*U, 3*U, 5*U)
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1L);
        int ith = 0;
        while (priorityQueue.size() > 0) {
            long num=priorityQueue.remove();
            ith++;
            if (ith == n) {
                return (int)num;
            }
            for (int f:factors) {
                if (!priorityQueue.contains(f*num)) {
                    priorityQueue.add(f*num);
                }
            }
        }
        return -1;
    }

    /**
     * Solution2
     * dynamic program
     *
     * main point: new ugly number can be calculated by the product of smaller ugly number and (2, 3, 5)
     *
     * ugly number 1, 2, 3, 4, 5, 6, 8, 9, 10, 12
     * make U as the smaller ugly number
     *
     * U*2 -> 1*2 2*2 3*2 4*2 5*2 6*2 ...
     * U*3 -> 1*3 2*3 3*3 4*3 5*3 6*3 ...
     * U*5 -> 1*5 2*5 3*5 4*5 5*5 6*5 ...
     *
     * 使用索引分别记录(2, 3, 5)对应的U的索引，每次取U*2, U*3, U*5的最小值，并将对最小值对应的索引后移
     * 注意：U*2, U*3，U*5由于对应的U不一样，这次对于重复情况需要将索引一次性后移
     * 比如 U*2 = U'*3, min=U*2, 这时候就应该将(2, 3)对应的ugly number索引都增1，重复的new ugly number只记录一次
     *
     * https://www.geeksforgeeks.org/ugly-numbers/
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] uglys = new int[n];
        uglys[0]=1;
        int index2 = 0, index3 = 0, index5= 0;
        int u2 = 2 * uglys[index2], u3 = 3 * uglys[index3], u5 = 5 * uglys[index5];
        for (int i = 1; i < n; ++i) {
            int min = Math.min(Math.min(u2, u3), u5);
            uglys[i] = min;
            if (u2==min) {
                index2++;
                u2 = uglys[index2]*2;
            }
            if (u3==min) {
                index3++;
                u3 = uglys[index3]*3;
            }
            if (u5 == min) {
                index5++;
                u5 = uglys[index5]*5;
            }
        }
        return uglys[n-1];
    }

    public static void main(String ...args) {
        LC_264 lc_264 = new LC_264();
        System.out.println(lc_264.nthUglyNumber1(1690));
    }

}
