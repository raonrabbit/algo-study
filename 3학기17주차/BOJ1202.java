import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Jewel {
        public int weight;
        public int price;

        public Jewel(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewel> jewels = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[] bags = new int[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(m, c));
        }

        for(int i = 0; i < K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);
        long result = 0;
        PriorityQueue<Integer> prices = new PriorityQueue<>(Collections.reverseOrder());

        for(int bag: bags){
            while(!jewels.isEmpty() && jewels.peek().weight <= bag){
                prices.add(jewels.poll().price);
            }

            if(!prices.isEmpty()) {
                result += prices.poll();
            }
        }

        System.out.print(result);
    }
}
