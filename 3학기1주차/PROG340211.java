import java.util.*;

class Solution {
    public static class Robot {
        public Queue<Coord> targetPos;
        public Coord curPos;

        public Robot(Coord curPos, Queue<Coord> targetPos) {
            this.curPos = curPos;
            this.targetPos = targetPos;
        }

        public void Move() {
            if (targetPos.isEmpty()) return;
            Coord target = targetPos.peek();

            if (curPos.y != target.y) {
                curPos.y += curPos.y < target.y ? 1 : -1;
            } else if (curPos.x != target.x) {
                curPos.x += curPos.x < target.x ? 1 : -1;
            }

            if (curPos.equals(target)) {
                targetPos.poll();
            }
        }

        public boolean isDone() {
            return targetPos.isEmpty();
        }
    }

    public static class Coord {
        public int y, x;

        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Coord)) return false;
            Coord coord = (Coord) o;
            return coord.y == this.y && coord.x == this.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int n = routes.length;
        List<Robot> robotList = new ArrayList<>();

        for (int[] route : routes) {
            Coord start = new Coord(points[route[0] - 1][0], points[route[0] - 1][1]);
            Queue<Coord> targets = new ArrayDeque<>();
            for (int i = 1; i < route.length; i++) {
                targets.add(new Coord(points[route[i] - 1][0], points[route[i] - 1][1]));
            }
            robotList.add(new Robot(start, targets));
        }

        int danger = 0;

        Map<Coord, Integer> startCount = new HashMap<>();
        for (Robot robot : robotList) {
            startCount.put(robot.curPos, startCount.getOrDefault(robot.curPos, 0) + 1);
        }
        for (int count : startCount.values()) {
            if (count >= 2) {
                danger++;
            }
        }

        while (true) {
            Map<Coord, Integer> coordCount = new HashMap<>();

            for (Robot robot : robotList) {
                if (!robot.isDone()) {
                    robot.Move();
                    coordCount.put(robot.curPos, coordCount.getOrDefault(robot.curPos, 0) + 1);
                }
            }

            for (int count : coordCount.values()) {
                if (count >= 2) {
                    danger++;
                }
            }

            boolean allDone = true;
            for (Robot robot : robotList) {
                if (!robot.isDone()) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) break;
        }

        return danger;
    }
}
