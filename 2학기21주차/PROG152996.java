import java.util.*;

class Solution {
    public long solution(int[] weights) {
        HashMap<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);
        long result = 0;

        for (int weight : weights) {

            result += map.getOrDefault(weight * 1.0, 0);
            result += map.getOrDefault(weight * 2.0 / 3, 0);
            result += map.getOrDefault(weight / 2.0, 0);
            result += map.getOrDefault(weight * 3.0 / 4, 0);

            map.put(weight * 1.0, map.getOrDefault(weight * 1.0 , 0) + 1);
        }

        return result;
    }
}