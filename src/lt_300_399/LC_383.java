package lt_300_399;

/**
 * [383] Ransom Note
 */
public class LC_383 {
    /**
     * hashmap
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] nodeArr = ransomNote.toCharArray();
        char[] magArr = magazine.toCharArray();
        int[] memory = new int[128];
        for (int i = 0; i < magArr.length; ++i) {
            memory[magArr[i]]++;
        }
        for (int i = 0; i < nodeArr.length; ++i) {
            if (--memory[nodeArr[i]] < 0) {
                return false;
            }
        }
        return true;
    }
}
