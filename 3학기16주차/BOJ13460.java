import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int N, M;
    public static boolean[][] map;
    public static int targetY, targetX;

    public static Position nextPos;
    public static int answer = -1;

    public static class Position {
        public int RY, RX;
        public int BY, BX;

        public Position() {}

        public Position(int RY, int RX, int BY, int BX) {
            this.RY = RY;
            this.RX = RX;
            this.BY = BY;
            this.BX = BX;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Position)) return false;
            Position o = (Position) obj;
            return RY == o.RY && RX == o.RX && BY == o.BY && BX == o.BX;
        }

        @Override
        public int hashCode() {
            return Objects.hash(RY, RX, BY, BX);
        }
    }

    static class MoveResult {
        int y, x;
        int moved;
        boolean fell;

        MoveResult(int y, int x, int moved, boolean fell){
            this.y = y; this.x = x; this.moved = moved; this.fell = fell;
        }
    }

    static MoveResult roll(int y, int x, int dy, int dx) {
        int cnt = 0;

        while (true) {
            int ny = y + dy;
            int nx = x + dx;

            if (!map[ny][nx]) break;

            y = ny;
            x = nx;
            cnt++;

            if (y == targetY && x == targetX)
                return new MoveResult(y, x, cnt, true);
        }

        return new MoveResult(y, x, cnt, false);
    }

    public static int tilt(Position curPosition, int dir) {
        int dy = dirs[dir][0];
        int dx = dirs[dir][1];

        MoveResult r = roll(curPosition.RY, curPosition.RX, dy, dx);
        MoveResult b = roll(curPosition.BY, curPosition.BX, dy, dx);

        if (b.fell) return -1;
        if (r.fell) return 1;

        if (r.y == b.y && r.x == b.x) {
            if (r.moved > b.moved) {
                r.y -= dy; r.x -= dx;
            } else {
                b.y -= dy; b.x -= dx;
            }
        }

        nextPos = new Position(r.y, r.x, b.y, b.x);
        return 0;
    }

    public static void BFS(Position startPosition){
        Set<Position> visited = new HashSet<>();
        Queue<Position> q = new ArrayDeque<>();

        visited.add(startPosition);
        q.add(startPosition);

        int depth = 0;

        while (!q.isEmpty() && depth < 10) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Position cur = q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int result = tilt(cur, dir);
                    if (result == -1) continue;
                    if (result == 1) {
                        answer = depth + 1;
                        return;
                    }
                    Position np = nextPos;
                    if (!visited.contains(np)) {
                        visited.add(np);
                        q.add(np);
                    }
                }
            }
            depth++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        Position startPosition = new Position();
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                char c = s.charAt(j);

                map[i][j] = c != '#';

                if(c == 'B'){
                    startPosition.BY = i;
                    startPosition.BX = j;
                }

                if(c == 'R'){
                    startPosition.RY = i;
                    startPosition.RX = j;
                }

                if(c == 'O'){
                    targetY = i;
                    targetX = j;
                }
            }
        }

        BFS(startPosition);
        System.out.println(answer);
    }
}
