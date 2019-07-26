package lt_600_699;

import java.util.*;

/**
 * [690] Employee Importance
 * 简单BFS
 */
public class LC_690 {
    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() <= 0) return 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e: employees) {
            map.put(e.id, e);
        }
        if (!map.containsKey(id)) return 0;
        Queue<Employee> queue = new LinkedList<>();
        int importance = 0;
        queue.offer(map.get(id));
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            importance += e.importance;
            for (int sub_id: e.subordinates) {
                if (map.containsKey(sub_id)) {
                    queue.offer(map.get(sub_id));
                }
            }
        }
        return importance;
    }
}
