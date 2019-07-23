package lt_300_399;


import java.util.*;

public class LC_399 {
    private String getQN(String numerator, String denominator) {
        return numerator+"/"+denominator;
    }
    private double dfs(Map<String, Set<String>> denominatorMap, Map<String, Double> equationMap, Map<String, Boolean> visited, String numerator, String denominator) {
        if (!denominatorMap.containsKey(denominator) || visited.containsKey(denominator)) return -1;
        if (numerator.equals(denominator)) {
            return 1.0;
        }
        visited.put(denominator, true);
        String curEquation = getQN(numerator, denominator);
        if (equationMap.containsKey(curEquation)) {
            return equationMap.get(curEquation);
        }
        Set<String> numeratorList = denominatorMap.get(denominator);
        for (String nd: numeratorList) {
            double nowFormulaValue = equationMap.get(getQN(nd, denominator));
            double leftFormulaValue = dfs(denominatorMap, equationMap, visited, numerator, nd);
            if (leftFormulaValue >= 0) {
                return nowFormulaValue*leftFormulaValue;
            }
        }
        visited.remove(denominator);
        return -1;
    }

    /**
     * 1. if you know a/b = k, then the b/a = 1/k
     * 2. if you know a/b=k1, b/c=k2, c/d=k3 -> a/c=k1*k2, b/d=k2*k3, a/d=k1*k2*k3
     * 3. if you know a/b=k1, c/b=k2  -> a/c = k1/k2
     *
     * 图遍历（注意增加节点已访问标识）
     *
     * 注意：
     * 1. 如果分子分母相等，需要看分子是否存在给定的equation中，存在取值为1，不存在取值为0
     *
     * [["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]\n[3.0,4.0,5.0,6.0]\n[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Set<String>> helperMap = new HashMap<>();
        Map<String, Double> equationMap = new HashMap<>();
        for (int i = 0; i < equations.size(); ++i) {
            String numerator = equations.get(i).get(0);
            String denominator = equations.get(i).get(1);
            equationMap.put(getQN(numerator, denominator), values[i]);
            equationMap.put(getQN(denominator, numerator), 1.0/values[i]);
            if (!helperMap.containsKey(denominator)) {
                helperMap.put(denominator, new HashSet<>());
            }
            if (!helperMap.containsKey(numerator)) {
                helperMap.put(numerator, new HashSet<>());
            }
            helperMap.get(denominator).add(numerator);
            helperMap.get(numerator).add(denominator);
        }
        double[] result = new double[queries.size()];
        Map<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < queries.size(); ++i) {
            visited.clear();
            String numinator = queries.get(i).get(0);
            String denominator = queries.get(i).get(1);
            double ans = dfs(helperMap, equationMap, visited, numinator, denominator);
            result[i] = ans < 0 ? -1.0 : ans;
        }
        return result;
    }
}
