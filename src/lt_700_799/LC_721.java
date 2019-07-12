package lt_700_799;

import java.util.*;

/**
 *
 * [721] Accounts Merge
 *
 * 并查集
 * union-find
 *
 * 一开始思路陷入混乱，没有正确表达account之间的关系
 * 这里又两种解法
 * 1. 就是这些account可以组成多个森林，森林中的每个树就是一个独立的merge之后的account
 * 2. 并查集操作，将有关联的email放到一个集合
 *      如 john A B C
 *      存放关系就是 A->A, B->A, C->A
 *    这样最后会产生多个集合关系，也就相当于森林里的多棵树
 */
public class LC_721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, String> emailNameMap = new HashMap<>();

        for (List<String> account: accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); ++i) {
                parentMap.put(account.get(i), account.get(i));
                emailNameMap.put(account.get(i), name);
            }
        }
        for (List<String> account: accounts) {
            String p1 = find(parentMap, account.get(1));
            for (int i = 2; i < account.size(); ++i) {
                String p2 = find(parentMap, account.get(i));
                parentMap.put(p2, p1);
            }
        }
        Map<String, TreeSet<String>> union = new HashMap<>();
        for (List<String> account: accounts) {
            for (int i = 1; i < account.size(); ++i) {
                String p = find(parentMap,account.get(i));
                if (!union.containsKey(p)) {
                    union.put(p, new TreeSet<>());
                }
                union.get(p).add(account.get(i));
            }
        }
        List<List<String>> retList = new ArrayList<>();
        for (Map.Entry<String, TreeSet<String>> entry: union.entrySet()) {
            List<String> temp = new ArrayList<>();
            temp.add(emailNameMap.get(entry.getKey()));
            temp.addAll(entry.getValue());
            retList.add(temp);
        }
        return retList;
    }
    private String find(Map<String, String> parentMap, String email) {
        if (email.equals(parentMap.get(email))) {
            return email;
        }
        String parent = find(parentMap, parentMap.get(email));
        parentMap.put(email, parent);
        return parent;
    }

    public static void main(String ...args) {
        //[["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David","David0@m.co","David4@m.co","David3@m.co"));
        accounts.add(Arrays.asList("David","David5@m.co","David5@m.co","David0@m.co"));
        accounts.add(Arrays.asList("David","David1@m.co","David4@m.co","David0@m.co"));
        accounts.add(Arrays.asList("David","David0@m.co","David1@m.co","David3@m.co"));
        accounts.add(Arrays.asList("David","David4@m.co","David1@m.co","David3@m.co"));
        List<List<String>> ret = new LC_721().accountsMerge(accounts);
        System.out.println(1);
    }
}
