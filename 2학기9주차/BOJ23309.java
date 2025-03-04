import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<Integer, Node> stations = new HashMap<>();

    public static class Node {
        public Node front, back;
        public int value;

        public Node(int value) {
            this.value = value;
            front = this;
            back = this;
        }

        public void Add(int value) {
            Node newNode = new Node(value);
            this.front = newNode;
            newNode.back = this;
            stations.put(value, newNode);
        }

        public int Delete() {
            front.back = back;
            back.front = front;
            stations.remove(value);
            return value;
        }

        public int AddFront(int value){
            Node newNode = new Node(value);
            newNode.front = front;
            newNode.back = this;
            front.back = newNode;
            this.front = newNode;
            stations.put(value, newNode);
            return newNode.front.value;
        }

        public int AddBack(int value){
            Node newNode = new Node(value);
            newNode.front = this;
            newNode.back = back;
            back.front = newNode;
            this.back = newNode;
            stations.put(value, newNode);
            return newNode.back.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Node tail = new Node(Integer.parseInt(st.nextToken()));
        stations.put(tail.value, tail);
        Node curNode = tail;
        for(int i = 1; i < N; i++){
            int value = Integer.parseInt(st.nextToken());
            curNode.Add(value);
            curNode = curNode.front;
        }
        curNode.front = tail;
        tail.back = curNode;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int target = Integer.parseInt(st.nextToken());
            Node targetNode = stations.get(target);
            switch(command){
                case "BN":
                    sb.append(targetNode.AddFront(Integer.parseInt(st.nextToken()))).append("\n");
                    break;
                case "BP":
                    sb.append(targetNode.AddBack(Integer.parseInt(st.nextToken()))).append("\n");
                    break;
                case "CP":
                    sb.append(targetNode.back.Delete()).append("\n");
                    break;
                case "CN":
                    sb.append(targetNode.front.Delete()).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}
