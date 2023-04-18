import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] arr;
	
	static boolean[][] visited;
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static boolean isWall(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return true;
		return false;
	}
	
	static int dijkstra(int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(x, y, arr[x][y]));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (node.x == N - 1 && node.y == N - 1) return node.cost;
			
			if (visited[node.x][node.y]) continue;
			visited[node.x][node.y] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if (isWall(nx, ny) || visited[nx][ny]) continue;
				
				pq.add(new Node(nx, ny, node.cost + arr[nx][ny]));
			}
		}
		
		
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			visited = new boolean[N][N];
			
			int res = dijkstra(0, 0);
			
			sb.append("Problem ")
				.append(t++)
				.append(": ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int cost;
	Node (int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node n) {
		return this.cost - n.cost;
	}
}