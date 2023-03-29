import java.io.*;
import java.util.*;

class Main {
	
	static int n; // 별의 개수
	static double[] X; // 각 별의 위치
	static double[] Y;
	
	static boolean[] visited;
	static double res;
	
	static void prim(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0.0));
		
		int visitedCnt = 0; // 방문한 노드 수
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (visited[node.n]) continue;
			visited[node.n] = true;
			
			res += node.cost;
			
			// 노드 다 방문하였으면 종료
			visitedCnt++;
			if (visitedCnt == n) return;
			
			// 현재 노드로부터 방문하지 않은 노드 queue 삽입
			double x = X[node.n];
			double y = Y[node.n];
			for (int i = 0; i < n; i++) {
				if (visited[i]) continue;
				
				double dist = Math.sqrt((x - X[i]) * (x - X[i]) + (y - Y[i]) * (y - Y[i]));
				queue.add(new Node(i, dist));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		X = new double[n];
		Y = new double[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X[i] = Double.parseDouble(st.nextToken());
			Y[i] = Double.parseDouble(st.nextToken());
		}
		
		visited = new boolean[n];
		res = 0.0;
		
		prim(0);
		
		System.out.printf("%.2f", res);
	}
}

class Node implements Comparable<Node> {
	int n;
	double cost;
	Node (int n, double cost) {
		this.n = n;
		this.cost = cost;
	}
	
	@Override
	public int compareTo (Node o) {
		return (int) (this.cost - o.cost);
	}
}