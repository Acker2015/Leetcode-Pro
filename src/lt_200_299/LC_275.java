package lt_200_299;


public class LC_275 {
    /**
     * 二分
     * 由于按照论文引用个数进行排序，那么可以通过判断paper个数和引用个数二分
     *
     * 1. 如果mid处论文个数大于引用个数，说明需要提高引用数、降低论文个数 -> l = mid+1
     * 2. 否则右指针保持在mid处 -> r = mid
     */
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length;
        while (l < r) {
            int mid = l + (r - l) /2;
            int paper_cnt = citations.length - mid;
            // 论文个数大于引用个数，那么需要提高引用，降低论文个数
            if (citations[mid] < paper_cnt) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return citations.length - l;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,3,5,6};
        LC_275 lc_275 = new LC_275();
        System.out.println(lc_275.hIndex(arr));
    }
}
