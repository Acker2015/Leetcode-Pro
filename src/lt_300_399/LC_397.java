package lt_300_399;

/**
 * [397] Integer Replacement
 题意概览：对于输入的n,如果n是偶数，只能将n减半。如果n为奇数，可以选择加一或者减一，求最快将n变为1的操作步数

 解法解析：贪心,bit-manipulation
 1). 为什么会想到二进制，因为无论是将n减半，还是在n(odd)基础上+1,-1，都在试图将n二进制最低位置为0，由此想到二进制解法
 2). 对于111011，怎么判断+1与-1哪个更好呢？
 111011 -> 111010 -> 11101 -> 11100 -> 1110  ->  111 -> 110 -> 11 -> 10 -> 1 (多一步，111011->111010 选择减一)
 111011 -> 111100 -> 11110 -> 1111  -> 10000 -> 1000 -> 100 -> 10 -> 1       (111011->111100 选择加一)
 所以需要选择加一还是减一的时候，要选择将尽量多的低位连续1置为0
 n=3除外，因为n=3如果在加一的话，bit位数增加一位，会增加操作
 11 -> 10  -> 1
 11 -> 100 -> 10 -> 1

 So the logic is:

 If n is even, halve it.
 If n=3 or n-1 has less 1's than n+1, decrement n.
 Otherwise, increment n.
 */
public class LC_397 {
    // 想办法尽量消除掉更多个二进制1
    public int integerReplacement(int n) {
        int num = 0;
        while (n!=1) {
            if ((n&1)==0) {
                n >>>= 1;
            } else if (n == 3 || ((n>>>1)&1)==0) {
                n--;
            } else {
                n++;
            }
            num++;
        }
        return num;
    }
}
