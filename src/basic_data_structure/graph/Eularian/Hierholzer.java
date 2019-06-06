package basic_data_structure.graph.Eularian;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://blog.csdn.net/stillxjy/article/details/51956183
 * 欧拉路径 - Hierholzer算法
 */
public class Hierholzer {
    private static final int MAXN = 1001;
    private int[][] G = new int[MAXN][MAXN];    // 第0列记录节点连接边数

    private int N, M;   // N代表节点个数
    private Stack<Integer> stack = new Stack<>();

    /**
     * 递归
     * @param u
     */
    public void dfs(int u) {
        for (int v = 1; v <= N; v++) {
            if (G[u][v] > 0) {
                G[u][v]--;
                G[v][u]--;
                dfs(v);
            }
        }
        stack.push(u);
    }


    /**
     * 迭代解法-通过栈来辅助
     * @param u
     */
    public void iteration(int u) {
        List<Integer> route = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        s.push(u);
        while (!s.isEmpty()) {
            u = s.pop();
            for (int v = 1; v <= N; ++v) {
                if (G[u][v] > 0) {
                    G[u][v]--;
                    G[v][u]--;
                    s.push(v);
                }
            }
            // 走投无路，开始出栈
            route.add(0, s.pop());
        }
    }

}
