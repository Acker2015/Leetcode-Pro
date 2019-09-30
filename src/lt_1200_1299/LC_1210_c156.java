package lt_1200_1299;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1210. Minimum Moves to Reach Target with Rotations
 * Contest 156
 * Q4
 * hard
 *
 * solution1 - Dynamic program
 * solution2 - BFS
 *
 *
 * DP + 记忆化搜索
 * 水平方向: dp[x][y][0] 表示snake tail在(x, y)并且为水平的时候到达右下角需要的最小移动次数
 * 竖直方向: dp[x][y][1] 表示snake tail在(x, y)并且为竖直的时候到达右下角需要的最小移动次数
 *
 * state transfer:
 *
 * for horizontal direction:
 *      那么此时可以选择的移动是(right, down, rotate clock-wise)
 *      1. right:               precondition -> grid[x][y+2]=0
 *      2. down:                precondition -> grid[x+1][y]=0 & grid[x+1][y+1]=0
 *      3. rotate clock-wise:   precondition -> grid[x+1][y]=0 & grid[x+1][y+1]=0
 *
 *      if all satisfy these three conditions
 *      dp[x][y][0] = Math.min(dp[x+1][y][0], dp[x+1][y][0], dp[x][y][1]) + 1
 *
 * for vertical direction:
 *      那么此时可以选择的移动是(down, right, rotate counterclockwise)
 *      1. down: precondition -> grid[x+2][y]=0
 *      2. right: precondition -> grid[x][y+1]=0 & grid[x+1][y+1]=0
 *      3. rotate counterclockwise -> grid[x][y+1]=0 & grid[x+1][y+1]=0
 *
 *      if all satisfy these three conditions
 *      dp[x][y][1] = Math.min(dp[x+1][y][1], dp[x][y+1][1], dp[x][y][0]) + 1
 *
 * pay attention:
 *      prevent same position go repeated rotate clockwise or counterclockwise, need set visited symbol
 *      为了方式在同一个点来回进行顺时针和逆时针的旋转，需要对访问过的点置访问标记
 */
public class LC_1210_c156 {
    /**
     * DP
     */
    private static class Solution1 {
        private int m, n;
        private int search(int[][] grid, int x, int y, boolean isH, Integer[][][] mem, boolean[][][] vis) {
            if (x < 0 || y < 0 || x >= m || y >= n) return Integer.MAX_VALUE;
            if (isH && y >= n-1) return Integer.MAX_VALUE;
            if (!isH && x >= m-1) return Integer.MAX_VALUE;
            // reach the aim ending point.
            if (isH && x==m-1 && y==n-2) return 0;
            if (mem[x][y][isH ? 0 : 1] != null) {
                return mem[x][y][isH ? 0 : 1];
            }
            // 如果已经访问，并且mem中没有记录值，那么说明此状态正在计算
            if (vis[x][y][isH ? 0: 1]) return Integer.MAX_VALUE;
            // set visit symbol
            vis[x][y][isH ? 0: 1] = true;
            int ans = Integer.MAX_VALUE;
            // 水平方向
            if (isH) {
                // right
                if (y+2 < n && grid[x][y+2]==0) {
                    ans = Math.min(ans, search(grid, x, y+1, isH, mem, vis));
                }
                // down and rotate clock-wise
                if (x+1 < m && grid[x+1][y]==0 && grid[x+1][y+1]==0) {
                    int way1 = search(grid, x+1, y, isH, mem, vis);   // down
                    int way2 = search(grid, x, y, !isH, mem, vis);
                    ans = Math.min(ans, Math.min(way1, way2));
                }
            } else {
                // down
                if (x+2 < m && grid[x+2][y] == 0) {
                    ans = Math.min(ans, search(grid, x+1, y, isH, mem, vis));
                }
                // right or rotate counterclockwise
                if (y+1 < n && grid[x][y+1]==0 && grid[x+1][y+1]==0) {
                    int way1 = search(grid, x, y+1, isH, mem, vis); // right
                    int way2 = search(grid, x, y, !isH, mem, vis); // rotate counterclockwise
                    ans = Math.min(ans, Math.min(way1, way2));
                }
            }
            if (ans < Integer.MAX_VALUE) {
                ans += 1;
            }
            mem[x][y][isH ? 0:1] = ans;
            return ans;
        }
        public int minimumMoves(int[][] grid) {
            if ((m=grid.length)<=1 || (n=grid[0].length)<=1) return -1;
            Integer[][][] mem = new Integer[m][n][2];
            boolean[][][] vis = new boolean[m][n][2];
            if (grid[0][0]==1 || grid[0][1] == 1) {
                return -1;
            }
            int ans = search(grid, 0, 0, true, mem, vis);
            return ans==Integer.MAX_VALUE ? -1: ans;

        }
    }




