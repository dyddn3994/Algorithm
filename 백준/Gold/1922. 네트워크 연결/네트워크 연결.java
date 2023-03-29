import java.io.*;
import java.util.*;

class Main {
	
	static int N; // 컴퓨터 수
	static int M; // 연결 선 수
	static List<Node>[] list; // 연결 리스트
	
	static boolean[] visited;
	static int res;
	
	static void prim(int x) {
		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(x, 0));
		
		int visitedCnt = 0; // 방문한 노드 수
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (visited[node.n]) continue;
			visited[node.n] = true;

			res += node.cost;
			
			visitedCnt++;
			if (visitedCnt == N) return;
			
			for (Node nNode : list[node.n]) {
				if (visited[nNode.n]) continue;
				
				queue.add(nNode);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		visited = new boolean[N + 1];
		res = 0;
		
		prim(1);
		
		System.out.println(res);
	}
}

class Node implements Comparable<Node> {
	int n;
	int cost;
	Node (int n, int cost) {
		this.n = n;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}