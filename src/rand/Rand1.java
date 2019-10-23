package rand;

import java.util.Random;

/**
 * 已知随机函数rand(),以p的概率产生0，以1-p的概率产生1
 *
 * P1. 由rand()函数产生Rand1(),要求等概率生成0和1
 * P2. 由rand()函数生成RandN(),要求等概率生成1,2,3,...,n
 * P3. rand5() -> rand7()
 * P4. randN() -> randM()
 *
 * Solution:
 * 1. 由于rand()生成0和1的概率不一样，所以连续生成两次数据, 01和10生成的概率都是p(1-p)
 *
 */
public class Rand1 {
    // 假设p=2/5
    private int rand() {
        Random random = new Random();
        int r = random.nextInt(5); // 0,1,2,3,4
        // p概率生成0，1-p概率生成1
        if (r <= 1) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * solution1
     * 1. 由于Rand()生成0和1的概率不一样，所以连续生成两次数据, 01和10生成的概率都是p(1-p)
     * @return
     */
    public int Rand1() {
        int r1 = rand();
        int r2 = rand();
        if (r1==0 && r2==1) {
            return 0;
        }
        if (r1==1 && r2==0) {
            return 1;
        }
        return Rand1();
    }

    /**
     * 第一步：由solution1得到Rand1()
     * 第二步：计算整数n的二进制表示所拥有的位数k，k = 1 +log2n（log以2为底n）
     * 第三步：调用ｋ次Rand()产生随机数。
     * @return
     */
    public int RandN(int n) {
        int k = (int)(Math.log(n)/Math.log(2))+1;
        int r = 0;
        for (int i = 0; i < k; ++i) {
            if (Rand1()==1) {
                r |= (1<<i);
            }
        }
        if (r > n || r == 0) {
            return RandN(n);
        }
        return r;
    }


    /**
     * Rand5用来随机删除1,2,3,4,5 本题中Rand5使用上述的RandN(5)来实现
     *
     * 通过Rand5来生成Rand7
     * 通过 rand5*5+(rand5-1) 生成0,1,2,3,4,5,6,7,8,9,...,29(5*5+4)
     * 去掉29，留下1,2,...,28用来除以4产生0-6的数，最后增1即可产生Rand7
     * @return
     */
    public int Rand7() {
        int r;
        do {
            r = RandN(5)*5 + (RandN(5)-1);
        } while (r >= 28);
        return r % 7 + 1;
    }
}