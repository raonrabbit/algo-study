import java.util.*;

class Solution {
    public static int[] nums;
    public static int find(int x){
        if(nums[x] == x) return x;
        else return nums[x] = find(nums[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        nums[y] = x;
    }

    public int solution(int n, int[][] computers) {
        nums = new int[n];

        for(int i = 0; i < n; i++){
            nums[i] = i;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j || computers[i][j] == 0) continue;
                union(i, j);
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(i == nums[i]) count++;
        }

        return count;
    }
}