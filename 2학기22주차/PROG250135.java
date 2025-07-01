class Solution {
    public int alramCount(int sec){
        int minAlrams = sec * 59 / 3600;
        int hourAlrams = sec * 719 / 43200;

        return minAlrams + hourAlrams - (43200 <= sec ? 2 : 1);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        int startTime = h1 * 60 * 60 + m1 * 60 + s1;
        int endTime = h2 * 60 * 60 + m2 * 60 + s2;

        answer = alramCount(endTime) - alramCount(startTime);
        answer += (startTime * 59 / 3600 == 0 || startTime * 719 % 43200 == 0) ? 1 : 0;

        return answer;
    }
}
