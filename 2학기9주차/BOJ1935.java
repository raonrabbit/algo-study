import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static double[] o;
    static String s;
    static Stack<Double> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        s = br.readLine();

        o = new double[N];
        for(int i = 0; i < N; i++){
            o[i] = Integer.parseInt(br.readLine());
        }

        for(char c : s.toCharArray()){
            int n = c-'A';
            if(n >= 0 && n < 26) {
                stack.add(o[n]);
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch(c){
                    case '+':
                        stack.add(a+b);
                        break;
                    case '-':
                        stack.add(a-b);
                        break;
                    case '*':
                        stack.add(a*b);
                        break;
                    case '/':
                        stack.add(a/b);
                        break;
                }
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
