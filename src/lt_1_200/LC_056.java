package lt_1_200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import domain.Interval;

public class LC_056 {
	public List<Interval> merge(List<Interval> intervals) {
        List<Interval> retList = new ArrayList<>();
        if (intervals==null || intervals.size() <= 0) return retList;
        Collections.sort(intervals, Comparator.comparingInt(a->a.start));
        retList.add(intervals.get(0));
        for (Interval interval: intervals) {
            Interval lastInterval = retList.get(retList.size()-1);
            if (interval.start > lastInterval.end) {
                retList.add(interval);
            } else {
                lastInterval.end = Math.max(lastInterval.end, interval.end);
            }
        }
        return retList;
    }
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(8, 10));
        list.add(new Interval(15, 18));

        LC_056 solution = new LC_056();
        System.out.println(solution.merge(list));

	}

}
