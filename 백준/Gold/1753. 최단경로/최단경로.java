import java.io.*;
import java.util.*;

class Main {

	static int V; // 정점 개수
	static int E; // 간선 개수
	static int K; // 시작 정점
	
	static List<Node>[] list; // 인접 리스트
	static int[] dist; // 시작점에서 해당 위치까지의 비용

	static void dijkstra(int K) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(K, 0));
		dist[K] = 0;

		while(!pq.isEmpty()) {
			Node node = pq.poll();

			if (node.cost > dist[node.num]) continue;

			for (Node n : list[node.num]) {
				if (dist[n.num] <= dist[node.num] + n.cost) continue;

				dist[n.num] = dist[node.num] + n.cost;
				pq.add(new Node(n.num, dist[n.num]));
			}
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        // 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list[u].add(new Node(v, w));
		}

		dijkstra(K);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) sb.append("INF");
			else sb.append(dist[i]);
			sb.append("\n");
		}

		System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
	int num;
	int cost;

	Node (int num, int cost) {
		this.num = num;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node n) {
		return this.cost - n.cost;
	}
}