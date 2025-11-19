import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 1) {
            System.out.print(0);
            return;
        } else if(N == 2){
            System.out.print(1);
            return;
        }

        boolean[] arr = new boolean[N + 1];

        for(int i = 2; i <= Math.sqrt(N); i++) {
            if (arr[i]) continue;
            int n = 2;
            while (i * n <= N) {
                arr[i * n] = true;
                n++;
            }
        }

        int a = 2;
        int b = 3;
        int sum = a + b;
        int result = 0;

        while(a <= b && b <= N){
            if(sum == N) {
                result++;
                do {
                    b++;
                } while (b <= N && arr[b]);
                sum += b;
            }
            else if(sum > N){
                sum -= a;
                do {
                    a++;
                } while(a <= N && arr[a]);
            } else {
                do {
                    b++;
                } while(b <= N && arr[b]);
                sum += b;
            }
        }

        System.out.print(result);
    }
}
