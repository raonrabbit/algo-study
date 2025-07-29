import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int[] number_arr = new int[number.length()];

        // 문자열로 이루어진 number 을 int 배열로 변환합니다.
        for(int i = 0; i < number.length(); i++){
            number_arr[i] = number.charAt(i) - '0';
        }

        int cur_idx = 0;

        // k 가 0이 될 때까지 반복합니다.
        while(k != 0){
            if(cur_idx + k >= number.length()) break;
            int max = number_arr[cur_idx];
            int maxIdx = cur_idx;

            // 현재 인덱스부터 삭제할 수 있는 개수 + 1 한 인덱스까지 탐색하여
            // 가장 큰 숫자와 그 인덱스를 저장합니다.
            for(int idx = cur_idx; idx < cur_idx + k + 1; idx++){
                if(number_arr[idx] > max){
                    max = number_arr[idx];
                    maxIdx = idx;
                }
            }

            // 가장 큰 숫자를 문자열에 추가하고
            // cur_idx 를 가장 큰 숫자 인덱스 + 1 로 바꿉니다.
            sb.append(max);
            k -= maxIdx - cur_idx;
            cur_idx = maxIdx + 1;
        }

        // 이미 지울 수 있는 친구들은 다 지웠으므로
        // 나머지 문자열들을 추가해줍니다.
        // 만약 k 가 남아있다면 마지막 k 개의 문자열은 추가하지 않습니다.
        for(int i = cur_idx; i < number.length() - k; i++){
            sb.append(number_arr[i]);
        }

        return sb.toString();
    }
}