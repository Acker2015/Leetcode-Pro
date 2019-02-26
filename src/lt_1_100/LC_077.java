package lt_1_100;

import java.util.ArrayList;
import java.util.List;

public class LC_077 {
	/**
	 * solution1: backtracking
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < k || k <= 0) return ret;
        backTracking(n, 1, k, new ArrayList<>(), ret);
        return ret;
    }
	private void backTracking(int n, int now, int k, List<Integer> midList, List<List<Integer>> ret) {
		if (midList.size() == k) {
			List<Integer> tmpL = new ArrayList<>();
			tmpL.addAll(midList);
			ret.add(tmpL);
			return;
		}
		if (now > n) return;
		backTracking(n, now+1, k, midList, ret);
		midList.add(now);
		backTracking(n, now+1, k, midList, ret);
		midList.remove(midList.size()-1);
	}
	/**
	 * C(n,k) = C(n-1, k-1) + C(n-1, k) 在n个数中取k个数的组合项个数，等于在n-1个数中取k-1个数的组合项个数再加上在n-1个数中取k个数的组合项个数之和。
	 * 
	 * ex:
	 * C(4, 2) = C(3, 1) + C(3, 2)
	 * C(3, 1) 的所有情况：[1], [2], [3]
	 * C(3, 2) 的所有情况：[1, 2], [1, 3], [2, 3]
	 * C(3, 2)的所有情况包含在 C(4, 2) 之中，但是 C(3, 1) 的每种情况只有一个数字，而我们需要的结果k=2，其实很好办，每种情况后面都加上4，于是变成了：[1, 4], [2, 4], [3, 4]，
	 * 加上C(3, 2) 的所有情况：[1, 2], [1, 3], [2, 3]，正好就得到了 n=4, k=2 的所有情况了。
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine2(int n, int k) {
		List<List<Integer>> ret = new ArrayList<>();
        if (n < k || k < 0) return ret;
        if (k == 0) {
        		ret.add(new ArrayList<>());
        		return ret;
        }
        List<List<Integer>> subRet = combine2(n-1, k-1);
        subRet.stream().forEach(l-> {
        		l.add(n);
        		ret.add(l);
        });
        ret.addAll(combine2(n-1, k));
        return ret;
	}
	
	public static void main(String[] args) {
		LC_077 lc_077 = new LC_077();
		List<List<Integer>> ret = lc_077.combine2(4, 2);
		for (int i = 0; i < ret.size(); ++i) {
			for (Integer item: ret.get(i)) {
				System.out.print(item+" ");
			}
			System.out.println("");
		}

	}

}
