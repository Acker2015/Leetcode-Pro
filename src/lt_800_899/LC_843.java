package lt_800_899;

import java.util.*;

/**
 * [843] Guess the Word
 *
 * solution1:
 * 随机选择：
 * 每次随机选择一个word，guess(word)的到匹配的结果t, 在wordList中筛选出与word对应为匹配结果为t的集合作为新的wordList
 *
 * solution2
 * https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
 * 每次猜word可以通过随机选择的方式，但是也可以有策略的选择
 *
 * 由于word的长度为6，而且wordList中的单词都是随机产生，所以每个单词做匹配结果为0的概率为80% (25/26)^6
 * 那么如何尽量让每次guess之后能够eliminate（剔除）更多的单词，或者说是保留更少的单词呢？
 *
 * 计算wordList中每一个word跟其他word的match结果为0的个数cnt，选择match为0个数cnt最小的那么word A，假设与A匹配结果为0的集合为S
 * 这样guess(A)=0的结果的概率为80%， 下一次选择的集合有80%的概率会是S
 *
 */
public class LC_843 {
    interface Master {
        public int guess(String word);
    }

    private int match(String s1, String s2) {
        int mtc = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) == s2.charAt(i)) {
                mtc++;
            }
        }
        return mtc;
    }

    public void findSecretWord_random(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            Random random = new Random();
            int idx = random.nextInt(wordlist.length);
            String A = wordlist[idx];
            x = master.guess(A);
            List<String> tempList = new ArrayList<>();
            for (String word: wordlist) {
                if (this.match(A, word) == x) {
                    tempList.add(word);
                }
            }
            wordlist = tempList.toArray(new String[]{});
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            // 选择匹配值为0的个数最小的word
            Map<String, Integer> cntMap = new HashMap<>();
            for (String str1: wordlist) {
                for (String str2: wordlist) {
                    if (match(str1, str2) == 0) {
                        cntMap.put(str1, cntMap.getOrDefault(str1, 0)+1);
                    }
                }
            }
            String word = wordlist[0];
            int cnt = cntMap.getOrDefault(word, 0);
            for (String str: wordlist) {
                if (cntMap.getOrDefault(str, 0) < cnt) {
                    word = str;
                    cnt = cntMap.getOrDefault(str, 0);
                }
            }
            // guess word, 根据反馈排除word，保留满足条件word，准备下一次的guess
            x = master.guess(word);
            List<String> tempList = new ArrayList<>();
            for (String str: wordlist) {
                if (match(str, word) == x) {
                    tempList.add(str);
                }
            }
            wordlist = tempList.toArray(new String[]{});
        }
    }
}
