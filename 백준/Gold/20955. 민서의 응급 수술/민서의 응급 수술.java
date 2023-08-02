import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;

    static int res;

    static int[] parent;
    static int getParent(int n) {
        if (parent[n] != n) parent[n] = getParent(parent[n]);
        return parent[n];
    }

    static void union(int n1, int n2) {
        int p1 = getParent(n1);
        int p2 = getParent(n2);
        parent[p2] = p1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (getParent(u) == getParent(v)) res++;
            else union(u, v);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(getParent(i));
        }

        System.out.println(res + set.size() - 1);
    }
}
