package lt_400_499;

/**
 * [481] Magical String
 *
 * 仔细观察 字符串前边的值是能够影响字符串后续生长的，只要保证前边每个字符产生的后续不连续(即只能组成1或者2即可)
 *
 */
public class LC_481 {
    /**
     * 相当于前面的数字会影响后边数字的生成
     * 如初始为 1
     * 然后为 12 这里为什么不能是11，按照规则如果是11 -> 那么会对应着2开头的数字
     * 然后为 122，left指针指向索引2处
     * 然后为 12211 -> 这里第三个数2对应的后续为11, 不能是22(12222)的原因是会与前面连起来变成4个2
     * 然后为 122112 -> left指针指向3
     * 然后为 1221121 -> left指针指向4
     * ...
     */
    public int magicalString(int n) {
        if (n == 0) return 0;
        if (n <= 3) return 1;
        int[] ans = new int[n];
        ans[0]=1;
        ans[1]=2;
        ans[2]=2;
        int oneNum = 1;
        int left = 2, idx = 3;
        while (idx < n) {
            int digit = ans[left++];  // num of val(前边的数字是1还是2)
            int val = ans[idx-1]==1 ? 2: 1;  // 1 or 2 (后边应该放置1还是2，与idx-1处的值不同即可)
            ans[idx++] = val;
            oneNum += val==1 ? 1 : 0;
            // 如果digit=2，后边需要再放一次val，凑成连续个数为2
            if (digit == 2 && idx < n) {
                ans[idx++] = val;
                oneNum += val==1 ? 1 : 0;
            }
        }
        return oneNum;
    }
}
