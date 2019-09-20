package lt_1100_1199;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 	1182-Shortest Distance to Target Color
 *
 * 	you are given an array colors, in which there are three colors:1, 2 and 3.
 * 	You are also given some queries, Each query consists of two integers i and c,
 * 	return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 *
 * 	example:
 * 	Input: colors=[1,1,2,1,3,2,2,3,3], queries = [[1,3], [2,2], [6,1]]
 * 	Output: [3,0,3]
 *  explanation:
 *  The nearest 3 from index 1 is at index 4 (3 steps away)
 *  The nearest 2 from index 2 is at index 2 itself (0 steps away)
 *  The nearest 1 from index 6 is at index 3 (3 steps)
 *
 */
public class LC_1182 {
    static class Solution1 {
        private int binarySearch(List<Integer> list, int loc) {
            int left = 0, right = list.size(), mid;
            while (left < right) {
                mid = left + (right-left)/2;
                if (list.get(mid) < loc) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == list.size()) {
                return loc - list.get(left-1);
            } else if (left == 0) {
                return list.get(left) - loc;
            } else {
                return Math.min(list.get(left)-loc, loc-list.get(left-1));
            }

        }
        /**
         * 分别收集1，2，3的位置为三个数组
         * 对于每一个query，在对应的数组中进行二分查找
         * @param colors
         * @param queries
         * @return
         */
        public int[] shortestDistanceColor(int[] colors, int[][] queries) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < colors.length; ++i) {
                map.computeIfAbsent(colors[i], k->new ArrayList<>()).add(i);
            }
            int[] ret = new int[queries.length];
            for (int i = 0; i < queries.length; ++i) {
                if (map.containsKey(queries[i][1])) {
                    ret[i] = binarySearch(map.get(queries[i][1]), queries[i][0]);
                } else {
                    ret[i] = -1;
                }
            }
            return ret;
        }
    }

    static class Solution2 {
        /**
         * 预处理
         * 记录每一个位置正向1，2，3的距离以及负向1，2，3的距离
         * @param colors
         * @param queries
         * @return
         */
        public int[] shortestDistanceColor(int[] colors, int[][] queries) {
            // record1[i][1]表示索引i的右侧第一个1出现的位置
            int[][] record1 = new int[colors.length][4];
            // record2[i][1]表示索引i的左侧第一个1出现的位置
            int[][] record2 = new int[colors.length][4];
            int ans1 = 0, ans2 = 0, ans3 = 1;
            for (int i = 0; i < colors.length; ++i) {
                int tmp = colors[i]==1 ? ans1 : (colors[i] == 2 ? ans2 : ans3);
                for (int j = tmp; j <= i; ++j) {
                    record1[j][colors[i]] = i;
                }
                if (colors[i] == 1) {
                    ans1 = i+1;
                } else if (colors[i] == 2) {
                    ans2 = i+1;
                } else {
                    ans3 = i+1;
                }
            }
            ans1 = ans2 = ans3 = colors.length-1;
            for (int i = colors.length-1; i >= 0; --i) {
                int tmp = colors[i]==1 ? ans1 : (colors[i] == 2 ? ans2 : ans3);
                for (int j = tmp; j >= i; --j) {
                    record2[j][colors[i]] = i;
                }
                if (colors[i] == 1) {
                    ans1 = i-1;
                } else if (colors[i] == 2) {
                    ans2 = i-1;
                } else {
                    ans3 = i-1;
                }
            }
            int[] ret = new int[queries.length];
            for (int i = 0; i < queries.length; ++i) {
                int idx = queries[i][0];
                int color = queries[i][1];
                int dis = Integer.MAX_VALUE;
                if (record1[idx][color] > 0) {
                    dis = Math.min(dis, record1[idx][color]-idx);
                }
                if (record2[idx][color] > 0) {
                    dis = Math.min(dis, idx - record2[idx][color]);
                }
                ret[i] = dis == Integer.MAX_VALUE ? -1 : dis;
            }
            return ret;
        }
    }

}
