package lt_300_399;


import java.util.*;

/**
 * [365] Water and Jug Problem
 */
public class LC_365 {

    // 辗转相除法，计算最大公约数
    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);

    }

    /**
     * 方法1
     * https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution
     *
     * z % gcd(a, b) == 0
     * gcd(a,b)使用辗转相除法
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater1(int x, int y, int z) {
        if (z > x+y) return false;
        if (x == z || y == z || x+y==z) return true;
        return z%gcd(x, y) == 0;
    }


    /**
     * 方法二：
     * intuitive
     * BFS
     * 每次遇到两个容器A, B都有六个选择
     * 1) 清空A
     * 2) 清空B
     * 3) 倒满A
     * 4) 倒满B
     * 5) 将A中倒入B (直到A为空，或者B满)
     * 6) 将B中倒入A (直到B为空，或者A满)
     * 使用HashSet记录状态，防止重复状态出现
     */
    private class Jugs {
        public int lessCapacity;
        public int moreCapacity;

        public int aimVal;
        public int lessVal;
        public int moreVal;

        public Jugs(int x, int y, int z) {
            lessCapacity = Math.min(x, y);
            moreCapacity = Math.max(x, y);
            aimVal = z;
            this.lessVal = 0;
            this.moreVal = 0;
        }

        public boolean end() {
            return lessVal==aimVal || moreVal==aimVal || lessVal+moreVal==aimVal;
        }
        public Jugs copy() {
            Jugs jugs = new Jugs(this.lessCapacity, this.moreCapacity, this.aimVal);
            jugs.lessVal = this.lessVal;
            jugs.moreVal = this.moreVal;
            return jugs;
        }
        @Override
        public String toString() {
            return "lessVal:"+lessVal+", moreVal:"+moreVal;
        }
    }

    private Jugs emptyLess(Jugs jugs) {
        if (jugs.lessVal == 0) {
            return null;
        }
        Jugs newJugs = jugs.copy();
        newJugs.lessVal = 0;
        return newJugs;
    }

    private Jugs emptyMore(Jugs jugs) {
        if (jugs.moreVal == 0) {
            return null;
        }
        Jugs newJugs = jugs.copy();
        newJugs.moreVal = 0;
        return newJugs;
    }
    private Jugs fullLess(Jugs jugs) {
        if (jugs.lessVal == jugs.lessCapacity) {
            return null;
        }
        Jugs newJugs = jugs.copy();
        newJugs.lessVal = newJugs.lessCapacity;
        return newJugs;
    }
    private Jugs fullMore(Jugs jugs) {
        if (jugs.moreVal == jugs.moreCapacity) {
            return null;
        }
        Jugs newJugs = jugs.copy();
        newJugs.moreVal = newJugs.moreCapacity;
        return newJugs;
    }
    private Jugs lessToMore(Jugs jugs) {
        if (jugs.lessVal == 0 || jugs.moreVal==jugs.moreCapacity) {
            return null;
        }
        int val = Math.min(jugs.lessVal, jugs.moreCapacity-jugs.moreVal);
        Jugs newJugs = jugs.copy();
        newJugs.lessVal -= val;
        newJugs.moreVal += val;
        return newJugs;
    }
    private Jugs moreToLess(Jugs jugs) {
        if (jugs.lessVal == jugs.lessCapacity || jugs.moreVal == 0) {
            return null;
        }
        int val = Math.min(jugs.moreVal, jugs.lessCapacity-jugs.lessVal);
        Jugs newJugs = jugs.copy();
        newJugs.lessVal += val;
        newJugs.moreVal -= val;
        return newJugs;
    }

    private List<Jugs> getOpList(Jugs jugs) {
        List<Jugs> list = new ArrayList<>();
        list.add(emptyLess(jugs));
        list.add(emptyMore(jugs));
        list.add(fullLess(jugs));
        list.add(fullMore(jugs));
        list.add(lessToMore(jugs));
        list.add(moreToLess(jugs));
        return list;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x+y < z) {
            return false;
        }
        if (x == z || y == z || z == 0 || x+y == z) {
            return true;
        }
        // BFS
        Jugs head = new Jugs(x,y,z);
        HashSet<String> hashSet = new HashSet<>();
        Queue<Jugs> queue = new LinkedList<>();
        queue.offer(head);
        hashSet.add(head.toString());
        while (!queue.isEmpty()) {
            Jugs jugs = queue.poll();
            List<Jugs> opList = getOpList(jugs);
            for (Jugs ans: opList) {
                if (ans == null) continue;
                if (ans.end()) return true;
                if (!hashSet.contains(ans.toString())) {
                    queue.offer(ans);
                    hashSet.add(ans.toString());
                }
            }
        }
        return false;
    }


    public static void main(String ...args) {
        int x = 2, y = 6;
        LC_365 lc_365 = new LC_365();
        System.out.println(lc_365.canMeasureWater(x, y, 5));
    }
}
