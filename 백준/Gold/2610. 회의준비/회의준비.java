import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static List<Integer>[] list;

    static int[] parent;
    static int getParent(int node) {
        if (parent[node] != node) parent[node] = getParent(parent[node]);
        return parent[node];
    }
    static void union(int n1, int n2) {
        int p1 = getParent(n1);
        int p2 = getParent(n2);
        parent[p2] = p1;
    }

    static int[][] dist;
    static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) continue;
//                    if (i == k || i == j || j == k) continue;
                    if (getParent(i) != getParent(j) || getParent(i) != getParent(k)) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    static int[] maxDist;
    static void setMaxDist() {
        for (int i = 1; i <= N; i++) {
            int res = 0;

            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) continue;
                res = Math.max(res, dist[i][j]);
            }

            maxDist[i] = res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        dist = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            list[n1].add(n2);
            list[n2].add(n1);
            union(n1, n2);
            dist[n1][n2] = dist[n2][n1] = 1;
        }

        floydWarshall();

        maxDist = new int[N + 1];
        setMaxDist();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int p = getParent(i);
            if (!map.containsKey(p)) map.put(p, i);
            else {
                int idx = map.get(p);
                if (maxDist[idx] > maxDist[i]) map.put(p, i);
            }
        }

        sb.append(map.size()).append("\n");
        List<Integer> resList = new ArrayList<>();

        for (Integer i : map.keySet()) {
            resList.add(map.get(i));
        }
        Collections.sort(resList);
        for (Integer i : resList) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}
