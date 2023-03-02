import java.io.*;
import java.util.*;
 
public class Main {

	static int N; // 정점 개수
	static int M; // 간선 개수
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;

	static void dfs(int node) {
		visited[node] = true;
		for(int selec : list.get(node)) {
			if (!visited[selec]) dfs(selec);
		}
	}

    public static void main(String[] args) throws Exception {
    	// 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		int res = 0;

        // 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.get(u).add(v);
			list.get(v).add(u);
		}

		// 정점 전체 확인해서 !visited면 dfs
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				res++;
				dfs(i);
			}
		}

		System.out.println(res);
    }
}