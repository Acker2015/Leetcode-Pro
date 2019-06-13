package basic_data_structure.stack;

import java.util.Stack;

/**
 * 汉诺塔问题
 * 从 1 -> 2 -> 3 -> 4 -> ...寻找规律
 * 发现都是将n-1个先移动到中间的柱子，
 * 再将最后一个盘子移动到第三个柱子
 * 最后将第二个柱子的盘子移动到第三个柱子
 *
 * 都是相同的解决思路，每个解决一个盘子的放置问题
 *
 */
public class Tower {
    private Stack<Integer> disks;
    private int index;
    public Tower(int i) {
        disks = new Stack<>();
        index = i;
    }
    public int index() {
        return this.index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
        System.out.println("Move disk " + top + " from " + index() + " to " + t.index);
    }

    public void moveDisks(int n, Tower dest, Tower buffer) {
        if (n > 0) {
            moveDisks(n-1, buffer, dest); // 将前n-1个移动到buffer上
            moveTopTo(dest); // 将底部最大的移动到目的地
            moveDisks(n-1, dest, this); // 将buffer上的n-1个汉诺塔移动到desc，这时候使用this作为新的buffer
        }
    }

    /**
     * 伪代码
     * moveDisks(int n, Tower origin, Tower dest, Tower buffer) {
     *     // 终止条件
     *     if (n <= 0) return;
     *     // 将顶部n-1个盘子从origin移动到buffer，这时候dest作为新的缓冲区buffer使用
     *     moveDisks(n-1, origin, buffer, dest);
     *     // 将origin顶部的盘子(即最后一个)移动到dest
     *     moveTop(origin, dest);
     *     // 将顶部n-1个盘子从buffer移动到dest，这时候origin作为新的缓冲区buffer
     *     moveDisks(n-1, buffer, dest, origin)
     * }
     */



}
