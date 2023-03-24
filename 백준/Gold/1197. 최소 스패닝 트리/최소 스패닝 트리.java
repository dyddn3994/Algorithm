import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		// 정점 간 가중치
		List<Node>[] dist = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			dist[i] = new ArrayList<Node>();
		}
		
		// 가중치 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			dist[A].add(new Node(B, C));
			dist[B].add(new Node(A, C));
		}
		
		boolean[] visited = new boolean[V+1];
		
		int res = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if (visited[node.n]) continue;
			
			visited[node.n] = true;
			res += node.d;
			for (Node n : dist[node.n]) {
				if(visited[n.n]) continue;
				pq.add(n);
			}
		}
		
		System.out.println(res);
	}

}

class Node implements Comparable<Node>{
	int n;
	int d;
	Node (int n, int d) {
		this.n = n;
		this.d = d;
	}
	
	@Override
	public int compareTo(Node node) {
		return this.d - node.d; 
	}
	
}