    private static class State {
        int x, y;
        boolean isH;
        State(int x, int y, boolean isH) {
            this.x = x;
            this.y = y;
            this.isH = isH;
        }
        @Override
        public String toString() {
            return x + "_"+ y + isH;
        }
    }

    /**
     * BFS 按照移动方式将状态层层转移即可
     */
    private static class Solution2 {
        private int m, n;
        public int minimumMoves(int[][] grid) {
            if ((m=grid.length) <= 1 || (n=grid[0].length) <= 1) {
                return -1;
            }
            if (grid[0][0]!=0 || grid[0][1]!=0) {
                return -1;
            }
            State state = new State(0, 0, true);
            Set<String> set = new HashSet<>();
            set.add(state.toString());
            Queue<State> queue = new LinkedList<>();
            queue.offer(state);
            int move = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    State ans = queue.poll();
                    State rightState = moveToRight(ans, grid);

                    if (rightState != null && !set.contains(rightState.toString())) {
                        if (isEnd(rightState)) return move+1;
                        set.add(rightState.toString());
                        queue.offer(rightState);
                    }
                    State downState = moveToDown(ans, grid);
                    if (downState != null && !set.contains(downState.toString())) {
                        if (isEnd(downState)) return move+1;
                        set.add(downState.toString());
                        queue.offer(downState);
                    }
                    State clockState = ans.isH ? moveClockwise(ans, grid) : moveCounterClockwise(ans, grid);
                    if (clockState != null && !set.contains(clockState.toString())) {
                        if (isEnd(clockState)) return move+1;
                        set.add(clockState.toString());
                        queue.offer(clockState);
                    }
                }
                move++;
            }
            return -1;
        }
        private boolean isEnd(State state) {
            return state.isH && state.x==m-1 && state.y==n-2;
        }
        private State moveToRight (State state, int[][] grid) {
            int x = state.x, y = state.y;
            if (state.isH && y+2 < n && grid[x][y+2]==0) {
                return new State(x, y+1, true);
            }
            if (!state.isH && y+1 < n && grid[x][y+1]==0 && grid[x+1][y+1]==0) {
                return new State(x, y+1, false);
            }
            return null;
        }
        private State moveToDown(State state, int[][] grid) {
            int x = state.x, y = state.y;
            if (state.isH && x+1 < m && grid[x+1][y]==0 && grid[x+1][y+1]==0) {
                return new State(x+1, y, true);
            }
            if (!state.isH && x+2 < m && grid[x+2][y]==0) {
                return new State(x+1, y, false);
            }
            return null;
        }
        private State moveClockwise(State state, int[][] grid) {
            int x = state.x, y = state.y;
            if (state.isH && x+1<m
                    && grid[x+1][y]==0 && grid[x+1][y+1]==0) {
                return new State(x, y, false);
            }
            return null;
        }
        private State moveCounterClockwise(State state, int[][] grid) {
            int x = state.x, y = state.y;
            if (!state.isH && y+1 < n && grid[x][y+1]==0 && grid[x+1][y+1]==0) {
                return new State(x, y, true);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1},
                {0,0,0},
                {1,0,0}
        };
        Solution2 solution = new Solution2();
        System.out.println(solution.minimumMoves(grid));
    }
    // [[0,0,0,0,0,1],[1,1,0,0,1,0],[0,0,0,0,1,1],[0,0,1,0,1,0],[0,1,1,0,0,0],[0,1,1,0,0,0]]
}
