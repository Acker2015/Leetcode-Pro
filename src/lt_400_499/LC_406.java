package lt_400_499;


import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * [406] Queue Reconstruction by Height
 * 矮子插队无所谓，反正高个子看不到
 * https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89359/Explanation-of-the-neat-Sort%2BInsert-solution
 */
public class LC_406 {
    /**
     * Solution1
     * 先根据身高由矮到高排序(高度相同按照k升序)
     * 那么可以肯定的是最矮的人的位置k，一定是最终的位置k，因为找不到剩下的每个人都比他高，所以前边只能有k个people
     *
     * 根据上述思想，将剩余的people进行填空
     * people初始序列号为k
     * 从头遇到身高比他高或者身高相同的或者空节点，为对k-1，当k为0的时候，插入到第一个空节点即可
     *
     * 利用思想，先插身高较矮的人，可以确定之前插入的人的身高"不高于"正在插入的这个人
     * @param people
     * @return
     */
    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, (a, b)->a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]);
        boolean[] vis = new boolean[people.length];
        int[][] ret = new int[people.length][2];
        for (int i = 0; i < people.length; ++i) {
            int seq = people[i][1];
            for (int j = 0; j < ret.length; ++j) {
                if (seq == 0 && !vis[j]) {
                    vis[j] = true;
                    ret[j] = people[i];
                    break;
                }
                if ((!vis[j] && seq > 0) || (vis[j] && ret[j][0]==people[i][0])) {
                    seq--;
                }
            }
        }
        return ret;
    }

    /**
     * Solution2:
     * 矮子插队无所谓，反正高个子看不到
     * 根据身高降序(身高相同，k升序)排序
     * 不断将遍历到的人插入到k索引位置上，后插入的人的身高都"不高于"已经插入的人
     * 矮子也不影响身高比较高的人的视线
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b)-> a[0]!=b[0]?b[0]-a[0]:a[1]-b[1]);
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; ++i) {
            list.add(people[i][1], people[i]);
        }
        int[][] ret = new int[people.length][2];
        for (int i = 0; i < list.size(); ++i) {
            ret[i] = list.get(i);
        }
        return ret;
    }
    public static void main(String...args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        LC_406 lc_406 = new LC_406();
        int[][] ret = lc_406.reconstructQueue(people);
        for (int i = 0; i < ret.length; ++i) {
            System.out.print("[" + ret[i][0] + "," + ret[i][1] + "]  ");
        }
    }
}
