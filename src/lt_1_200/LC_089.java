package lt_1_200;

import java.util.ArrayList;
import java.util.List;

public class LC_089 {
	/**
	 * [89] Gray Code 格雷码
     * 对n位二进制的码字，从右到左，以0到n-1编号
     * 如果二进制码字的第i位和i+1位相同，则对应的格雷码的第i位为0，否则为1
     * 
     * 例如：二进制码0101，为4位数，所以其所转为之格雷码也必为4位数，因此可取转成之二进位码第五位为0，即0 b3 b2 b1 b0。
		0 xor 0=0，所以g3=0
		0 xor 1=1，所以g2=1
		1 xor 0=1，所以g1=1
		0 xor 1=1，所以g0=1
		因此所转换为之格雷码为0111
		
		(0101 >> 1) = 0010
		0101^0010=0111
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        int ans = (int) Math.pow(2, n);
        for (int i = 0; i < ans; ++i) {
            list.add(i^(i>>1));
        }
        return list;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
