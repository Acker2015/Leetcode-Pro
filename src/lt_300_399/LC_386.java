package lt_300_399;


import java.util.ArrayList;
import java.util.List;

/**
 * [386] Lexicographical Numbers
 */
public class LC_386 {
    private void dfs(int n, List<Integer> list, int cur) {
        if (cur > n) {
            return;
        }
        list.add(cur);
        for (int i = 0; i <= 9; ++i) {
            if (cur*10 + i > n) {
                break;
            }
            dfs(n, list, cur*10+i);
        }
    }

    /**
     * solution1: dfs
     * The idea is pretty simple. If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and make it a tree.
     Then we visit every node in pre-order.
     1            2           3    ...
     /\           /\         /\
     10 ...19    20...29    30...39   ....
     使用dfs分别将1，2，3...9开头的从小到大深搜
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n+1);
        for (int i = 1; i < 10; ++i) {
            dfs(n, list, i);
        }
        return list;
    }

    /**
     * solution2 - math
     *
     * 对于这种lexical sort, 如何找到下一个数呢？
     * 在不考虑越界的情况下，对于个任意的数x,下一个数肯定是(x*10),比如45的下一个数就是450
     * 但是在考虑到越界情况的时候，就需要考虑x*10越界之后该如何处理了？
     * 可能一下会想到x*10越界时候下一个数就是x+1, 比如450越界之后，45的下一个数就是46
     * 但是如果对于末尾是连续的9，导致+1连续进位之后得到的值不再是下一个字典序的值了，比如499 -> (499/10)+1 = 50，但是下一个数应该是5
     *
     * 对于45 下一个数为450， 如果450>n, 那么下一个数就是(46)
     * 对于499，下一个数是4990, 如果4990>0, 那么下一个数就是5，最后的9要全部舍弃掉
     * 想想为什么？
     *  499 -> (499/10)+1 = 50
     *  499 -> 4 + 1 = 5
     * 明显5要小于50，9会产生进位干扰
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder1(int n) {
        List<Integer> list = new ArrayList<>();
        int i = 1, prev = 1;
        list.add(prev);
        while (i++ < n) {
            if (prev * 10 <= n) {
                prev = prev * 10;
            } else {
                while (prev%10 == 9 || prev == n) {
                    prev /= 10;
                }
                prev += 1;
            }
            list.add(prev);
        }
        return list;
    }
}
