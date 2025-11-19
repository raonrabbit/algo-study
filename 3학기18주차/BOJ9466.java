import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];

            st = new StringTokenizer(br.readLine());

            // 갈 수 있는 번호
            Set<Integer> cango = new HashSet<>();

            // 팀을 이룰 수 없는 번호
            Set<Integer> noteam_num = new HashSet<>();

            for(int i = 1; i <= n; i++){
                arr[i] = Integer.parseInt(st.nextToken());

                // 자기 자신을 가리켜 이미 팀이 구성된 번호는 cango 에서 제외
                if(i != arr[i]) cango.add(i);
            }

            for(int i = 1; i <= n; i++){
                if(!cango.contains(i)) continue;

                //가려는 곳이 갈 수 없는 곳이면 패스
                if(!cango.contains(arr[i])) {
                    noteam_num.add(i);
                    cango.remove(i);
                    continue;
                }

                Set<Integer> visited = new HashSet<>();
                Queue<Integer> line = new ArrayDeque<>();
                int cur = i;

                // line 에 가는 길을 순서대로 기록하고,
                // 이미 방문한 곳에 재방문하면(사이클이 생기면) 종료.
                // 자기 자신을 가리켜 더 이상 앞으로 갈 수 없는 곳이어도 종료.
                while(!visited.contains(cur)){
                    visited.add(cur);
                    line.add(cur);
                    cur = arr[cur];
                    if(cur == arr[cur]){
                        line.add(cur);
                        break;
                    }
                }

                // 이 시점에서 cur 은 사이클의 시작부분
                // line 의 앞에서부터 숫자를 하나씩 빼서
                // 사이클의 시작 부분인지 판단 후,
                // 사이클 이전 숫자는 팀을 이룰 수 없다고 판단
                // 사이클의 시작 부분을 찾으면 break
                while(!line.isEmpty()){
                    int temp = line.poll();
                    cango.remove(temp);
                    if(temp == cur) break;
                    noteam_num.add(temp);
                }

                // 사이클 시작부분 이후의 숫자는 전부 하나의 팀이므로 중복 탐지 방지를 위해 cango 에서 제외,
                while(!line.isEmpty()){
                    cango.remove(line.poll());
                }
            }

            sb.append(noteam_num.size()).append('\n');
        }

        System.out.print(sb);
    }
}
