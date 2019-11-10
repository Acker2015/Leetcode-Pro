package lt_1200_1299;

/**
 * 1255. Maximum Score Words Formed by Letters
 *
 * backtracking
 *
 * word数组的length <= 15, 可以直接按照subsets方法中的回溯
 *
 */
public class LC_1255_c162 {
    // 根据字母出现次数来判断回溯中这个word
    private boolean judge(int[] count, int[] letterArr) {
        for (int i = 0; i < 26; ++i) {
            if (letterArr[i] < count[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param wordCount     记录每个word对应的各个字符的出现次数
     * @param idx           word的索引
     * @param letterArr     记录给定letter的使用剩余次数
     * @param score         给定的字符对应的分数
     * @return
     */
    private int backtracking(int[][] wordCount, int idx, int[] letterArr, int[] score) {
        if (idx >= wordCount.length) {
            return 0;
        }
        int ans1 = backtracking(wordCount, idx+1, letterArr, score);
        int ans2 = 0;
        if (judge(wordCount[idx], letterArr)) {
            for (int i = 0; i < 26; ++i) {
                letterArr[i] -= wordCount[idx][i];
                ans2 += wordCount[idx][i] * score[i];
            }
            ans2 += backtracking(wordCount, idx+1, letterArr, score);
            for (int i = 0; i < 26; ++i) {
                letterArr[i] += wordCount[idx][i];
            }
        }
        return Math.max(ans1, ans2);
    }
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterArr = new int[26];
        for (char c: letters) {
            letterArr[c-'a']++;
        }
        // 将每个位置的word都转化成一个int[]数组表示word中每个字符的出现次数 - 后续方便进行比较
        int[][] wordCount = new int[words.length][26];
        for (int i = 0; i < words.length; ++i) {
            char[] chs = words[i].toCharArray();
            for (char c: chs) {
                wordCount[i][c-'a']++;
            }
        }
        return backtracking(wordCount, 0, letterArr, score);
    }
}
