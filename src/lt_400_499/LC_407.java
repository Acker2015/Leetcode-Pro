package lt_400_499;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [407] Trapping Rain Water II
 *
 * BFS
 *
 * 由于某处能储藏多少水其实是看周围的最低高度来决定的，那么如何定义周围的最低高度呢？
 * 可以通过向剥洋葱一样来从外围往里处理，并且往里边处理的时候优先处理height较低的(因为较低的高度才可能成为瓶颈)
 * 这个时候就需要最小堆的支持（优先队列）
 *
 * 这个过程记录边界的最大水位高度max，如果出队列的高度大于max, 那么更新max为更大
 * 如果出队列的高度小于height，那么说明这个位置积水咯，水量为(max-当前位置水位高度)
 *
 * BFS过程中使用vis数组来记录位置是否已经被访问
 *
 * 视频解释：https://leetcode.com/problems/trapping-rain-water-ii/discuss/89472/Visualization-No-Code
 *
 *
 给一个2Dmap, 每个position 有 height. 找Trapping water sum.
 #### Min Heap
 - 用PriorityQueue把选中的height排序,为走位, create class Cell (x,y, height).

 ##### 注意几个理论
 - 1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block
 - 2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层
 - 以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.

 ##### Steps
 - 1. process的时候，画个图也可以搞清楚: 就是四个方向都走走，用curr cell的高度减去周围cell的高度.
 - 2. 若大于零，那么周围的cell就有积水: 因为cell已经是外围最低, 所以内部更低的, 一定有积水.
 - 3. 每个visited的cell都要mark, avoid revisit
 - 4. 根据4个方向的走位 `(mX, mY)` 创建新cell 加进queue里面: cell(mX, mY) 已经计算过积水后, 外围墙小时, `(mX, mY)`就会变成墙.
 - 5. 因为做的是缩小一圈的新围墙, height = Math.max(cell.h, neighbor.h);
 - 和trapping water I 想法一样。刚刚从外围，只是能加到跟外围cell高度一致的水平面。往里面，很可能cell高度变化。
 - 这里要附上curr cell 和 move-to cell的最大高度。

 ##### 为什么想到用Heap (min-heap - priorityQueue)
 - 要找到bucket的最短板
 - 每次需要最先处理最短的那条 (on top)

 ##### 为什么从外向里遍历
 - 木桶理论, 包水, 是从外面包住里面
 - 洋葱剥皮, 用完丢掉
 */
public class LC_407 {
    private int[] dx = {0,0,1,-1};
    private int[] dy = {1,-1,0,0};

    private static class Tuple {
        int x, y;
        int height;
        public Tuple(int x, int y, int height) {
            this.x = x; this.y = y;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int m, n;
        if ((m=heightMap.length) <= 0 || (n=heightMap[0].length) <= 0) return 0;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        int max = 0;
        int water = 0;
        for (int i = 0; i < m; ++i) {
            pq.offer(new Tuple(i, 0, heightMap[i][0]));
            pq.offer(new Tuple(i, n-1, heightMap[i][n-1]));
            vis[i][0] = true;
            vis[i][n-1] = true;
        }
        for (int j = 1; j < n-1; ++j) {
            pq.offer(new Tuple(0, j, heightMap[0][j]));
            pq.offer(new Tuple(m-1, j, heightMap[m-1][j]));
            vis[0][j] = true;
            vis[m-1][j] = true;
        }
        while (!pq.isEmpty()) {
            Tuple tmp = pq.poll();
            if (tmp.height > max) {
                max = tmp.height;
            } else {
                water += (max - tmp.height);
            }
            for (int i = 0; i < 4; ++i) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || vis[nx][ny]) continue;
                vis[nx][ny] = true;
                pq.offer(new Tuple(nx, ny, heightMap[nx][ny]));
            }
        }
        return water;
    }
}
