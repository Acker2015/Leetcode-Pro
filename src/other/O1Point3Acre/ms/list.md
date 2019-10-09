1. Find pair in an array having minimum absolute sum (two pointers)
2. 给一个字符串，有大写和小写字母，要把所有大写字母移动到小写字母后面并保持顺序不变。
3. 最少插入的字符个数，使原字符串变成回文串-DP
4. 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
5. 给定一个有序的正整数数组，在首尾之间，不连续的部分可以看成是漏掉了一些数。这些漏掉的数可以组成一个虚拟的数组，要求给出一个序号K，
    返回虚拟数组的第K个数
6. 给定一个almost有序的数组，只能做一下操作一次：
    a. reverse一段子数组
    b. swap一次
   问给定的数组是否能够变成有序？ （https://www.hackerrank.com/challenges/almost-sorted/problem）
7. 给定一个int型数组array和一个int型数 target，从数组中删除最少个数的元素，使得数组中剩下的数字无法通过位或得到target。
8. 在一个数组里找任意两个数之和的绝对值最小值(https://www.techiedelight.com/find-pair-array-minimum-absolute-sum/)
9. 给两个正int数，a和b，然后求a%b,不能用乘法和除法。
10. 调度bug，最小化乘客的最大等车时间（***）
11. n个人，求有多少个两两组合之间的距离小于k的数量。要求O(n^2)


12. 递增的数列，判断这个递增数列是不是合法的，不合法返回不合法的数，合法返回-1。要求logn。
13. 给定字符串数组，将大写字母移到所有小写字母后面，并且不改变大/小字母的相对位置。
14.字，你不能依靠client输入valid的string，如果是三百二十千八百二十八这个就不行，有可能是负数。
数量是1兆到-1兆。我最开始以为不算很难，但是用stack思想做了后漏洞百出，怎么判定是invalid？
十一这个testcase你怎么办？
十是算作单位还是算作一个数？
一到九和其他单位是不是要分开考虑？
五万零三百二十这个test case怎么办？

还有一点我比较无语，他说int overflow怎么办？我用的是python，我记得是不用管的，不会有overflow的问题，但是强行让我管...我只能瞎说了。反正后面头都要炸了，说得也比较乱，强行扯了一波，其实面完就知道我要跪了。

把一串string转化成int。具体是三百二十八万三千二百八十转化成数

15. 判断圆和矩形是否相交(https://blog.csdn.net/noahzuo/article/details/52037151)