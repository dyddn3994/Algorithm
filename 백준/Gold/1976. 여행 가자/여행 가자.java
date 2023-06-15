import java.io.*;
import java.util.*;

class Main {
    static int N; // 도시 수
    static int M; // 여행 계획 도시 수
    
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if ("1".equals(st.nextToken())) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = 1;
        int p = getParent(Integer.parseInt(st.nextToken()));
        for (; i < M; i++) {
            if (p != getParent(Integer.parseInt(st.nextToken()))) break;
        }

        if (i == M) System.out.print("YES");
        else System.out.print("NO");
    }
}