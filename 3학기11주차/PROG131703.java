import java.util.*;

class Solution {
    public int[][] board;
    public int[][] target_board;
    public int cnt;
    public int min = 100;

    public int solution(int[][] beginning, int[][] target) {
        int h = beginning.length;
        int w = beginning[0].length;
        board = beginning;
        target_board = target;

        DFS(0);
        if(min == 100) min = -1;
        return min;
    }

    public boolean isSame(int n){
        for(int i = 0; i < board.length; i++){
            if(board[i][n] != target_board[i][n]) return false;
        }
        return true;
    }

    public void flip(boolean isH, int n){
        if(isH){
            // 행 뒤집기
            for(int i = 0; i < board[0].length; i++){
                board[n][i] = (board[n][i] + 1) % 2;
            }
        } else {
            // 열 뒤집기
            for(int i = 0; i < board.length; i++){
                board[i][n] = (board[i][n] + 1) % 2;
            }
        }
    }

    public void DFS(int depth){
        if(depth == board.length){
            int cnt_temp = cnt;
            int i = 0;

            // 열 확인
            for(i = 0; i < board[0].length; i++){
                // 이미 같아서 뒤집을 필요 없는 경우
                if(isSame(i)) continue;

                // 뒤집으면 같아지는 경우
                flip(false, i);
                if(isSame(i)){
                    cnt_temp++;
                    flip(false, i);
                    continue;
                }
                flip(false, i);

                // 둘다 해당 안되는 경우
                break;
            }

            if(i != board[0].length) return;
            min = Math.min(min, cnt_temp);
            return;
        }

        // 행 뒤집기
        cnt++;
        flip(true, depth);
        DFS(depth + 1);
        flip(true, depth);
        cnt--;

        DFS(depth + 1);
    }
}