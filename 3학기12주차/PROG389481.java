import java.util.*;

class Solution {
    public static long alphaToNum(String alpha) {
        long num = 0;
        for (char c : alpha.toCharArray()) {
            num = num * 26 + (c - 'a' + 1);
        }
        return num;
    }

    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder();
        long[] banNums = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            banNums[i] = alphaToNum(bans[i]);
        }
        Arrays.sort(banNums);

        for (long banNum : banNums) {
            if (banNum <= n) {
                n++;
            } else {
                break;
            }
        }

        while (n > 0) {
            n--;
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }

        String answer = sb.reverse().toString();
        return answer;
    }
}
