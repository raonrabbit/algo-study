import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] potions;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        potions = new long[N];

        for(int i = 0; i < N; i++){
            potions[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(potions);

        long[] result = new long[3];
        long min = Long.MAX_VALUE;

        for(int i = 0; i < N - 2; i++){
            int left = i + 1;
            int right = N - 1;
            long n = potions[i];
            while(left < right){
                long temp = n + potions[left] + potions[right];
                if(Math.abs(temp) < min){
                    result[0] = n;
                    result[1] = potions[left];
                    result[2] = potions[right];
                    min = Math.abs(temp);
                }
                if(temp < 0) left++;
                else right--;
            }
        }

        Arrays.sort(result);

        for(int i = 0; i < 3; i++){
            System.out.print(result[i] + " ");
        }
    }
}
