class Solution {
    static int[][] map;

    static int rotate(int startR, int startC, int endR, int endC){
        int temp = map[startR][startC];
        int temp2 = -1;
        int min = temp;

        for(int c = startC; c < endC; c++){
            temp2 = map[startR][c + 1];
            map[startR][c + 1] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        for(int r = startR; r < endR; r++){
            temp2 = map[r + 1][endC];
            map[r + 1][endC] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        for(int c = endC; c > startC; c--){
            temp2 = map[endR][c - 1];
            map[endR][c - 1] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        for(int r = endR; r > startR; r--){
            temp2 = map[r - 1][startC];
            map[r - 1][startC] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }


        return min;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        int[] answer = new int[queries.length];

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                map[r][c] = c + (r * columns) + 1;
            }
        }

        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            answer[i] = rotate(query[0] - 1, query[1] - 1,
                    query[2] - 1, query[3] - 1);
        }

        return answer;
    }
}