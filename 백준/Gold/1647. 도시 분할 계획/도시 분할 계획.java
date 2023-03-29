import java.io.*;
import java.util.*;

// 노드 최대 100_000, 간선 최대 1_000_000
class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N; // 집의 개수
	static int M; // 길의 개수

	static int res;
	
	// prim
	static List<Node>[] list; // 길 인접리스트
	static boolean[] visited;
	
	static void prim(int x) throws IOException	{
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// A번 집과 B번 집을 연결하는 길의 유지비 C
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		visited = new boolean[N+1];
		
		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(x, 0));
		int maxRoad = 0; // 가장 유지비가 큰 길의 유지비, 해당 길만 끊어서 마을 구성
		int visitedCnt = 0; // 방문한 노드 수
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (visited[node.num]) continue;
			visited[node.num] = true;
			
			res += node.expense;
			maxRoad = Math.max(maxRoad, node.expense);
			
			visitedCnt++;
			if (visitedCnt == N) break;

			for (Node n : list[node.num]) {
				if (!visited[n.num]) queue.add(n);
			}
		}
		
		res -= maxRoad;
	}
	
	// kruskal
	static PriorityQueue<Node2> queue;
	static int[] parent;
	
	static void kruskal() throws IOException {
		queue = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			queue.add(new Node2(A, B, C));
		}
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int maxRoad = 0; // 가장 유지비가 큰 길의 유지비, 해당 길만 끊어서 마을 구성
		
		while(!queue.isEmpty()) {
			Node2 node = queue.poll();
			
			if (findSet(node.s) == findSet(node.e)) continue;
			union(node.s, node.e);
			
			res += node.cost;
			maxRoad = Math.max(maxRoad, node.cost);
		}
		
		res -= maxRoad;
	}
	
	static int findSet(int x) {
		if (parent[x] != x) parent[x] = findSet(parent[x]);
		return parent[x];
	}
	
	static void union(int x, int y) {
		int p1 = findSet(x);
		int p2 = findSet(y);
		
		parent[p1] = p2;
	}
	
	public static void main(String[] args) throws Exception {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		prim(1);
		
		kruskal();
		
		System.out.println(res);
	}
}

class Node implements Comparable<Node> {
	int num; // 집 번호
	int expense; // 유지비
	
	Node (int num, int expense) {
		this.num = num;
		this.expense = expense;
	}

	@Override
	public int compareTo(Node o) {
		return this.expense - o.expense;
	}
}

class Node2 implements Comparable<Node2> {
	int s;
	int e;
	int cost;
	
	Node2 (int s, int e, int cost) {
		this.s = s;
		this.e = e;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node2 o) {
		return this.cost - o.cost;
	}
}