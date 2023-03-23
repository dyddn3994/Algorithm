import java.io.*;
import java.util.*;

public class Solution {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            // 입력
            int N = Integer.parseInt(br.readLine()); // 섬의 개수
            long[] X = new long[N];
            long[] Y = new long[N];
            long[] D = new long[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Long.parseLong(st.nextToken());
                D[i] = Long.MAX_VALUE;
            }
            double E = Double.parseDouble(br.readLine());

            boolean[] visited = new boolean[N];
            long total = 0L;

            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.add(new Point(0, 0));
            while(!pq.isEmpty()) {
                Point point = pq.poll();
                if (visited[point.idx]) continue;

                total += point.d;
                visited[point.idx] = true;
                for (int i = 0; i < N; i++) {
                    if (visited[i]) continue;

                    long dist = (X[point.idx] - X[i])*(X[point.idx] - X[i]) + (Y[point.idx] - Y[i])*(Y[point.idx] - Y[i]);
                    if (D[i] > dist) {
                        D[i] = dist;
                        pq.add(new Point(i, dist));
                    }
                }
            }

            sb.append("#")
                .append(t)
                .append(" ")
                .append(Math.round(total * E))
                .append("\n");
        }

        System.out.println(sb);
    }
}

class Point implements Comparable<Point> {
    int idx;
    long d;

    Point(int idx, long d) {
        this.idx = idx;
        this.d = d;
    }

	@Override
	public int compareTo(Point p) {
		if (this.d > p.d) return 1;
		else if (this.d < p.d) return -1; 
		return 0;
	}
}