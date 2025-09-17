import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        StringTokenizer st;
        SortedMap<Integer, Integer> doublePriorityQueue = new TreeMap<>();

        int N = operations.length;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(operations[i]);

            String command = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            switch(command){
                case "I":
                    if(doublePriorityQueue.containsKey(num)){
                        doublePriorityQueue.put(num, doublePriorityQueue.get(num) + 1);
                    } else {
                        doublePriorityQueue.put(num, 1);
                    }
                    break;
                case "D":
                    if(doublePriorityQueue.isEmpty()){
                        break;
                    } else {
                        if(num == 1){
                            int max = doublePriorityQueue.lastKey();
                            if(doublePriorityQueue.get(max) == 1){
                                doublePriorityQueue.remove(max);
                            } else {
                                doublePriorityQueue.put(max, doublePriorityQueue.get(max) - 1);
                            }
                        } else {
                            int min = doublePriorityQueue.firstKey();
                            if(doublePriorityQueue.get(min) == 1){
                                doublePriorityQueue.remove(min);
                            } else {
                                doublePriorityQueue.put(min, doublePriorityQueue.get(min) - 1);
                            }
                        }
                    }
                    break;
            }
        }

        if(!doublePriorityQueue.isEmpty()) {
            answer[0] = doublePriorityQueue.lastKey();
            answer[1] = doublePriorityQueue.firstKey();
        }
        return answer;
    }
}