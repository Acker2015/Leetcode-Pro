package basic_data_structure.tree;

/**
 * 并查集实现
 * 1. 基本实现
 * 2. 路径压缩
 */
public class UnionFind {
    private int maxn = 1000;
    private int[] father = new int[maxn];

    /**
     * 初始化
     */
    public void init() {
        for (int i = 0; i < maxn; ++i) {
            father[i] = i;
        }
    }

    /**
     * 迭代查找父亲节点
     * @param idx
     * @return
     */
    public int findFather(int idx) {
        int originIdx = idx;
        while (idx != father[idx]) {
            idx = father[idx];
        }
        // 再走一遍，进行路径压缩
        while (originIdx != father[originIdx]) {
            int ans = originIdx;
            originIdx = father[originIdx];
            father[ans] = idx;
        }
        return idx;
    }

    /**
     * 递归查找父亲节点
     * @param idx
     * @return
     */
    public int findFatherRc(int idx) {
        if (idx == father[idx]) {
            return idx;
        }
        // return findFather(father[idx]);
        // 路径压缩
        int topFather = findFather(father[idx]);
        father[idx] = topFather;
        return topFather;
    }

    /**
     * 将两个节点建立父子关系
     * @param i
     * @param j
     */
    public void connect(int i, int j) {
        int fi = findFather(i);
        int fj = findFather(j);
        if (fi != fj) {
            father[fi] = fj;
        }
    }




}
