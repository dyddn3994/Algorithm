import java.io.*;
import java.util.*;

class Main {
	
	static int N; // 집의 개수
	static int M; // 길의 개수
	
	static List<Node>[] list; // 길 인접리스트
	static boolean[] visited;
	static int res;
	
	static void prim(int x)	{
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// A번 집과 B번 집을 연결하는 길의 유지비 C
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		visited = new boolean[N+1];
		
		prim(1);
		
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