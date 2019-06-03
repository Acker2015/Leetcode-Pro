package basic_data_structure.graph;

/**
 * 全源最短路径问题
 */
public class Floyd {
    private static final int MAXV = 200;
    private static final int INF = 0x3fffffff;
    private int n; // n为顶点数
    private int[][] G;  // 邻接表，INF表示边不存在
    int[][] dis;

    /**
     * 初始化dis数组
     * 邻接矩阵辅助初始化
     */
    private void init() {
        dis = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dis[i][j] = i==j ? 0 : G[i][j];
            }
        }
    }

    /**
     * O(V^3)
     *
     * 如果存在顶点k，使得以k作为中介点时顶点i和顶点j的当前最短路径缩短，则使用顶点k作为顶点i和顶点j的中介点，
     * 即dis[i][k]+dis[k][j] < dis[i][j]
     * 令dis[i][j]=dis[i][k]+dis[k][j]
     */
    public void floyd() {
        init();
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (dis[i][k]!=INF && dis[k][j]!=INF && dis[i][k]+dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k]+dis[k][j];    // 更新最短路径
                    }
                }
            }
        }
    }
}
