package lt_600_699;

import java.util.*;

public class LC_672 {
    /**
     * solution1
     * Math
     * 具体看题解，无聊的一笔
     * @param n
     * @param m
     * @return
     */
    public int flipLights1(int n, int m) {
        if(m==0) return 1;
        if(n==1) return 2;
        if(n==2&&m==1) return 3;
        if(n==2) return 4;
        if(m==1) return 4;
        if(m==2) return 7;
        if(m>=3) return 8;
        return 8;
    }

    private int flipAll(int num, int n) {
        int ans = (1<<n)-1;
        return num^ans;
    }
    private int flipEven(int num, int n) {
        int temp = 0;
        for (int i = 0; i < n; i+=2) {
            temp |= (1<<i);
        }
        return num ^ temp;
    }
    private int flipOdd(int num, int n) {
        int temp = 0;
        for (int i = 1; i < n; i+=2) {
            temp |= (1<<i);
        }
        return num ^ temp;
    }
    private int flip3k(int num, int n) {
        int temp = 0;
        for (int i = 0; i < n; i+=3) {
            temp |= (1<<i);
        }
        return num^temp;
    }
    private List<Integer> flipOperations(int num, int n) {
        List<Integer> list = new ArrayList<>();
        list.add(flipAll(num, n));
        list.add(flipEven(num, n));
        list.add(flipOdd(num, n));
        list.add(flip3k(num, n));
        return list;
    }

    /**
     * BFS解法
     * @param n
     * @param m
     * @return
     */
    public int flipLights(int n, int m) {
        n = (n/6>0?6:0) + n%6;
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(flipAll(0, n));
        for (int i = 1; i <= m; ++i) {
            int size = queue.size();
            for (int j = 0; j < size; ++j) {
                int state = queue.poll();
                List<Integer> list = flipOperations(state, n);
                for (Integer s: list) {
                    if (!set.contains(s)) {
                        queue.offer(s);
                        set.add(s);
                    }
                }
            }
        }
        return set.size();
    }

    public static void main(String ...args) {
        int n = 3, m = 2;
        System.out.println(new LC_672().flipLights(n,m));
    }
}
