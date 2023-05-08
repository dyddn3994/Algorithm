import java.io.*;
import java.util.*;

public class Main {
	
	static int N; // 노드
	static int M; // 간선
	static int S; // 출발지
	static int E; // 도착지
	
	static List<Node>[] list;
	static int[] dist;
	
	static void dijkstra(int S) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(S, 0));
		dist[S] = 0;
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			
			if (dist[node.n] < node.cost) continue; 
			
			for (Node nNode : list[node.n]) {
				if (dist[nNode.n] > dist[node.n] + nNode.cost) {
					dist[nNode.n] = dist[node.n] + nNode.cost;
					pq.add(new Node(nNode.n, dist[nNode.n]));
				}
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Node>(); 
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(e, c));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		// 입력 끝
		
		// 시작점부터 해당 위치까지 최단거리
		dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE; 
		}
		
		dijkstra(S);
		
		System.out.println(dist[E]);
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
	public int compareTo(Node n) {
		return this.cost - n.cost;
	}
}