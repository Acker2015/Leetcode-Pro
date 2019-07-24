package lt_600_699;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [675] Cut Off Trees for Golf Event
 * hard
 * BFS
 */
public class LC_675 {
    private int[][] dr = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int m, n;
    /**
     * 题目意思有点难理解，其实意思就是对于坐标值>1的点按照高度从低到高进行cut，计算需要最少走的步数，
     * 1. 使用优先队列存储tree的坐标，按照tree height升序作为默认排序
     * 2. 每次出队列的slice就是下一次要去的坐标，那么只需要使用BFS来求现在的坐标start到下一个坐标end的最小距离即可
     *
     * 在第二步 start->end不可达的时候，路径被obstacle阻隔，需要返回-1帮助判断
     *
     * time complexity O(m^2*n^2)
     *
     * @param forest
     * @return
     */
    public int cutOffTree(List<List<Integer>> forest) {
        if ((m=forest.size()) <= 0 || (n=forest.get(0).size()) <= 0) return 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (forest.get(i).get(j) > 1) {
                    priorityQueue.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[]{0, 0};
        int step = 0;
        while (!priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.poll();
            int temp = getDistance(forest, start, cur);
            if (temp == -1) return -1;
            step += temp;
            start[0] = cur[0];
            start[1] = cur[1];
        }
        return step;
    }

    private int getDistance(List<List<Integer>> forest, int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] vis = new boolean[m*n];
        queue.offer(start);
        vis[start[0]*n+start[1]] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (cur[0]==end[0] && cur[1] == end[1]) {
                    return depth;
                }
                for (int k = 0; k < dr.length; ++k) {
                    int ni = cur[0] + dr[k][0];
                    int nj = cur[1] + dr[k][1];
                    if (ni < 0 || nj < 0 || ni >= m || nj >= n) continue;
                    if (vis[ni*n+nj] || forest.get(ni).get(nj) == 0) continue;
                    vis[ni*n+nj] = true;
                    queue.offer(new int[]{ni,nj});
                }
            }
            depth++;
        }
        return -1;
    }
}
