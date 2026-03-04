package Strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortCounter implements FrequencyCounter {

    @Override
    public Map<Integer, Integer> count(int[] array) {
        if (array == null || array.length == 0) return new HashMap<>();
        int[] sorted = new int[array.length];
        System.arraycopy(array, 0, sorted, 0, array.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int current = sorted[0];
        int c = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == current) {
                c++;
            } else {
                map.put(current, c);
                current = sorted[i];
                c = 1;
            }
        }
        map.put(current, c);
        return map;
    }
}
