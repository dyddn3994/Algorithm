import java.io.*;
import java.util.*;

public class Solution {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int[][] arr;
    static boolean[][] visited;

    static int res;

    static boolean isWall(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            // 입력
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = input.charAt(j) - '0';
                }
            }

            // 이동 거리값 저장 배열 초기화
            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) { 
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.add(new Point(0, 0, 0));
            while (!pq.isEmpty()) {
                Point point = pq.poll();
                if (point.x == N-1 && point.y == N-1) {
                    res = point.d;
                    break;
                }

                visited[point.x][point.y] = true;

                for (int i = 0; i < 4; i++) {
                    int nx = point.x + dx[i];
                    int ny = point.y + dy[i];

                    if (isWall(nx, ny) || visited[nx][ny]) continue;

                    int nd = point.d + arr[nx][ny];
                    if (dist[nx][ny] > nd) {
                        dist[nx][ny] = nd;
                        pq.add(new Point(nx, ny, nd));
                    }
                }
            }

            sb.append("#")
                .append(t)
                .append(" ")
                .append(res)
                .append("\n");
        }

        System.out.println(sb);
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int d;

    Point(int x, int y ,int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

	@Override
	public int compareTo(Point p) {
		if (this.d > p.d) return 1;
		else if (this.d < p.d) return -1; 
		return 0;
	}
}