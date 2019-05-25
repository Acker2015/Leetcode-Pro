package lt_300_399;
import java.util.*;

/**
 *
 * [310-Minimum Height Trees] (https://leetcode.com/problems/minimum-height-trees/description/)
 *
 * MHTS出现在graph的最长路径上，并且最大有两个节点
 * 1. 最长路径长度为偶数的时候，会有两个MTH，中间两个节点
 * 2. 最长路径长度为奇数的时候，只有一个MTH，中间节点
 *
 * 方法一：找到最长路径，利用前驱路径来获取MTH
 *        注意这里如何找最长路径，首先随便找一个节点，寻找其出发的最长路径叶子节点root1, 然后以root1为起点找到的最长路径就是graph的最长路径root1->root2
 *        通过记录路径前缀数组来获取MTH
 *
 * 方法二：通过不断将度为1的节点砍掉，最后一个被砍掉的节点就是MTH
 */
public class LC_310 {
    private int[] pre;
    private int getLongestPath(int root, ArrayList[] adj) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(root);
        set.add(root);
        int leaf = root;
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                leaf = queue.poll();
                for (Object obj: adj[leaf]) {
                    int next = (int) obj;
                    if (!set.contains(next)) {
                        pre[next] = leaf;
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return leaf;
    }
    private void back(int start, int end, List<Integer> path) {
        path.add(end);
        if (start == end) return;
        back(start, pre[end], path);
    }

    /**
     * 方法一
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> retList = new ArrayList<>();
        if (n <= 1) {
            retList.add(0);
            return retList;
        }
        pre = new int[n];
        ArrayList[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList();
        }
        for (int i = 0; i < edges.length; ++i) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        int root1 = getLongestPath(0, adj);
        int root2 = getLongestPath(root1, adj);
        List<Integer> path = new ArrayList<>();
        back(root1, root2, path);
        int size = path.size();
        if (size % 2 ==0) {
            retList.add(path.get(size/2-1));
            retList.add(path.get(size/2));
        } else {
            retList.add(path.get(size/2));
        }
        return retList;
    }

    /**
     *  方法二： 通过不断将度为0的节点砍掉，最后一个被砍掉的节点就是MTH
     *  BFS
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        ArrayList<Integer> retList = new ArrayList<>();
        if (n <= 1) {
            retList.add(0);
            return retList;
        }
        int[] degree = new int[n];
        ArrayList[] edgesList = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edgesList[i] = new ArrayList();
        }
        for (int i = 0; i < edges.length; ++i) {
            edgesList[edges[i][0]].add(edges[i][1]);
            edgesList[edges[i][1]].add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1) {
                queue.offer(i);
                degree[i]--;
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            retList.clear();
            while(size-- > 0) {
                int root = queue.poll();
                retList.add(root);
                degree[root] = 0;
                for (Object obj: edgesList[root]) {
                    int next = (int)obj;
                    degree[next]--;
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return retList;
    }

    public static void main(String ...args) {
        int n = 6;
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        LC_310 lc_310 = new LC_310();
        List<Integer> ret = lc_310.findMinHeightTrees(n, edges);
        ret.forEach(System.out::println);
    }
}
