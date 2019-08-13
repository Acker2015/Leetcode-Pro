package lt_1100_1199;

import java.util.ArrayList;
import java.util.List;

/**
 * [1146] Snapshot Array
 *
 * design
 * 使用两个变量 snapId, array
 * array的每个索引处的结构都是一个List<int[]>存放val-snapId的数对列表
 * 1. set的时候只需要找到对应的snapId处执行
 * 2. get的时候可以通过snap_id来二分找到第一个大于等于snap_id的位置，然后根据此位置找到正确位置进行处理
 */
public class LC_1146 {
    class SnapshotArray {
        private Integer snapId;
        List<int[]>[] array;
        public SnapshotArray(int length) {
            this.snapId = 0;
            array = new ArrayList[length];
            for (int i = 0; i < length; ++i) {
                this.array[i] = new ArrayList<>();
                this.array[i].add(new int[]{0, snapId});
            }
        }
        public void set(int index, int val) {
            int size = array[index].size();
            if (array[index].get(size-1)[1] != snapId) {
                array[index].add(new int[]{val, snapId});
            } else {
                array[index].get(size-1)[0] = val;
            }
        }
        public int snap() {
            return snapId++;
        }
        public int get(int index, int snap_id) {
            if (this.array[index] == null || this.array[index].size() <= 0) return 0;
            List<int[]> list = this.array[index];
            // binary-search
            int left = 0, right = list.size();
            while (left < right) {
                int mid = left + (right-left)/2;
                if (list.get(mid)[1] < snap_id) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left==list.size() || list.get(left)[1] > snap_id) {
                left--;
            }
            return list.get(left)[0];
        }
    }

    // ["SnapshotArray","snap","get","get","set","get","set","get","set"]
    // [[2],[],[1,0],[0,0],[1,8],[1,0],[0,20],[0,0],[0,7]]
}
