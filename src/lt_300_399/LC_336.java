package lt_300_399;


import java.util.*;

/**
 * [336] Palindrome Pairs
 *
 * 字典树 + search
 * 1. 现将数组中的每个word插入到Trie中，word倒序插入(最后一个字符先插入)
 * 2. 每个Trie节点除了next数组还需要记录两个信息
 *      2.1 记录index信息，表示当前节点是否为word结束，并记录在数组中的索引.(为了后续search的时候去重，防止同一个word与自身构成回文)
 *      2.2 记录list列表，表示以此node为前缀 && 其所有后缀子串为回文串
 *
 * 所以建树需要O(n*k^2)
 *
 * how to search?
 * 对每个word在树中查找，这次是根据word从索引0开始搜索。
 * a. 如果word没有搜索结束并且遇到中间结束节点(node.index>=0), 需要判断word剩余部分是否为回文
 * b. 如果word比较结束，并且root不为null。那么需要将root的后续后缀为回文串的列表加入结果集中(自身索引要跳过)
 *
 * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
 *
 * 建字典树的时间复杂度为 O(n*k^2)
 * 为什么是k^2?
 * 一个是对每个节点遍历建立节点或者找到下一个位置
 * 一个是需要判断尾部剩余是否为回文
 */
public class LC_336 {
    private static class TrieNode {
        TrieNode[] next;
        int index;              // 如果当前节点表示有word，那么记录其word索引，否则为-1
        List<Integer> list;     // 记录所有以当前node为前缀 && 其所有后缀子串为palindrome的列表(包括当前word)

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }
    static class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            TrieNode root = new TrieNode();
            // build trie
            for (int i = 0; i < words.length; i++) {
                addWord(root, words[i], i);
            }
            // search for result pairs.
            for (int i = 0; i < words.length; i++) {
                search(words, i, root, res);
            }
            return res;
        }

        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                int j = word.charAt(i) - 'a';
                if (root.next[j] == null) {
                    root.next[j] = new TrieNode();
                }
                // 如果rest子串为palindrome，那么加入到节点的list中
                if (isPalindrome(word, 0, i)) {
                    root.list.add(index);
                }
                root = root.next[j];
            }
            root.list.add(index);
            root.index = index;
        }

        private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
            for (int j = 0; j < words[i].length(); j++) {
                // 如果当前root是Trie中的单词结尾，那么需要判断其是否满足回文 (主要判断word剩余部分是否为回文)
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    res.add(Arrays.asList(i, root.index));
                }

                root = root.next[words[i].charAt(j) - 'a'];
                if (root == null) return;
            }
            // 如果word[i]已经遍历结束，那么需要把root节点中所有满足回文加入结果集
            for (int j : root.list) {
                if (i == j) continue;   // 相同串，跳过
                res.add(Arrays.asList(i, j));
            }
        }
        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String[] words = {"abcd","dcba","lls","s","sssll"};
        String [] words2 = {"bat","tab","cat"};
        Solution solution = new Solution();
        List<List<Integer>> ret = solution.palindromePairs(words2);
        for (List<Integer> list : ret) {
            System.out.println(list.get(0) + ", " + list.get(1));
        }
    }
}
