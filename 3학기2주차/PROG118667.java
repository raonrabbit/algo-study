import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long queue1_sum = 0;
        long queue2_sum = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for(int i = 0; i < queue1.length; i++){
            queue1_sum += queue1[i];
            queue2_sum += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        if((queue1_sum + queue2_sum) % 2 != 0) return -1;

        for(int i = 0; i <= queue1.length * 3; i++){
            if(queue1_sum == queue2_sum) {
                return i;
            }

            if(queue1_sum > queue2_sum){
                int t = q1.poll();
                queue1_sum -= t;
                queue2_sum += t;
                q2.add(t);
            } else {
                int t = q2.poll();
                queue2_sum -= t;
                queue1_sum += t;
                q1.add(t);
            }


            if(q1.isEmpty() || q2.isEmpty()) return -1;
        }

        return -1;
    }
}