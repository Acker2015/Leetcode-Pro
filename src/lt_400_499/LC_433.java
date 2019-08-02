package lt_400_499;

import java.util.*;

/**
 * [433] Minimum Genetic Mutation
 * 解法1. BFS
 * 解法2. two-direction BFS 解法类似word ladder
 */
public class LC_433 {
    private char[] geneChars = {'A', 'C', 'G', 'T'};

    /**
     * solution1
     * 单向BFS
     */
    public class Solution1 {
        public int minMutation(String start, String end, String[] bank) {
            Set<String> bankSet = new HashSet<>();
            bankSet.addAll(Arrays.asList(bank));
            int depth = 0;
            Queue<String> queue = new LinkedList<>();
            Map<String, Boolean> vis = new HashMap<>();
            vis.put(start, true);
            queue.offer(start);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    String gene = queue.poll();
                    char[] chs = gene.toCharArray();
                    for (int i = 0; i < chs.length; ++i) {
                        char origin = chs[i];
                        for (char c: geneChars) {
                            chs[i] = c;
                            String temp = String.valueOf(chs);
                            if (vis.containsKey(temp) || !bankSet.contains(temp)) continue;
                            if (temp.equals(end)) {
                                return depth+1;
                            }
                            vis.put(temp, true);
                            queue.offer(temp);
                        }
                        chs[i] = origin;
                    }
                }
                depth++;
            }
            return -1;
        }
    }


    public class Solution2 {
        /**
         * 双向BFS， two-direction BFS
         * 必然会在中间处相遇，所以只需要记录最新的头尾BFS层元素集合即可
         */
        public int minMutation(String start, String end, String[] bank) {
            Set<String> bankSet = new HashSet<>();
            bankSet.addAll(Arrays.asList(bank));
            int depth = 0;
            // 检查end的合法性
            if (!bankSet.contains(end)) {
                return -1;
            }
            Set<String> startSet = new HashSet<>(), endSet = new HashSet<>();
            startSet.add(start);
            endSet.add(end);
            bankSet.remove(start);
            bankSet.remove(end);
            while (!startSet.isEmpty() && !endSet.isEmpty()) {
                if (startSet.size() > endSet.size()) {
                    Set<String> tmp = startSet;
                    startSet = endSet;
                    endSet = tmp;
                }
                Set<String> middleRet = new HashSet<>();
                for (String gene: startSet) {
                    char[] chs = gene.toCharArray();
                    for (int i = 0; i < chs.length; ++i) {
                        char tmp = chs[i];
                        for (char c: geneChars) {
                            chs[i] = c;
                            String nextGene = String.valueOf(chs);
                            if (endSet.contains(nextGene)) return depth+1;
                            if (bankSet.contains(nextGene)) {
                                middleRet.add(nextGene);
                                bankSet.remove(nextGene);   // bank中删除元素
                            }
                        }
                        chs[i] = tmp;
                    }
                }
                startSet = middleRet;
                depth++;
            }
            return -1;
        }
    }
}
