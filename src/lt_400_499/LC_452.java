package lt_400_499;


import java.util.Arrays;
import java.util.Comparator;

/**
 * [452] Minimum Number of Arrows to Burst Balloons
 * Greedy
 */
public class LC_452 {
    /**
     No offense but the currently highest voted java solution is not ideal, the sorting can be adjusted so that there's no need to check again in the for loop.

     Idea:
     We know that eventually we have to shoot down every balloon, so for each balloon there must be an arrow whose position is between balloon[0] and balloon[1] inclusively.
     Given that, we can sort the array of balloons by their ending position. Then we make sure that while we take care of each balloon in order, we can shoot as many following balloons as possible.

     So what position should we pick each time? We should shoot as to the right as possible,
     because since balloons are sorted, this gives you the best chance to take down more balloons.
     Therefore the position should always be balloon[i][1] for the ith balloon.

     This is exactly what I do in the for loop: check how many balloons I can shoot down with one shot aiming at the ending position of the current balloon.
     Then I skip all these balloons and start again from the next one (or the leftmost remaining one) that needs another arrow.

     Example:

     balloons = [[7,10], [1,5], [3,6], [2,4], [1,4]]
     After sorting, it becomes:

     balloons = [[2,4], [1,4], [1,5], [3,6], [7,10]]
     So first of all, we shoot at position 4, we go through the array and see that all first 4 balloons can be taken care of by this single shot. Then we need another shot for one last balloon. So the result should be 2.
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length <= 0) return 0;
        Arrays.sort(points, Comparator.comparing(cor -> cor[1]));
        int cnt = 1;
        int lastEnd = points[0][1];
        for (int i = 0; i < points.length; ++i) {
            if (points[i][0] > lastEnd) {
                cnt++;
                lastEnd = points[i][1];
            }
        }
        return cnt;
    }
    public static void main(String ...args) {
        int[][] points = {{1,6}, {2,8}, {7,12}, {10, 16}};
        LC_452 lc_452 = new LC_452();
        System.out.println(lc_452.findMinArrowShots(points));
    }
}
