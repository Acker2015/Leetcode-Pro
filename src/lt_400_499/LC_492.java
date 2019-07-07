package lt_400_499;

/**
 * [492] Construct the Rectangle
 * easy
 */
public class LC_492 {
    public int[] constructRectangle(int area) {
        int start = (int)Math.sqrt(area);
        while (area%start!=0) {
            start--;
        }

        return new int[]{area/start, start};
    }
}
