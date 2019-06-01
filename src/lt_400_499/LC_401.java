package lt_400_499;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯
 * [401] Binary Watch
 */
public class LC_401 {
    private class Record{
        List<Integer> hour;
        List<Integer> minute;
        public Record() {
            hour = new ArrayList<>();
            minute = new ArrayList<>();
        }
        public void clear() {
            hour.clear();
            minute.clear();
        }
    }
    private Record record;
    private void backTracking(int res, int idx, int time, boolean isHour) {
        if (res < 0 || idx-res+1 < 0 || (isHour && time > 11) || (!isHour && time > 59)) return;
        if (res == 0) {
            if (isHour) record.hour.add(time);
            if (!isHour) record.minute.add(time);
            return;
        }
        backTracking(res, idx-1, time, isHour);
        backTracking(res-1, idx-1, time|(1<<idx), isHour);
    }
    public List<String> readBinaryWatch(int num) {
        List<String> retResult = new ArrayList<>();
        record = new Record();
        int i = 0;
        while (i <= num && i <= 3) {
            record.clear();
            backTracking(i, 3, 0, true);
            backTracking(num-i, 5, 0, false);
            for (int h: record.hour) {
                for (int m: record.minute) {
                    retResult.add(h + ":" + String.format("%02d", m));
                }
            }
            i++;
        }
        return retResult;
    }
}
