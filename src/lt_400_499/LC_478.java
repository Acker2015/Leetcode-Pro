package lt_400_499;

import java.util.Random;

/**
 * random
 * [478] Generate Random Point in a Circle
 *
 * 使用半径*cos(角度）和 半径*sin(角度)来实现
 */
public class LC_478 {
    private double radius, xc, yc;
    Random random;
    public LC_478(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xc = x_center;
        this.yc = y_center;
        random = new Random();
    }

    public double[] randPoint() {
        // why sqrt? https://meyavuz.wordpress.com/2018/11/15/generate-uniform-random-points-within-a-circle/
        double len= Math.sqrt(Math.random())*radius;
        double deg= Math.random()*2*Math.PI;
        double x= xc+len*Math.cos(deg);
        double y= yc+len*Math.sin(deg);
        return new double[]{x,y};
    }

    public static void main(String ...args) {
        LC_478 lc_478 = new LC_478(1,0,0);
        for (int i = 0; i < 10; ++i) {
            double[] ret = lc_478.randPoint();
            System.out.println(ret[0] + " : " + ret[1]);
        }
    }
}
