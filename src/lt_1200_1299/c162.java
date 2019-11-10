package lt_1200_1299;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/11/10
 */
public class c162 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ret = new ArrayList<>(2);
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < colsum.length; ++i) {
            int[] tmp = new int[2];
            if (colsum[i] == 2) {
                tmp[0]=tmp[1]=1;
                ans1++;ans2++;
            } else {
                if (ans1 < upper) {
                    tmp[0] = 1;
                    ans1++;
                } else {
                    tmp[1] = 1;
                    ans2++;
                }
            }
            if (ans1 > upper || ans2 > lower) {
                ret.clear();
                break;
            }
        }
        return ret;
    }

    private int maxScore;
    private void backtracking(int[][] wordCount, int idx, int[] letterArr, int[] score, int cur) {
        maxScore = Math.max(maxScore, cur);
        if (idx >= wordCount.length) return;
        backtracking(wordCount, idx+1, letterArr, score, cur);
        if (judge(wordCount[idx], letterArr)) {
            for (int i = 0; i < 26; ++i) {
                letterArr[i] -= wordCount[idx][i];
                cur += wordCount[idx][i] * score[i];
            }
            backtracking(wordCount, idx+1, letterArr, score, cur);
            for (int i = 0; i < 26; ++i) {
                letterArr[i] += wordCount[idx][i];
                cur -= wordCount[idx][i] * score[i];
            }
        }

    }
    private boolean judge(int[] count, int[] letterArr) {
        for (int i = 0; i < 26; ++i) {
            if (letterArr[i] < count[i]) {
                return false;
            }
        }
        return true;
    }
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        maxScore = 0;
        int[] letterArr = new int[26];
        for (char c: letters) {
            letterArr[c-'a']++;
        }
        int[][] wordCount = new int[words.length][26];
        for (int i = 0; i < words.length; ++i) {
            char[] chs = words[i].toCharArray();
            for (char c: chs) {
                wordCount[i][c-'a']++;
            }
        }
        backtracking(wordCount, 0, letterArr, score, 0);
        return maxScore;
    }

    public static void main(String[] args) {
        String[] words = {"leetcode"};
        char[] letters = {'l','e','t','c','o','d'};
        int[] score = {4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10};
        c162 c162 = new c162();
        System.out.println(c162.maxScoreWords(words, letters, score));
    }


}
