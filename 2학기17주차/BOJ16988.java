import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] baduk;
    static boolean[][] visited;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int groupIdx = 2;
    static Map<Integer, Integer> groupSize = new HashMap<>();
    static Map<Integer, Set<Node>> groupEmpty = new HashMap<>();
    static List<Node> emptyList = new ArrayList<>();

    static class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        // set 비교를 위해
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node other = (Node) o;
            return this.y == other.y && this.x == other.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        baduk = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                baduk[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();
        int max = result();
        System.out.println(max);
    }

    static void BFS() {
        Set<Node> usefulEmpty = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (baduk[i][j] == 2 && !visited[i][j]) {
                    Queue<Node> q = new ArrayDeque<>();
                    q.add(new Node(i, j));
                    visited[i][j] = true;

                    int size = 1;
                    Set<Node> empty = new HashSet<>();

                    while (!q.isEmpty()) {
                        Node cur = q.poll();
                        for (int[] dir : dirs) {
                            int ny = cur.y + dir[0];
                            int nx = cur.x + dir[1];
                            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                            if (baduk[ny][nx] == 2 && !visited[ny][nx]) {
                                visited[ny][nx] = true;
                                q.add(new Node(ny, nx));
                                size++;
                            } else if (baduk[ny][nx] == 0) {
                                empty.add(new Node(ny, nx));
                            }
                        }
                    }

                    // 적의 바둑돌 그룹에 인접한 빈 공간이 2 이하일 때
                    if (empty.size() <= 2) {
                        groupSize.put(groupIdx, size);
                        groupEmpty.put(groupIdx, empty);
                        usefulEmpty.addAll(empty);
                    }

                    groupIdx++;
                }
            }
        }

        emptyList.addAll(usefulEmpty);
    }

    static int result() {
        int max = 0;
        int size = emptyList.size();

        // 후보돌들 순회
        for (int i = 0; i < size; i++) {
            Node a = emptyList.get(i);

            // 하나의 돌만 선택하는 경우 잡을 수 있는 최대
            Set<Node> oneSet = Set.of(a);
            int oneTotal = 0;
            for (int group : groupSize.keySet()) {
                Set<Node> emptySet = groupEmpty.get(group);
                if (emptySet.size() == 1 && oneSet.containsAll(emptySet)) {
                    oneTotal += groupSize.get(group);
                }
            }
            max = Math.max(max, oneTotal);

            // 두개의 돌을 선택하는 경우 잡을 수 있는 최대
            for (int j = i + 1; j < size; j++) {
                Node b = emptyList.get(j);
                Set<Node> twoSet = Set.of(a, b);

                int total = 0;
                for (int group : groupSize.keySet()) {
                    Set<Node> target = groupEmpty.get(group);
                    if (target.size() == 1 && twoSet.containsAll(target)) {
                        total += groupSize.get(group);
                    } else if (target.size() == 2 && twoSet.containsAll(target)) {
                        total += groupSize.get(group);
                    }
                }
                max = Math.max(max, total);
            }
        }

        return max;
    }


}
