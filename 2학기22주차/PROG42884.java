import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> {return o1[1] - o2[1];});
        int count = 0;
        int position = Integer.MIN_VALUE;
        for(int[] route : routes){
            if(route[0] > position){
                count++;
                position = route[1];
            }
        }
        return count;
    }
}