import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int found(int n, int[] arr){

        for(int i = n - 1; i >= 1; i--) {
            int target = arr[i];

            for(int a = i - 1; a >= 0; a--){
                for(int b = a; b >= 0; b--){
                    int temp = arr[a] + arr[b];

                    int start = 0;
                    int end = b + 1;
                    int mid = end / 2;

                    while(start < end){
                        if(arr[mid] + temp > target){
                            end = mid;
                            mid = (start + end) / 2;
                            continue;
                        }

                        if(arr[mid] + temp < target){
                            start = mid + 1;
                            mid = (start + end) / 2;
                            continue;
                        }

                        // arr[mid] + temp == target
                        return target;
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        System.out.print(found(n, arr));
    }
}