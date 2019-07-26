package lt_700_799;

import java.util.*;

public class LC_753 {
    /**
     * 构造欧拉环求欧拉路径的解法
     *
     * We can create a graph.. each vertex is any possible strings contains n - 1 digits(all permutations). and each vertex will have k outgoing edges...
     * each points to anther vertex...that vertex can be obtain by appending i to the end of the original vertex... then take the last n - 1 digits...
     *
     * for example for case of n = 4, k = 6... "000" is one of the vertices, it will have 6 outgoing edges, pointing to "000", "001", "002", "003", "004", "005" respectively...
     * this operation is equal to adding one digit to the vertex, and the next vertex would use the last n-2 digits of the previous vertex.
     * 比如在顶点123,
     *      边1 -> 1231 -> 取后三位（相当于23作为前缀与123共用） -> 231
     *      边2 -> 1232 -> 232
     *      边3 -> 1233 -> 233
     *
     * And the original problem of finding shorted string equals to finding an Eulerian cycle of that graph.. and since all the vertices in the directed graph have equal in degree and out degree (both are k)... there definitely exists such Eulerian cycle.
     * 这样最终构成的欧拉路径
     * 最后的结果就是取第一个顶点中的全部字符，后边的取边值（相当于下一个节点的最后一个字符）
     *
     * Proof completed...
     * Here is the algorithm I used by creating the graph, and then find the Eulerian cycle(post-order).
     *
     * Your runtime beats 93.61 % of java submissions
     *
     * 最终串的长度为 n-1 + (k^(n-1))*k = n-1+k^n
     *
     */
    public static class Solution1 {
        // Eulerian cycle
        public String crackSafe(int n, int k) {
            if (n == 1) {
                StringBuilder helper = new StringBuilder();
                for (int i = 0; i < k; ++i) {
                    helper.append(i);
                }
                return helper.toString();
            }
            Map<String, List<String>> graph = new HashMap<>();
            List<String> permutations = new ArrayList<>();
            generatePermutation(n-1, k, new StringBuilder(), permutations);
            // build graph
            for (String perm: permutations) {
                graph.put(perm, new ArrayList<>());
                String pre = perm.substring(1);
                for (int i = 0; i < k; ++i) {
                    graph.get(perm).add(pre+i);
                }
            }
            List<String> eulerianPath = new ArrayList<>();
            getEulerianPath(permutations.get(0), graph, eulerianPath);
            StringBuilder builder = new StringBuilder();
            builder.append(eulerianPath.get(0));
            for (int i = 1; i < eulerianPath.size(); ++i) {
                builder.append(eulerianPath.get(i).charAt(n-2));
            }
            return builder.toString();
        }
        // generate all the permutation
        private void generatePermutation(int num, int k, StringBuilder builder, List<String> ves) {
            if (num == 0) {
                ves.add(builder.toString());
                return;
            }
            for (int i = 0; i < k; ++i) {
                builder.append(i);
                generatePermutation(num-1, k, builder, ves);
                builder.deleteCharAt(builder.length()-1);
            }
        }
        // 欧拉路径 post-order
        private void getEulerianPath(String start, Map<String, List<String>> map, List<String> eulerianPath) {
            while (map.get(start).size() > 0) {
                String next = map.get(start).remove(0);
                getEulerianPath(next, map, eulerianPath);
            }
            eulerianPath.add(0, start);
        }
    }


    /**
     * Solution2
     * DFS
     * 在解法一种欧拉路径证明成立的情况下, 可以知道password集合中每个password值会出现一次，并且总个数为k^n
     * 意思就是结果的任意一个长度n的串不会重复, 使用set来帮助去重判断
     * 最终串的长度为 n-1+k^n
     *
     */
    public static class Solution2 {
        private boolean crack(int leftNum, Set<String> passwordSet, StringBuilder builder, int n, int k) {
            if (leftNum == 0) {
                return true;
            }
            String prePassword = builder.substring(builder.length()-n+1);
            for (int i = 0; i  < k; ++i) {
                String currentPassword = prePassword+String.valueOf(i);
                if (!passwordSet.contains(currentPassword)) {
                    passwordSet.add(currentPassword);
                    builder.append((char)('0'+i));
                    if (crack(leftNum-1, passwordSet, builder, n, k)) {
                        return true;
                    }
                    builder.deleteCharAt(builder.length()-1);
                    passwordSet.remove(currentPassword);
                }
            }
            return false;
        }
        public String crackSafe(int n, int k) {
            if (n <= 0 || k <= 0) return "";
            int passNum = (int) Math.pow(k, n); // num of password
            String initPass = String.join("", Collections.nCopies(n, "0"));
            StringBuilder builder = new StringBuilder();
            builder.append(initPass);
            Set<String> passwordSet = new HashSet<>();
            passwordSet.add(initPass);
            crack(passNum-1, passwordSet, builder, n, k);
            return builder.toString();
        }
    }

    public static void main(String ...args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.crackSafe(2,2));
    }
}
