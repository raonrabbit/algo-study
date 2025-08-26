import java.util.*;

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < n; i++){
            if(cityMap[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for(int i = 0; i < m; i++){
            if(cityMap[i][0] == 1) break;
            dp[i][0] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(cityMap[i][j] == 1) continue;
                int value = 0;
                int tempY = i - 1;
                int tempX = j - 1;

                while(cityMap[tempY][j] == 2) {
                    tempY--;
                    if(tempY < 0) break;
                }
                while(cityMap[i][tempX] == 2) {
                    tempX--;
                    if(tempX < 0) break;
                }
                int a = 0;
                int b = 0;

                if(tempY >= 0) a = dp[tempY][j];
                if(tempX >= 0) b = dp[i][tempX];

                dp[i][j] = (a + b) % MOD;
            }
        }

        return dp[m - 1][n - 1];
    }
}