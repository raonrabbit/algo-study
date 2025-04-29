import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] names;

    public static class Node{
        // 중복 개수
        public int count;

        // 자식
        public HashMap<Character, Node> child;

        public Node(){
            this.count = 0;
            child = new HashMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        names = new String[N];

        for(int i = 0; i < N; i++){
            names[i] = br.readLine();
        }

        Node head = new Node();
        for(String s : names){
            Node cur = head;
            // 처음으로 트리에 존재하지 않는 문자에 다가갔는지 확인할 플래그
            boolean nameGood = false;
            
            // 문자열 캐릭터 하나씩 순회
            for(char c : s.toCharArray()){

                // 현재 문자가 트리에 존재하지 않는다면
                if(!cur.child.containsKey(c)){
                    // 처음으로 존재하지 않는 문자로 온 경우
                    if(!nameGood) {
                        // 별칭이 겹칠리 없으므로 별칭에 현재 문자 추가하고 개행
                        sb.append(c).append('\n');
                        nameGood = true;
                    }

                    // 현재 노드 자식에 새로운 노드 등록
                    Node child = new Node();
                    cur.child.put(c, child);
                    cur = child;
                }

                // 현재 문자가 트리에 이미 존재할 경우
                else {
                    if (!nameGood) sb.append(c);
                    cur = cur.child.get(c);
                }
            }
            cur.count++;

            // 모든 문자가 트리에 존재했을 경우
            if (!nameGood) {
                if (cur.count >= 2) {
                    sb.append(cur.count);
                }
                sb.append('\n');
            }
        }

        System.out.print(sb);
    }
}
