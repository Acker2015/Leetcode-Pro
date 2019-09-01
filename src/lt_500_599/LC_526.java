package lt_500_599;


/**
 * 回溯
 * [526] Beautiful Arrangement
 * The back tracking start from the back so that each search won't go too deep before it fails,
 * because smaller numbers have higher chance to be divisible among themselves.
 */
public class LC_526 {
    int count = 0;
    private boolean canSet(int val, int i) {
        return val > i ? val%i==0 : i%val==0;
    }
    private void backtracking(int index, int N, boolean[] mem) {
        if (index < 1) {
            count++;
            return;
        }
        for (int i = N-1; i >= 0; --i) {
            if (mem[i] || !canSet(i+1, index)) continue;
            mem[i] = true;
            backtracking(index-1, N, mem);
            mem[i] = false;
        }
    }
    public int countArrangement(int N) {
        boolean[] mem = new boolean[N];
        this.count = 0;
        backtracking(N, N, mem);
        return this.count;

    }
}
