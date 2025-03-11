import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] seat;
    static Student[] students;

    public static class Student{
        public int studentNum;
        public int[] preference;
        public int x;
        public int y;
        public int matchCount;

        public Student(int studentNum, int[] preference){
            this.studentNum = studentNum;
            this.preference = preference;
        }
    }

    public static class Candidate{
        public int preferCount;
        public int emptyCount;
        public int x;
        public int y;

        public Candidate(int preferCount, int emptyCount, int x, int y){
            this.preferCount = preferCount;
            this.emptyCount = emptyCount;
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isGoodCandidate(Candidate current, Candidate newC) {
        if (newC.preferCount > current.preferCount) return true;
        if (newC.preferCount == current.preferCount) {
            if (newC.emptyCount > current.emptyCount) return true;
            if (newC.emptyCount == current.emptyCount) {
                if (newC.y < current.y) return true;
                if (newC.y == current.y && newC.x < current.x) return true;
            }
        }
        return false;
    }

    public static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        seat = new int[n][n];
        int studentCount = (int)Math.pow(n, 2);
        students = new Student[studentCount];

        for(int i = 0; i < studentCount; i++){
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            int[] preference = new int[4];
            for(int j = 0; j < 4; j++){
                preference[j] = Integer.parseInt(st.nextToken());
            }
            students[i] = new Student(studentNum, preference);
        }

        for(int i = 0; i < studentCount; i++){
            Student student = students[i];
            int studentNum = student.studentNum;
            int[] preference = student.preference;
            Candidate candidate = new Candidate(-1, -1, n, n);
            for(int y = 0; y < n; y++){
                for(int x = 0; x < n; x++){
                    if(seat[y][x] != 0) continue;

                    int preferCount = 0;
                    int emptyCount = 0;
                    for(int[] dir: dirs){
                        int ny = y + dir[0];
                        int nx = x + dir[1];
                        if(ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                        if(seat[ny][nx] == 0) emptyCount++;
                        for(int j = 0; j < 4; j++){
                            if(seat[ny][nx] == preference[j]){
                                preferCount++;
                                break;
                            }
                        }
                    }

                    if(isGoodCandidate(candidate, new Candidate(preferCount, emptyCount, x, y))){
                        candidate = new Candidate(preferCount, emptyCount, x, y);
                    }
                }
            }
            seat[candidate.y][candidate.x] = studentNum;
            student.x = candidate.x;
            student.y = candidate.y;
            student.matchCount = candidate.preferCount;
        }

        int result = 0;
        for(Student s : students) {
            int y = s.y;
            int x = s.x;
            int matchCount = 0;
            for (int[] dir : dirs) {
                int ny = y + dir[0];
                int nx = x + dir[1];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                for (int i = 0; i < 4; i++) {
                    if (seat[ny][nx] == s.preference[i]) {
                        matchCount++;
                        break;
                    }
                }

            }

            switch (matchCount) {
                case 0:
                    break;
                case 1:
                    result += 1;
                    break;
                case 2:
                    result += 10;
                    break;
                case 3:
                    result += 100;
                    break;
                case 4:
                    result += 1000;
                    break;
            }
        }
        System.out.print(result);
    }
}
