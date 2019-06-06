package basic_data_structure.graph.Eularian;


import java.util.Stack;

public class Fleury {
    private static final int MAXN = 1001;
    private int[][] G = new int[MAXN][MAXN];    // 第0列记录节点连接边数

    private int N, M;   // N代表节点个数
    private Stack<Integer> stack = new Stack<>();

    public Fleury() {
        // 初始化G，G[i][0] 存放与节点i连接的剩余边数
    }

    /**
     * time O(E)
     * bad: O(VE)
     * 返回的状态说明是否经过了一个桥
     * @param u
     * @return
     */
    private boolean dfs(int u) {
        // 进入某一个节点时推入节点，如果误入歧途需要负责弹出
        stack.push(u);
        // G[u][0】代表该节点所邻接的边的数目，此段代码检查是否走完所有edge，或者没有走完(桥)
        if (G[u][0] == 0) {
            boolean flag = true;
            for (int i = 1; i <= N; ++i) {
                if (i == u) continue;
                flag = G[i][0]==0 && flag;
            }
            if (!flag) {
                // 没有走完(桥),刚刚走的这条边为桥
                stack.pop();
            }
            return flag;
        }
        for (int v = 1; v <= N; ++v) {
            if (G[u][v] > 0) {
                G[u][v] -= 1;
                G[v][u] -= 1;
                G[u][0] -= 1;
                G[v][0] -= 1;
                if (dfs(v)) {
                    return true;
                } else {
                    // 撤销删除
                    G[u][v] += 1;
                    G[v][u] += 1;
                    G[v][0] += 1;
                    G[u][0] += 1;
                }
            }
        }
        stack.pop();
        return false;
    }
    public void find() {
        int u = 1;
        for (u = 1; u <= N; ++u) {
            if ((G[u][0]&1)>0) break;   // 度为奇数的节点为起始点
        }
        stack.clear();
        if (u == N+1) {
            dfs(1);
        } else {
            dfs(u);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
    public static void main(String ...args) {
        Fleury fleury = new Fleury();
        fleury.find();
    }
}
