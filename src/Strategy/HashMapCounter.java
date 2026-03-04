package Strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapCounter implements FrequencyCounter{

    @Override
    public Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }
}
