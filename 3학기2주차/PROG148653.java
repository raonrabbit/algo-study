class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int current_value = storey % 10;
            int next_value = (storey / 10) % 10;

            if (current_value > 5 || (current_value == 5 && next_value >= 5)) {
                answer += 10 - current_value;
                storey += 10 - current_value;
            } else {
                answer += current_value;
            }

            storey /= 10;
        }

        return answer;
    }
}
