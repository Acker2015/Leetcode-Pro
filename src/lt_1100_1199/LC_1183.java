package lt_1100_1199;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Maximum number of ones
 * Consider a matrix M with dimensions width * height, such that every cell has 0 and 1,
 * and any square sub-matrix M of size sideLength*sideLength has at most maxOnes ones.
 *
 * Return the maximum possible number of ones that the matrix M can have
 *
 * 数学问题-贪心-尽量减少共用的1
 * 1. 1尽量放sideLength的最左边
 * 2. 1尽量放sideLength的最上边
 * 所以问题就是尽量往左或者往上放置
 *
 * sideLength=2, maxOnes=1
 * [1,0,1]
 * [1,0,1]
 * [1,0,1]
 * 对于中间一列会被两边的sideLength=2的正方形共用，所以尽量往左上放置
 *
 * 如果是4*4里放置3*3，且maxOnes=5，那么根据上述问题描述
 * [1,1,1,1]
 * [1,0,0,1]
 * [1,0,0,1]
 * [1,1,1,1]
 *
 */
public class LC_1183 {

}
