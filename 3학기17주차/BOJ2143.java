import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> A_SUM, B_SUM;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        A_SUM = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        A[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken()) + A[i - 1];
        }

        for(int i = 0; i < n; i++){
            if(A_SUM.containsKey(A[i])) A_SUM.put(A[i], A_SUM.get(A[i]) + 1);
            else A_SUM.put(A[i], 1);

            for(int j = 0; j < i; j++){
                int num = A[i] - A[j];
                if(A_SUM.containsKey(num)) A_SUM.put(num, A_SUM.get(num) + 1);
                else A_SUM.put(num, 1);
            }
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        B_SUM = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        B[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < m; i++){
            B[i] = Integer.parseInt(st.nextToken()) + B[i - 1];
        }

        for(int i = 0; i < m; i++){
            if(B_SUM.containsKey(B[i])) B_SUM.put(B[i], B_SUM.get(B[i]) + 1);
            else B_SUM.put(B[i], 1);

            for(int j = 0; j < i; j++){
                int num = B[i] - B[j];
                if(B_SUM.containsKey(num)) B_SUM.put(num, B_SUM.get(num) + 1);
                else B_SUM.put(num, 1);
            }
        }

        long result = 0;
        for(int key : A_SUM.keySet()){
            long a_count = A_SUM.get(key);
            int target = T - key;

            if(B_SUM.containsKey(target)){
                result += a_count * B_SUM.get(target);
            }
        }

        System.out.print(result);
    }
}
