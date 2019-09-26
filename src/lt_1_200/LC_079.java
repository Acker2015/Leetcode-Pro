package lt_1_200;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class LC_079 {
	/**
	 * 回溯
	 * 判断x,y是否可走
	 * @param board
	 * @param x
	 * @param y
	 * @param num
	 * @param word
	 * @return
	 */
	public boolean backtracking(char[][] board, int x, int y, int num, String word) {
        int m = board.length, n = board[0].length;
        // 判断x,y是否合法，以及对应左边值是否等于word指定的位置字符
        if (x < 0 || y < 0 || x >= m || y >= n || board[x][y] != word.charAt(num)) return false;
        if (num == word.length()-1) return true;
        char tmp = board[x][y];
        // 将此处的左边值置为不可用
        board[x][y] = '0';
        boolean ans = backtracking(board, x-1, y, num+1, word) 
                || backtracking(board, x+1, y, num+1, word)
                || backtracking(board, x, y-1, num+1, word)
                || backtracking(board, x, y+1, num+1, word);
        // 将此处的左边值重新置为可用，向上回溯
        board[x][y] = tmp;
        return ans;
    }
    public boolean exist(char[][] board, String word) {
        if (word.length() <= 0) return true;
        if (board.length == 0 || board[0].length == 0) return false;
        int m = board.length, n = board[0].length;
        char c = word.charAt(0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == c && backtracking(board, i, j, 0, word)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String ...args) {
    		char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
    		String word = "ABCB";
    		LC_079 lc_079 = new LC_079();
    		System.out.println(lc_079.exist(board, word));
    }
}
