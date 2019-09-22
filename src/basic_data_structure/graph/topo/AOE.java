package basic_data_structure.graph.topo;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 关键路径
 * 求解节点的最早开始时间ve[j]以及最晚开始时间vl[j]
 *
 * ve[j]最早开始时间可以通过 {正向} 拓扑排序来求解
 *      ve[j] = max{ve[i] + length(i->j)}
 *
 *
 * vl[i]最晚开始时间可以通过 {逆向} 的拓扑排序来进行求解
 *      vl[i] = min{vl[j] - length(i->j)}
 *
 *
 */
public class AOE {
    private class Node {
        int v;
        int dis; // 边权
    }


    private static final int MAX_V = 100;
    private int n;  // 顶点个数
    private ArrayList[] adj = new ArrayList[MAX_V]; // 邻接表
    private int[] inDegree = new int[MAX_V];        // 节点入度数记录数组
    private Stack<Integer> topOrder = new Stack<>();   // 用于记录topo排序的入栈顺序 - 记录正向拓扑

    private int[] ve = new int[MAX_V];  // 节点的最早开始时间(正向拓扑)
    private int[] vl = new int[MAX_V];  // 节点的最晚开始时间(逆向拓扑)

    private void initInDegree() {
        for (int i = 0; i < n; ++i) {
            for (Object obj: adj[i]) {
                Node node = (Node)obj;
                inDegree[node.v]++;
            }
        }
    }

    private boolean topoLogicSort() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 入度为0的节点入优先队列
        for (int i = 0; i < n; ++i) {
            if (inDegree[i]==0) {
                priorityQueue.offer(i);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll();
            topOrder.push(u);
            for (Object obj: adj[u]) {
                Node node = (Node) obj;
                inDegree[node.v]--;
                if (inDegree[node.v] == 0) {
                    priorityQueue.offer(node.v);
                }
                ve[node.v] = Math.max(ve[node.v], ve[u]+node.dis);


            }
        }
        return topOrder.size()==n;  // 如果出队列的节点个数恰好为n，那么说明拓扑排序成功，否则拓扑排序失败，证明图中有环的存在
    }


    /**
     * 关键路径，不是有向无环图就返回-1, 否则返回关键路径长度
     * @return
     */
    int CriticalPath() {
        // 正向拓扑排序，求解ve数组
        if (!topoLogicSort()) {
            return -1;
        }

        for (int i = 0; i < n; ++i) {
            vl[i] = ve[n-1];
        }
        while (!topOrder.isEmpty()) {
            int u = topOrder.pop();
            for (Object obj: adj[u]) {
                Node node = (Node)obj;
                vl[u] = Math.min(vl[u], vl[node.v]-node.dis);   // 求解最迟开始时间vl数组
            }
        }

        // 获取关键路径
        for (int u = 0; u < n; ++u) {
            for (Object obj: adj[u]) {
                Node node = (Node) obj;
                int v = node.v;
                int dis = node.dis;
                int e = ve[u], l = vl[v]-dis;
                // 如果e==l,说明活动u->v就是关键路径
                if (e == l) {
                    System.out.println(u+"->"+v);
                }
            }
        }
        return ve[n-1];
    }


}
