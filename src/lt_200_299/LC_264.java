package lt_200_299;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
     * 核心思想：后续的uglyNumber一定是已经找到的uglyNumber与(2,3,5)乘积的结果，只需要考虑如何每次取最小的uglyNumber即可
     * 参考cracking the coding interview
     *
     * 1.初始化一个队列array存放uglyNumber（想象一下）
     * 2.使用三个队列Q2, Q3, Q5分别存放已经找到的uglyNumber与2，3，5的乘积
     * 3.将1插入到array
     * 4.分别将1*2、1*3、1*5放入Q2,Q3,Q5
     * 5.另x为Q2,Q3,Q5中的最小值。将x添加到array的尾部，作为新的uglyNumber
     * 6. 若x存在于
     *      6.1 Q2,那么将x*2,x*3,x*5分别放到Q2,Q3,Q5. 并从Q2中将x删除
     *      6.2 Q3,那么将x*3,x*5分别放到Q3,Q5. 并从Q3中将x删除 （这里为什么不放x*2）
     *          这里由于x是从Q3中拿到的，所以x=3*y, 之前2y输出为uglyNumber时候，已经将3*2y放到了Q3中，所以这里x*2=6y就不用继续放了
     *      6.3 Q5,那么将x*5放入Q5，将Q5移除x
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        if (n < 0) return 0;
        int val = 0;
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queue5 = new LinkedList<>();
        queue3.add(1);
        for (int i = 1; i <= n; ++i) {
            int v2 = queue2.size()>0 ? queue2.peek():Integer.MAX_VALUE;
            int v3 = queue3.size()>0 ? queue3.peek():Integer.MAX_VALUE;
            int v5 = queue5.size()>0 ? queue5.peek():Integer.MAX_VALUE;
            val = Math.min(v2, Math.min(v3,v5));
            if (val == v2) {
                queue2.remove();
                queue2.add(val*2);
                queue3.add(val*3);
                //queue5.add(val*5);
            } else if (val == v3) {
                // 3x*2已经在2x*3的时候输出过了，所以不用重复输出
                queue3.remove();
                queue3.add(val*3);
            } else if (val == v5) {
                queue5.remove();
            }
            queue5.add(val*5);
        }
        return val;
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
     * 再比如 2*3和3*2会重复出现
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
