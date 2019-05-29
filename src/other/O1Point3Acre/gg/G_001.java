package other.O1Point3Acre.gg;

/**
 * 给一句英文句子和给出屏幕的宽度，要求每个单词左对齐打印出来，求每一行最大的单词数目
 *
 * Yimusanfendi is a great website that has a lot of good discussion, len=40
 * ->
 * Yimusanfendi is         a great website
 * that         has        a lot   of
 * good         discussion
 *
 * 二分查找一行中最大的单词数目，检查，输出
 * 直接记录每一列的最大宽度就可
 */
public class G_001 {
     private boolean check(String[] words, int maxColumn, int maxLen) {
         if (maxColumn <= 0) return true;
         int[] columnRecords = new int[maxColumn];  // max len of each column.
         int sum = maxColumn-1; // record the lenth of space between two words.
         int i = 0;
         while (i < words.length) {
             int loc = i % maxColumn;
             int wordLen = words[i].length();
             if (wordLen > columnRecords[loc]) {
                 sum += wordLen-columnRecords[loc]; // update length
                 if (sum > maxLen) {
                     return false;
                 }
                 columnRecords[loc] = wordLen;
             }
             i++;
         }
         return true;
     }
     public int maxPrintColumns(String[] words, int maxLen) {
         int left = 0, right = maxLen, mid;
         int maxColumn = 0;
         while (left <= right) {
             mid = left + (right-left)/2;
             if (check(words, mid, maxLen)) {
                 maxColumn = mid;
                 left = mid + 1;
             } else {
                 right = mid - 1;
             }
         }
         return maxColumn;
     }

     public static void main(String ...args) {
        String words = "Yimusanfendi is a great website that has a lot of good discussion";
        System.out.println(new G_001().maxPrintColumns(words.split(" "), 40));
     }
}
