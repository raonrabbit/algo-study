import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] map;
    static Set<Shark> sharks;

    public static class Shark {
        public int r, c, speed, dir, size;

        public Shark(int r, int c, int speed, int dir, int size){
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static int catchShark(int fisher){
        for(int i = 1; i <= R; i++){
            if(map[i][fisher] != null){
                Shark shark = map[i][fisher];
                map[i][fisher] = null;
                sharks.remove(shark);
                return shark.size;
            }
        }

        return 0;
    }

    public static void moveSharks() {
        Shark[][] newMap = new Shark[R + 1][C + 1];
        List<Shark> list = new ArrayList<>(sharks);

        for (Shark shark : list) {
            int d = shark.dir;
            int r = shark.r;
            int c = shark.c;

            if (d == 1 || d == 2) {
                int s = shark.speed % ((R - 1) * 2);

                for (int i = 0; i < s; i++) {
                    if (r == 1 && d == 1) d = 2;
                    else if (r == R && d == 2) d = 1;
                    r += (d == 1 ? -1 : 1);
                }
            } else {
                int s = shark.speed % ((C - 1) * 2);

                for (int i = 0; i < s; i++) {
                    if (c == 1 && d == 4) d = 3;
                    else if (c == C && d == 3) d = 4;
                    c += (d == 3 ? 1 : -1);
                }
            }

            shark.r = r;
            shark.c = c;
            shark.dir = d;

            if(newMap[shark.r][shark.c] != null){
                Shark temp_shark = newMap[shark.r][shark.c];

                if(temp_shark.size > shark.size){
                    sharks.remove(shark);
                } else {
                    sharks.remove(temp_shark);
                    newMap[shark.r][shark.c] = shark;
                }
            } else {
                newMap[shark.r][shark.c] = shark;
            }
        }

        map = newMap;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sharks = new HashSet<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r, c, s, d, z);
            map[r][c] = shark;
            sharks.add(shark);
        }

        int fisher = 0;
        int answer = 0;
        while(fisher < C){
            fisher++;
            answer += catchShark(fisher);
            moveSharks();
        }

        System.out.print(answer);
    }
}
