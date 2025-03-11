import java.io.*;
import java.util.*;

public class Main {
    static int min;

    public static void dfs(int depth, int n){
        if(depth >= min) return;
        if(n == 1){
            min = depth;
            return;
        }
        if(n % 3 == 0) dfs(depth+1, n/3);
        if(n % 2 == 0) dfs(depth+1, n/2);
        dfs(depth+1, n-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        min = n - 1;
        dfs(0, n);
        System.out.print(min);
    }
}
