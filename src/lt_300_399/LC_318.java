package lt_300_399;

/**
 * [318] Maximum Product of Word Lengths
 *
 * 由于只会出现lower case letter,使用int的bit位来记录字母是否出现过
 *
 * time: O(n^2)
 * space: O(n)
 */
public class LC_318 {
    public int maxProduct(String[] words) {
        int len = words.length;
        if (len <= 1) return 0;
        int[] mem = new int[len];
        int[] wordLen = new int[len];
        for (int i = 0; i < len; ++i) {
            int temp = 0;
            char[] chs = words[i].toCharArray();
            for (char c: chs) {
                temp |= (1<<(c-'a'));
            }
            mem[i] = temp;
            wordLen[i] = words[i].length();
        }
        int maxProduct = 0;
        for (int i = 0; i < len; ++i) {
            for (int j = i+1; j < len; ++j) {
                if ((mem[i]&mem[j]) > 0) continue;
                maxProduct = Math.max(wordLen[i]*wordLen[j], maxProduct);
            }
        }
        return maxProduct;
    }
}
