package lt_400_499;

import java.util.*;

/**
 * [417] Pacific Atlantic Water Flow
 *
 * BFS or DFS
 * 思路就是用两个访问数组
 * 从第0行以及第0列往矩阵访问，访问到的就是可达Pacific的坐标
 * 从最后一行以及最后一列往矩阵访问，能访问到的就是可达Atlantic的坐标
 *
 * 那么结果就是同时可达两者的坐标，两个访问数组就能够表示
 */
public class LC_417 {
    int[][] dr = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    int m, n;
    private void dfs(int[][] matrix, int i, int j, boolean[][] vis) {
        if (vis[i][j]) return;
        vis[i][j] = true;
        for (int[] d: dr) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni <0 || nj < 0 || ni >= m || nj >= n) continue;
            if (vis[ni][nj] || matrix[i][j] > matrix[ni][nj]) continue;
            dfs(matrix, ni, nj, vis);
        }
    }
    public List<List<Integer>> pacificAtlantic_DFS(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>();
        if ((m=matrix.length) <= 0 || (n=matrix[0].length) <= 0) {
            return list;
        }
        boolean[][] visP = new boolean[m][n];
        boolean[][] visA = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            dfs(matrix, i, 0, visP);
            dfs(matrix, i, n-1, visA);
        }
        for (int j = 0; j < n; ++j) {
            dfs(matrix, 0, j, visP);
            dfs(matrix, m-1, j, visA);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visA[i][j] && visP[i][j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    list.add(temp);
                }
            }
        }
        return list;
    }

    public List<List<Integer>> pacificAtlantic_BFS(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>();
        if ((m=matrix.length) <= 0 || (n=matrix[0].length) <= 0) {
            return list;
        }
        boolean[][] visP = new boolean[m][n];
        boolean[][] visA = new boolean[m][n];
        Queue<int[]> queueP = new LinkedList<>();
        Queue<int[]> queueA = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            queueP.offer(new int[]{i, 0});
            visP[i][0] = true;
            queueA.offer(new int[]{i, n-1});
            visA[i][n-1] = true;
        }
        for (int j = 0; j < n; ++j) {
            queueP.offer(new int[]{0, j});
            visP[0][j] = true;
            queueA.offer(new int[]{m-1, j});
            visA[m-1][j] = true;

        }
        bfs(matrix, visP, queueP);
        bfs(matrix, visA, queueA);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visP[i][j] && visA[i][j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    list.add(temp);
                }
            }
        }
        return list;
    }

    private void bfs(int[][] matrix, boolean[][] vis, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] top = queue.poll();
                for (int[] d: dr) {
                    int ni = top[0]+d[0];
                    int nj = top[1]+d[1];
                    if (ni<0 || nj<0 || ni >= m || nj >= n) continue;
                    if (vis[ni][nj] || matrix[ni][nj] < matrix[top[0]][top[1]]) continue;
                    vis[ni][nj] = true;
                    queue.offer(new int[]{ni,nj});
                }
            }
        }
    }
    public static void main(String ...args) {
        int[][] matrix = {{7,1,17,13,9,10,5,14,0,3},{7,15,7,8,15,16,10,10,5,13},{18,9,15,8,19,16,7,5,5,10},{15,11,18,3,1,17,6,4,10,19},{3,16,19,12,12,19,2,14,5,9},{7,16,0,13,14,7,2,8,6,19},{5,10,1,10,2,12,19,1,0,19},{13,18,19,12,17,17,4,5,8,2},{2,1,17,13,14,12,14,2,16,10},{5,8,1,11,16,1,18,15,6,19},{3,8,14,14,5,0,2,7,5,1},{17,1,9,17,10,10,10,7,1,16},{14,18,5,11,17,15,8,8,14,13},{6,4,10,17,8,0,11,4,2,8},{16,11,17,9,3,2,11,0,6,5},{12,18,18,11,1,7,12,16,12,12},{2,14,12,0,2,8,5,10,7,0},{16,13,1,19,8,13,11,8,11,3},{11,2,8,19,6,14,14,6,16,12},{18,0,18,10,16,15,15,12,4,3},{8,15,9,13,8,2,6,11,17,6},{7,3,0,18,7,12,2,3,12,10},{7,9,13,0,11,16,9,9,12,13},{9,4,19,6,8,10,12,6,7,11},{5,9,18,0,4,9,6,4,0,1},{9,12,1,11,13,13,0,16,0,6},{7,15,4,8,15,17,17,19,15,1},{7,17,4,1,1,14,10,19,10,19},{10,5,12,5,8,8,14,14,6,0},{16,10,10,7,13,4,0,15,18,0},{11,2,10,6,5,13,4,5,3,1},{9,14,16,14,15,3,2,13,17,8},{19,2,10,1,2,15,12,10,2,5},{12,4,8,9,8,6,4,14,14,0},{11,17,17,4,16,13,6,15,5,7},{12,18,1,3,9,10,7,1,1,1},{18,6,10,8,12,14,9,12,10,3},{15,13,18,13,8,5,12,14,18,0},{15,4,8,9,19,18,6,19,12,0},{4,14,15,4,17,17,9,17,9,0},{6,17,16,10,3,8,8,18,15,9},{3,8,4,2,13,0,2,8,8,2},{14,12,13,12,17,4,16,9,8,7},{0,19,8,16,1,13,7,6,15,11},{1,13,16,14,10,4,11,19,9,13},{8,0,2,1,16,12,16,9,9,9},{5,2,10,4,8,12,17,0,2,15},{11,2,15,15,14,9,11,19,18,11},{4,4,1,5,13,19,9,17,17,17},{4,1,8,0,8,19,11,0,5,4},{8,16,14,18,12,2,0,19,0,13},{7,11,3,18,8,2,2,19,8,7},{3,13,6,1,12,16,4,13,0,5},{12,1,16,19,2,12,16,15,19,6},{1,7,12,15,3,3,13,17,16,12}};
        LC_417 lc_417 = new LC_417();
        lc_417.pacificAtlantic_DFS(matrix);
    }
}
