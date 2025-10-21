import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    public static int binarySearch(int[] arr, int target){
        int start = 0;
        int end = M - 1;
        int mid = end / 2;

        while (start < end) {

            // arr[mid] 가 0이다 -> arr[mid] 왼쪽에는 아무런 값도 없다.
            // 오른쪽 탐색하며 값이 있는 index 탐색하고 start 로 지정, mid 재계산
            if (arr[mid] == 0) {
                int i = mid + 1;
                while (i < M && arr[i] == 0) i++;
                if (i >= M) {
                    i = M - 1;
                }
                start = i;
                mid = (start + end) / 2;
                continue;
            }

            // arr[mid] 가 음수다 -> 그 값만큼 왼쪽으로 가면 값이 있다. ex) mid = 5, arr[mid] = -2 -> arr[3] 에 값이 있음
            // arr[mid + arr[mid]] 값으로 이동, 그 곳을 mid 로 설정, 만약 mid + arr[mid] 가 start 보다 작을 경우
            // 조건에 맞는 값은 arr[mid] 오른쪽에 가장 먼저 나오는 양수값의 위치이다.
            if (arr[mid] < 0) {
                int temp = mid + arr[mid];
                if (temp < start) {
                    int i = mid + 1;
                    while (i < M && arr[i] <= 0) i++;
                    return i < M ? i : M - 1;
                }
                mid = temp;
                continue;
            }

            // start, mid 가 세팅 되었으므로 평범한 이분탐색
            // arr[mid] 가 타겟보다 클 경우
            if (arr[mid] > target) {
                end = mid;
                mid = (start + end) / 2;
                continue;
            }

            // arr[mid] 가 타겟보다 작거나 같을 경우
            start = mid + 1;
            mid = (start + end) / 2;
        }

        return start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] minsu = new int[M];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++){
            minsu[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(minsu);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int chulsu = Integer.parseInt(st.nextToken());

            int result = binarySearch(minsu, chulsu);
            sb.append(minsu[result]).append('\n');

            // 결과값을 minsu 배열에서 제거하는 작업 필요
            // result 가 0 이거나 minsu[result - 1] 가 0일 경우
            // minsu[result] = 0 으로 만들어서 왼쪽에 더이상 어떤 값도 없음을 표시
            // minsu[result] 오른쪽에 음수값이 있을 경우 minsu[result] 가 0이 되기 때문에
            // 우측에 있는 연속된 음수값들도 0으로 바꿈
            if (result == 0 || minsu[result - 1] == 0) {
                minsu[result] = 0;
                int r = result + 1;
                while (r < M && minsu[r] < 0) {
                    minsu[r++] = 0;
                }
                continue;
            }

            // minsu[result - 1] 이 음수인 경우 minsu[result] 도 옆의 음수값 - 1 로 세팅
            // 우측에 연속된 음수값이 있으면 업데이트
            // ex) minsu[result - 1] = -3 -> minsu[result] = -4 ( 4번째 전 인덱스에 값이 있음 )
            if (minsu[result - 1] < 0) {
                int temp = minsu[result - 1];
                temp -= 1;
                minsu[result] = temp;
                int r = result + 1;
                while (r < M && minsu[r] < 0) {
                    minsu[r] = --temp;
                    r++;
                }
                continue;
            }

            // minsu[result - 1] 이 양수인 경우
            // minsu[result] = -1 로 1 인덱스 전에 값이 있다고 표시 & 우측의 연속된 음수값이 있으면 업데이트
            int temp = -1;
            minsu[result] = temp;
            int r = result + 1;
            while (r < M && minsu[r] < 0) {
                minsu[r] = --temp;
                r++;
            }
        }

        System.out.print(sb);
    }
}