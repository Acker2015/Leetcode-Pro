package lt_200_299;

/**
 * [238] Product of Array Except Self
 *
 * DP
 */
public class LC_238 {
    /**
     * 不允许除了输出结果的数据的额外空间，那么只能在原数组上做文章
     * 第一轮使用rets来记录product的前缀，rets[i]表示i索引之前product=nums[0]*nums[1]*...*nums[i-1]
     * 第二轮在nums数据上做文章来记录product的后缀，nums[i]表示原数组之后的product=nums[i+1]*...*nums[len-1]
     *
     * 第三轮每个位置的结果就是 rets[i] = rets[i] * nums[i]; 前缀product * 后缀product
     * 这里第二轮第三轮可以合并在一起完成
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] rets = new int[len];
        rets[0] = 1;
        for (int i = 1; i < len; ++i) {
            rets[i] = rets[i-1]*nums[i-1];
        }
        int postProduct = 1;
        for (int i = len-1; i>=0; --i) {
            rets[i] *= postProduct;
            postProduct *= nums[i];
        }
        return rets;
    }
}
