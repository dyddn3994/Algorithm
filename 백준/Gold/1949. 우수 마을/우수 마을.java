import java.io.*;
import java.util.*;

class Main {
    static int N; // 마을 수
    static int[] villager; // 주민 수
    static List<Integer>[] list; // 인접 리스트
    static int[][] dp;
    static boolean[] visited;

    static int villagerSum;
    static int res;

    static int solve(int node, int isSelected) {
        if (dp[node][isSelected] != -1) return dp[node][isSelected];
        visited[node] = true;
        
        int cnt = 0;
        if (isSelected == 1) cnt = villager[node];
        for (int n: list[node]) {
            if (visited[n]) continue;
            
            int val = solve(n, 0);
            if (isSelected == 0) {
                val = Math.max(val, solve(n, 1));
            }

            cnt += val;
        }
        dp[node][isSelected] = cnt;
        visited[node] = false;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        villager = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            villager[i] = Integer.parseInt(st.nextToken());
        }
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list[n1].add(n2);
            list[n2].add(n1);
        }
        
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }
        res = Math.max(solve(1, 0), solve(1, 1));

        System.out.println(res);
    }
}