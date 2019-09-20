package lt_500_599;

import java.util.HashSet;
import java.util.Set;

/**
 * [547] Friend Circles
 * union-find
 */
public class LC_547 {
    /**
     * union-find
     * @param friends
     * @param id
     * @return
     */
    private int topFriends(int[] friends, int id) {
        if (friends[id] == id) {
            return id;
        }
        // 路径压缩
        int top = topFriends(friends, friends[id]);
        friends[id] = top;
        return top;
    }
    public int findCircleNum(int[][] M) {
        int N = M.length;
        if (N <= 1) {
            return N;
        }

        int[] friends = new int[N];
        for (int i = 0; i < N; ++i) {
            friends[i] = i;
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (M[i][j] == 1 && i!=j) {
                    int top1 = topFriends(friends, i);
                    int top2 = topFriends(friends, j);
                    friends[top1] = top2;
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            set.add(topFriends(friends, i));
        }
        return set.size();
    }

    public static void main(String ...args) {
        int[][] M = {{1,1,0}, {1,1,0},{0,0,1}};
        System.out.println(new LC_547().findCircleNum(M));
    }
}
