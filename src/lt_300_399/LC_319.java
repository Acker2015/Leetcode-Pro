package lt_300_399;

/**
 * [319] Bulb Switcher
 */
public class LC_319 {
    /**
     * 思考一下为什么直接就能使用Math.sqrt(n)来返回
     *
     * 由于每一个数(如果不是平方数的话)，他的因子都是成对出现的，所以每个灯都会有偶数次switch，最终状态是关闭状态
     *
     * 而平方数不同的一点是，有其中一个因子是t满足t^2=n, 所以switch的次数为奇数，最终状态就是on
     *
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
