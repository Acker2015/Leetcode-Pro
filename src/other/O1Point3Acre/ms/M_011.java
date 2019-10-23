package other.O1Point3Acre.ms;

/**
 * n个人，求有多少个两两组合之间的距离小于k的数量。要求O n^2
 */
public class M_011 {
    /**
     * Map<Integer, Set<Integer>>
     * 主要用n^2找出合法的两两距离。
     * 然后再用hashtable记录每个点的对应可行点，再用n^2找出每两个可行点，最后O(1) 查找第三个点是不是在里面。
     *
     */
}
