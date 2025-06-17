import java.util.*;

class Solution {
    public int leftThumb, rightThumb;

    public int min;
    public String number;
    public int numLength;

    public int[][] cost = new int[][] {
            {1, 7, 6, 7, 5, 4, 5, 3, 2 ,3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5 ,6 ,2 ,3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };

    public int[][][] memo;

    public int DP(int depth, int lT, int rT){
        if(depth == numLength){
            return 0;
        }

        if(memo[depth][lT][rT] != 0) return memo[depth][lT][rT];

        int cur = number.charAt(depth) - '0';
        int result = Integer.MAX_VALUE;

        if(cur != rT) result = Math.min(DP(depth + 1, cur, rT) + cost[lT][cur], result);

        if(cur != lT) result = Math.min(DP(depth + 1, lT, cur) + cost[rT][cur], result);

        memo[depth][lT][rT] = result;
        return result;
    }

    public int solution(String numbers) {
        leftThumb = 4;
        rightThumb = 6;
        number = numbers;
        numLength = numbers.length();
        min = Integer.MAX_VALUE;
        memo = new int[numLength + 1][10][10];

        return DP(0, leftThumb, rightThumb);
    }
}