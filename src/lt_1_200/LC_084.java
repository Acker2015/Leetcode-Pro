package lt_1_200;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 */
public class LC_084 {
    //单调队列 使用栈
    /*
     要想找出面积最大的矩形的左边界和右边界，我们把矩形的左边界从数组的左边向右边移动，每移动一次，求一次最大矩形面积。最后再对所有矩形面积求最大值。这就是下面的算法的思路。
     The point of this algorithm is to maintain a stack where higher element is always greater or equal to the lower element.
     Why do we need to maintain that kind of stack?
     Because if we have a non-decreasing list, we can easily calculate the maximum area in one scan. We just need to compare: height[i] * (n – i) for every i.
     So how do we maintain this stack?
     If we keep seeing larger element, we just need to push them onto the stack.
     If we see a smaller (compared to the top element on the stack) element, we need to do two things:
     1. Pop the stack until we can maintain the non-decreasing order.
     2. Pushing the smaller element for m times, where m = number of poped elements.


     Keep track of the maximum area that cause by those pop.
     For example, we have height = {1,3,5,7,4}.
     We push onto the stack for {1,3,5,7} then we see 4. 4 is less than 7, so we need to pop. We stop popping until we see 3.
     However many times we pop, we push 4 onto the stack. Therefore the resulted stack would be {1,3,4,4,4}.
     Because of popping 7, we need to remember that the maximum area that contains 7 is 7.
     The largest area that contains 5, the other element which get popped, is 10.
     So we take that down. We then finish processing all the elements in the original array and end up with a non-decreasing stack {1,3,4,4,4}.
     We can compute the largest area of this stack, which is 4*3 = 12. Since 12 is larger than the previous largest, 10, we output 12.
     */

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || stack.peek()<=heights[i]) {
                stack.push(heights[i++]);
            } else {
                int num = 1;
                while (!stack.isEmpty() && stack.peek()>heights[i]) {
                    int top = stack.pop();
                    maxArea = Math.max(maxArea, num*top);
                    num++;
                }
                while (num-- > 0) {
                    stack.push(heights[i]);
                }
                i++;
            }
        }
        int num = 0;
        while (!stack.isEmpty()) {
            num++;
            int top = stack.pop();
            maxArea = Math.max(maxArea, num*top);
        }
        return maxArea;
    }
}
