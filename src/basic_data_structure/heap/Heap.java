package basic_data_structure.heap;

/**
 * 对于完全二叉树可以使用数组来进行存储
 * 数组下边从1开始，表示完全二叉树的根节点
 *
 * 对于任意下标索引i的节点，其左孩子索引为2i，右孩子的索引为2i+1
 *
 * 最大堆实例
 */
public class Heap {
    private static final Integer MAXN = 100;
    private int n = 10; // 元素个数
    private int[] heap = new int[MAXN];

    Heap(int n) {
        this.n = n;
    }
    public void setValue(int idx, int val) {
        heap[idx] = val;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * 向下调整
     *
     * target为欲调整的节点下标
     * right为完全二叉树的数据表示边界
     * O(logn)
     *
     * @param target
     * @param right
     */
    public void downAdjust(int target, int right) {
        // i为欲调整的节点，j为i的左孩子
        int i = target, j = 2 * i;
        while (j <= right) {
            if (j + 1 <= right && heap[j] < heap[j+1]) {
                j++;
            }
            if (heap[i] < heap[j]) {
                swap(i, j);
                i = j;
                j = 2 * i;
            } else {
                break;
            }
        }
    }

    /**
     * 向上调整，插入节点的时候使用
     * O(logn)
     *
     * target为要调整的节点
     * left为左边界
     *
     * 目标是一直沿着路径向上调整，直到遇到根节点元素大于调整目标元素
     * @param left
     * @param target
     */
    public void upAdjust(int left, int target) {
        int i = target/2, j = target;
        while (i >= left) {
            if (heap[i] < heap[j]) {
                swap(i, j);
                j = i;
                i = j / 2;
            } else {
                break;
            }
        }
    }

    /**
     * 建堆过程
     * O(n) 从n/2处倒序遍历，不断向下调整，这样能够保证每个节点都是以其根节点的子树中权值最大的
     */
    public void createHeap() {
        for (int k = n / 2; k >= 1; --k) {
            downAdjust(k, n);
        }
    }

    /**
     * insert new node to tail
     *
     * up adjust
     * @param x
     */
    public void insert(int x) {
        heap[++n] = x;
        upAdjust(1, n);
    }

    /**
     * 堆排序
     * O(nlogn)
     *
     * 倒序从n向前遍历，假设当前访问到i位，那么将堆顶元素和i号位的元素交换，
     * 然后在[1， i-1]之间来一次向下调整，维持[1, i-1]仍然为最大堆状态
     */
    public void heapSort() {
        for (int i = n; i >= 1; --i) {
            swap(i, 1);
            downAdjust(1, i - 1);
        }
    }

    public void traverseHeapArr() {
        for (int i = 1; i <= n; ++i) {
            System.out.println("idx: " + i + ", val: " + heap[i]);
        }
    }

    public static void main(String ...args) {
        Heap heap = new Heap(10);
        for (int i = 1; i <= 10; ++i) {
            heap.setValue(i, 10 - i);
        }
        heap.createHeap();
        //heap.traverseHeapArr();
        heap.insert(200);
        //heap.traverseHeapArr();
        heap.heapSort();
        heap.traverseHeapArr();
    }
}
