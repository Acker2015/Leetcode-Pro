package lt_1_200;

public class LC_060 {
	public int findValue(boolean[] visited, int t) {
        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i]) {
                if (t == 0) {
                    return i+1;
                }
                t--;
            }
        }
        return -1;
    }
    /**
     * 直接看例子， n=4 k = 14
     * 
     * 1 + (permutations of 2,3,4)
     * 2 + (permutations of 1,3,4)
     * 3 + (permutations of 1,2,4)
     * 4 + (permutations of 1,2,3)
     * 其中(permutations of 2,3,4)有6种， 那么k=14,显然第一个数应该选择3， 然后在(permutations of 1,2,4)中选择第二个
     * 这样重复迭代直到选完最后一个数为止
     * 
     * # https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
     */
    public String getPermutation(int n, int k) {
        if (n <= 1) return String.valueOf(n);
        boolean[] used = new boolean[n];
        int[] factorial = new int[n];
        factorial[0]=factorial[1]=1;
        for (int i = 2; i < n; ++i) {
            factorial[i] = factorial[i-1]*i;
        }
        StringBuilder sbd = new StringBuilder();
        int seq = n;
        k--;
        while(seq-- > 0) {
            int quo = k/factorial[seq];
            k = k%factorial[seq];
            int indexVal = findValue(used, quo);
            sbd.append(String.valueOf(indexVal));
            used[indexVal-1]=true;
            if (k == 0) {
                for (int i = 0; i < used.length; ++i) {
                    if (!used[i]) {
                        sbd.append(String.valueOf(i+1));
                    }
                }
                break;
            }
        }
        return sbd.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
