package lt_200_299;

import java.util.ArrayList;
import java.util.List;

/**
 * [212] Word Search II
 *
 *
 * 字典树 + DFS
 */
public class LC_212 {
    private int[] d1 = {-1, 1, 0, 0};
    private int[] d2 = {0, 0, -1, 1};
    private int m, n;

    private void dfs(char[][] board, int x, int y, TrieNode node, List<String> retList) {
        char c = board[x][y];
        if (c == '#' || node.next[c-'a'] == null) return;
        int idx = c - 'a';
        if (node.next[idx].word != null) {
            retList.add(node.next[idx].word);
            node.next[idx].word = null;
        }
        board[x][y] = '#';
        for (int i = 0; i < 4; ++i) {
            int nx = x + d1[i];
            int ny = y + d2[i];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            dfs(board, nx, ny, node.next[idx], retList);
        }
        board[x][y] = c;
    }

    /**
     * 字典树+DFS回溯
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords (char[][] board, String[] words) {
        List<String> retList = new ArrayList<>();
        m = board.length;
        if (m <= 0) return retList;
        n = board[0].length;
        if (n <= 0) return retList;
        TrieNode node = this.buildTrie(words);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(board, i, j, node, retList);
            }
        }
        return retList;
    }
    // 构建字典树
    private TrieNode buildTrie(String[] words) {
        TrieNode node = new TrieNode();
        for (String word: words) {
            TrieNode ans = node;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (ans.next[c-'a'] == null) {
                    ans.next[c-'a'] = new TrieNode();
                }
                ans = ans.next[c-'a'];
            }
            ans.word = word;
        }
        return node;
    }
    private class TrieNode {
        String word;
        TrieNode[] next = new TrieNode[26];
    }


    public static void main(String ...args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        char[][] b2 = {{'b'},{'a'}, {'b'}, {'b'}, {'a'}};
        String[] words = {"oath","pea","eat","rain"};
        String[] words2 = {"baa","abba","baab","aba"};
        LC_212 lc_212 = new LC_212();
        System.out.println(lc_212.findWords(board, words));
    }
}
