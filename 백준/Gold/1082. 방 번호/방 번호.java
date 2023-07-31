import java.io.*;

public class Main {

    static int N;
    static int[] P;
    static int M;

    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(input[i]);
        }
        M = Integer.parseInt(br.readLine());

        nodes = new Node[M + 1];
        for (int i = 0; i <= M; i++) nodes[i] = new Node();

        for (int i = N - 1; i > 0; i--) {
            for (int j = P[i]; j <= M; j++) {
                nodes[j - P[i]].cnt[i]++;

                if (nodes[j - P[i]].isBiggerThan(nodes[j])) {
                    for (int k = 0; k <= 9; k++) {
                        nodes[j].cnt[k] = nodes[j - P[i]].cnt[k];
                    }
                }

                nodes[j - P[i]].cnt[i]--;
            }
        }

        // 0일때는 0만으로 이루어져 있으면 무효
        for (int i = P[0]; i <= M; i++) {
            if (nodes[i - P[0]].isOnlyZero()) continue;

            nodes[i - P[0]].cnt[0]++;

            if (nodes[i - P[0]].isBiggerThan(nodes[i])) {
                for (int k = 0; k <= 9; k++) {
                    nodes[i].cnt[k] = nodes[i - P[0]].cnt[k];
                }
            }

            nodes[i - P[0]].cnt[0]--;
        }

        StringBuilder sb = new StringBuilder();
        if (nodes[M].getTotalCnt() == 0) sb.append(0);
        else {
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < nodes[M].cnt[i]; j++) {
                    sb.append(i);
                }
            }
        }
        System.out.println(sb);
    }
}

class Node {
    int[] cnt;
    Node() {
        cnt = new int[10];
    }

    int getTotalCnt() {
        int n = 0;
        for (int i = 0; i <= 9; i++) {
            n += cnt[i];
        }
        return n;
    }

    boolean isOnlyZero() {
        int n = 0;
        for (int i = 1; i <= 9; i++) {
            n += cnt[i];
        }
        return n == 0 && cnt[0] == 1;
    }

    public boolean isBiggerThan(Node o) {
        if (this.getTotalCnt() > o.getTotalCnt()) return true;
        if (this.getTotalCnt() < o.getTotalCnt()) return false;

        for (int i = 9; i >= 0; i--) {
            if (this.cnt[i] > o.cnt[i]) return true;
            if (this.cnt[i] < o.cnt[i]) return false;
        }

        return false;
    }
}
