import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void horizontal_flip(int[][] arr, int x, int y, int n) {
        for (int i = 0; i < n / 2; i++) {
            int r1 = y + i;
            int r2 = y + (n - 1 - i);
            for (int j = 0; j < n; j++) {
                int c = x + j;
                int tmp = arr[r1][c];
                arr[r1][c] = arr[r2][c];
                arr[r2][c] = tmp;
            }
        }
    }

    static void vertical_flip(int[][] arr, int x, int y, int n) {
        for (int j = 0; j < n / 2; j++) {
            int c1 = x + j;
            int c2 = x + (n - 1 - j);
            for (int i = 0; i < n; i++) {
                int r = y + i;
                int tmp = arr[r][c1];
                arr[r][c1] = arr[r][c2];
                arr[r][c2] = tmp;
            }
        }
    }

    static void transpose(int[][] arr, int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int r1 = y + i, c1 = x + j;
                int r2 = y + j, c2 = x + i;
                int tmp = arr[r1][c1];
                arr[r1][c1] = arr[r2][c2];
                arr[r2][c2] = tmp;
            }
        }
    }

    static void right_turn(int[][] arr, int x, int y, int n) {
        transpose(arr, x, y, n);
        vertical_flip(arr, x, y, n);
    }

    static void left_turn(int[][] arr, int x, int y, int n) {
        transpose(arr, x, y, n);
        horizontal_flip(arr, x, y, n);
    }

    static void op1(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        for (int y = 0; y < N; y += size)
            for (int x = 0; x < N; x += size)
                horizontal_flip(arr, x, y, size);
    }

    static void op2(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        for (int y = 0; y < N; y += size)
            for (int x = 0; x < N; x += size)
                vertical_flip(arr, x, y, size);
    }

    static void op3(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        for (int y = 0; y < N; y += size)
            for (int x = 0; x < N; x += size)
                right_turn(arr, x, y, size);
    }

    static void op4(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        for (int y = 0; y < N; y += size)
            for (int x = 0; x < N; x += size)
                left_turn(arr, x, y, size);
    }

    static void copyBlock(int[][] src, int sy, int sx, int[][] dst, int dy, int dx, int size) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(src[sy + i], sx, dst[dy + i], dx, size);
        }
    }

    static void op5(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        int bn = N / size;
        int[][] tmp = new int[N][N];
        for (int by = 0; by < bn; by++) {
            for (int bx = 0; bx < bn; bx++) {
                int sy = by * size, sx = bx * size;
                int ty = (bn - 1 - by) * size, tx = sx;
                copyBlock(arr, sy, sx, tmp, ty, tx, size);
            }
        }
        for (int i = 0; i < N; i++) System.arraycopy(tmp[i], 0, arr[i], 0, N);
    }

    static void op6(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        int bn = N / size;
        int[][] tmp = new int[N][N];
        for (int by = 0; by < bn; by++) {
            for (int bx = 0; bx < bn; bx++) {
                int sy = by * size, sx = bx * size;
                int ty = sy, tx = (bn - 1 - bx) * size;
                copyBlock(arr, sy, sx, tmp, ty, tx, size);
            }
        }
        for (int i = 0; i < N; i++) System.arraycopy(tmp[i], 0, arr[i], 0, N);
    }

    static void op7(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        int bn = N / size;
        int[][] tmp = new int[N][N];
        for (int by = 0; by < bn; by++) {
            for (int bx = 0; bx < bn; bx++) {
                int sy = by * size, sx = bx * size;
                int ty = bx * size, tx = (bn - 1 - by) * size;
                copyBlock(arr, sy, sx, tmp, ty, tx, size);
            }
        }
        for (int i = 0; i < N; i++) System.arraycopy(tmp[i], 0, arr[i], 0, N);
    }

    static void op8(int[][] arr, int l) {
        int size = 1 << l;
        int N = arr.length;
        int bn = N / size;
        int[][] tmp = new int[N][N];
        for (int by = 0; by < bn; by++) {
            for (int bx = 0; bx < bn; bx++) {
                int sy = by * size, sx = bx * size;
                int ty = (bn - 1 - bx) * size, tx = by * size;
                copyBlock(arr, sy, sx, tmp, ty, tx, size);
            }
        }
        for (int i = 0; i < N; i++) System.arraycopy(tmp[i], 0, arr[i], 0, N);
    }

    static void apply(int[][] arr, int k, int l) {
        switch (k) {
            case 1: op1(arr, l); break;
            case 2: op2(arr, l); break;
            case 3: op3(arr, l); break;
            case 4: op4(arr, l); break;
            case 5: op5(arr, l); break;
            case 6: op6(arr, l); break;
            case 7: op7(arr, l); break;
            case 8: op8(arr, l); break;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int N = 1 << n;

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            apply(map, k, l);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(map[i][j]).append(' ');
            sb.setLength(sb.length() - 1);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
