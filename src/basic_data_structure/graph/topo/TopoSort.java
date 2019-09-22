package basic_data_structure.graph.topo;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 拓扑排序
 *
 * 根据入度来实现
 *
 */
public class TopoSort {
    private class Node {
        int v;
        int dis; // 边权
    }


    private static final int MAX_V = 100;
    private int n;
    private ArrayList[] adj = new ArrayList[MAX_V];
    private int[] inDegree = new int[MAX_V];

    TopoSort(int n) {
        this.n = n;
    }

    private void initInDegree() {
        for (int i = 0; i < n; ++i) {
            for (Object obj: adj[i]) {
                Node node = (Node)obj;
                inDegree[node.v]++;
            }
        }
    }

    private boolean topoLogicSort() {
        int num = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 入度为0的节点入优先队列
        for (int i = 0; i < n; ++i) {
            if (inDegree[i]==0) {
                priorityQueue.offer(i);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll();
            for (Object obj: adj[u]) {
                Node node = (Node) obj;
                inDegree[node.v]--;
                if (inDegree[node.v] == 0) {
                    priorityQueue.offer(node.v);
                }
            }
            num++;
        }
        return num==n;  // 如果出队列的节点个数恰好为n，那么说明拓扑排序成功，否则拓扑排序失败，证明图中有环的存在

    }
}
