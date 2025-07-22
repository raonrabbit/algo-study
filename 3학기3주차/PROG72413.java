class Solution {
    public static int INF = 100000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n + 1][n + 1];

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }

        for(int[] fare : fares){
            int first = fare[0];
            int second = fare[1];
            int dist = fare[2];

            map[first][second] = dist;
            map[second][first] = dist;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) continue;
                for(int t = 1; t <= n; t++){
                    map[j][t] = Math.min(map[j][t], map[j][i] + map[i][t]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            if(map[s][i] == INF) continue;
            int dist = map[s][i];

            dist += map[i][a];
            dist += map[i][b];

            if(dist >= INF) continue;

            answer = Math.min(answer, dist);
        }

        return answer;
    }
}