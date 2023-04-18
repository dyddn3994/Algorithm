import java.io.*;
import java.util.*;

public class Main {
	
	static int n; // 컴퓨터 개수
	static int d; // 의존성 개수
	static int c; // 해킹당한 컴퓨터 번호
	
	static List<Node>[] list; // 인접 리스트
	static boolean[] visited;
	
	// 결과
	static int cnt;
	static int time;
	
	static void dijkstra(int c) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(c, 0));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			
			if (visited[node.x]) continue;
			visited[node.x] = true;
			
			cnt++;
			time = node.cost;
			
			for (Node node2 : list[node.x]) {
				if (visited[node2.x]) continue;
				
				pq.add(new Node(node2.x, node.cost + node2.cost));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<Node>();
			}
			
			visited = new boolean[n + 1];
			cnt = 0;
			time = 0;
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Node(a, s));
			}
			
			dijkstra(c);
			
			sb.append(cnt).append(" ").append(time).append("\n");
		}
		
		System.out.println(sb);
	}
}

class Node implements Comparable<Node> {
	int x;
	int cost;
	Node (int x, int cost) {
		this.x = x;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node n) {
		return this.cost - n.cost;
	} 
}