import java.util.*;

class Solution {
    public int[] solution(String s) {
        int zeroCount = 0;
        int steps = 0;

        while(!s.equals("1")){
            int sSize = s.length();
            steps++;
            for(char c : s.toCharArray()){
                if(c == '0') {
                    sSize--;
                    zeroCount++;
                }
            }
            s = Integer.toBinaryString(sSize);
        }
        return new int[] {steps, zeroCount};
    }
}