package lt_300_399;


import java.util.*;

/**
 * [399] Evaluate Division
 *
 * 1. if you know a/b = k, then the b/a = 1/k
 * 2. if you know a/b=k1, b/c=k2, c/d=k3 -> a/c=k1*k2, b/d=k2*k3, a/d=k1*k2*k3
 * 3. if you know a/b=k1, c/b=k2  -> a/c = k1/k2
 *
 * 图遍历（注意增加节点已访问标识）
 *
 * 注意：
 * 1. 如果分子分母相等，需要看分子是否存在给定的equation中，存在取值为1，不存在取值为0
 *
 * [["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
 * [3.0,4.0,5.0,6.0]
 * [["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
 *
 *
 * solution:
 *
 * 对于a/b=k, 那么图中就表示为两条有向边
 *  1. b->a, 权值为k
 *  2. a->b, 权值为1/k
 *
 * 最后针对每个查询在图中搜索即可
 */
public class LC_399 {
    // 建图
    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> adjMap = new HashMap<>();
        for (int i = 0; i < equations.size(); ++i) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            if (u.equals(v)) {
                adjMap.computeIfAbsent(u, k->new HashMap<>()).put(v, 1.0);
            } else {
                adjMap.computeIfAbsent(v, k->new HashMap<>()).put(u, val);
                adjMap.computeIfAbsent(u, k->new HashMap<>()).put(v, 1.0/val);
            }
        }
        return adjMap;
    }

    private double searchToCalculate(Map<String, Map<String, Double>> adjMap, String from, String to, double val, Set<String> set) {
        if (!adjMap.containsKey(from) || !adjMap.containsKey(to)) return -1;
        if (adjMap.get(from).containsKey(to)) {
            return val*adjMap.get(from).get(to);
        }
        for (Map.Entry<String, Double> entry: adjMap.get(from).entrySet()) {
            String key = from+"_"+entry.getKey();
            if (set.contains(key)) continue;
            set.add(key);
            double ret = searchToCalculate(adjMap, entry.getKey(), to, val*entry.getValue(), set);
            if (ret >= 0) {
                return ret;
            }
            set.remove(key);
        }
        return -1.0;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 邻接表
        Map<String, Map<String, Double>> adjMap = buildGraph(equations, values);
        Set<String> cacheSet = new HashSet<>();
        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            cacheSet.clear();
            String to = queries.get(i).get(0);
            String from = queries.get(i).get(1);
            // val*from=to -> to/from=val
            if (from.equals(to) && adjMap.containsKey(from)) {
                ret[i] = 1.0;
            } else {
                ret[i] = searchToCalculate(adjMap, from, to, 1.0, cacheSet);
            }
        }
        return ret;
    }

    /*
    equations = [ ["a", "b"], ["b", "c"] ],
    values = [2.0, 3.0],
    queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     */
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        Arrays.stream(new LC_399().calcEquation(equations, values, queries)).forEach(System.out::println);
    }
}
