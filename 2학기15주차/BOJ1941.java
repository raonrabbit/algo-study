import java.io.*;
import java.util.*;

public class Main {

    static char[][] map;
    static int[] group;
    static int groupCount;

    // 다 이어져 있는지 체크
    public static void BFS(){
        Queue<Integer> coord = new ArrayDeque<>();
        coord.add(group[0]);
        boolean[] groupCheck = new boolean[25];

        for(int i = 1; i < 7; i++){
            groupCheck[group[i]] = true;
        }

        int result = 1;

        while(!coord.isEmpty()){
            int size = coord.size();

            for(int i = 0; i < size; i++){
                int n = coord.poll();

                // 위에 있는 학생 체크
                if(n - 5 >= 0 && groupCheck[n - 5]){
                    coord.add(n - 5);
                    groupCheck[n - 5] = false;
                    result++;
                }

                // 아래에 있는 학생 체크
                if(n + 5 < 25 && groupCheck[n + 5]){
                    coord.add(n + 5);
                    groupCheck[n + 5] = false;
                    result++;
                }

                // 왼쪽에 있는 학생 체크
                if(n - 1 >= 0 && groupCheck[n - 1] && n % 5 != 0){
                    coord.add(n - 1);
                    groupCheck[n - 1] = false;
                    result++;
                }

                // 오른쪽에 있는 학생 체크
                if(n + 1 < 25 && groupCheck[n + 1] && n % 5 != 4){
                    coord.add(n + 1);
                    groupCheck[n + 1] = false;
                    result++;
                }
            }
        }

        if(result == 7) groupCount++;
    }

    public static void DFS(int start, int depth, int sCount){
        if(depth == 7){
            // S 파가 4명 이상 있는지
            if(sCount < 4) return;

            // 모두 이어져 있는지
            BFS();
            return;
        }

        for(int i = start; i < 25; i++){
            group[depth] = i;
            if(map[i / 5][i % 5] == 'S') {
                DFS(i + 1, depth + 1, sCount + 1);
            } else {
                DFS(i + 1, depth + 1, sCount);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        group = new int[7];
        groupCount = 0;

        for(int i = 0; i < 5; i++){
            String s = br.readLine();
            for(int j = 0; j < 5; j++){
                map[i][j] = s.charAt(j);
            }
        }

        DFS(0, 0, 0);

        System.out.print(groupCount);
    }
}
