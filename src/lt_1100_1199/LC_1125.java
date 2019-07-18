package lt_1100_1199;

import java.util.*;

/**
 * 1125. Smallest Sufficient Team
 * NP问题
 */
public class LC_1125 {
    /**
     * 路径压缩 - 拥有课程技能使用二进制表示
     * 对于每个人有选择与不选两个选择
     *
     * 在迭代过程中，不能在原map上进行修改，否则会报错
     * @param req_skills
     * @param people
     * @return
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        Map<String, Integer> skillMap = new HashMap<>(16);
        for (int i = 0; i < req_skills.length; ++i) {
            skillMap.put(req_skills[i], i);
        }
        for (int i = 0; i < people.size(); ++i) {
            int skill = 0;
            for (String str: people.get(i)) {
                skill = skill | (1 << skillMap.get(str));
            }
            Map<Integer, List<Integer>> tempMap = new HashMap<>();
            for (Map.Entry<Integer, List<Integer>> entry: resultMap.entrySet()) {
                tempMap.put(entry.getKey(), entry.getValue());
            }
            // skill 逐个与前边的值匹配
            for (Map.Entry<Integer, List<Integer>> entry: tempMap.entrySet()) {
                int newSkill = entry.getKey() | skill;
                if (!resultMap.containsKey(newSkill) || resultMap.get(newSkill).size() > entry.getValue().size()+1) {
                    List<Integer> ansList = new ArrayList<>();
                    ansList.addAll(entry.getValue());
                    ansList.add(i);
                    resultMap.put(newSkill, ansList);
                }
            }
            List<Integer> curList = Arrays.asList(i);
            resultMap.put(skill, curList);
        }
        List<Integer> result = resultMap.get((1<<req_skills.length)-1);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
