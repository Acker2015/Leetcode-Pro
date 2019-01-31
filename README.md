# Leetcode-Pro
go and record


### Stack
1.  [496-next-greater-element-i](https://leetcode.com/problems/next-greater-element-i/)
2.  [503-next-greater-element-ii](https://leetcode.com/problems/next-greater-element-ii/)


### Array
1. 


### Tree
1. 

### String


## 特别专题

### 积水系列
1. [011-container-with-most-water](https://leetcode.com/problems/container-with-most-water/submissions/)

### 蓄水池抽样算法(Reservoir Sampling Algorithm)
定理：该算法保证每个元素以 k / n 的概率被选入蓄水池数组。
https://blog.csdn.net/u010150046/article/details/77017145#commentBox

```
/*
	replace elements with gradually decreasing probability
	算法首先创建一个长度为K的数组（蓄水池）用来存放结果，初始化为S的前k个元素，然后从k+1个元素开始迭代直到数组结束
	算法生成一个随机数j∈[1, i]，如果 j ≤ k，那么蓄水池的第 j 个元素被替换为S的第i个元素。
*/
for each i in k+1 to length(S) do
    j := random(1, i);   // important: inclusive range
    if j <= k then
        R[j] := S[i]
    fi
done
```

1. [382- Linked List Random Node](https://leetcode.com/problems/linked-list-random-node/)
