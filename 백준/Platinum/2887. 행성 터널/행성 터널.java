import java.io.*;
import java.util.*;

class Main {
	
	static int N; // 행성 수
	static Node[] X; // 행성별 위치
	static Node[] Y;
	static Node[] Z;
	
    static List<Node>[] list; // 인접 리스트 
	static boolean[] visited;
	static int res;
	
	static void prim(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0));
		
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		X = new Node[N];
		Y = new Node[N];
		Z = new Node[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            X[i] = new Node(i, Integer.parseInt(st.nextToken()));
            Y[i] = new Node(i, Integer.parseInt(st.nextToken()));
            Z[i] = new Node(i, Integer.parseInt(st.nextToken()));
		}

        // x, y, z값에 따른 정렬, 그 사이의 거리값에 대한 간선을 토대로 인접리스트 생성
        Arrays.sort(X);        
        Arrays.sort(Y);        
        Arrays.sort(Z);

        // 인접리스트 생성
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            list[X[i].n].add(new Node(X[i + 1].n, X[i + 1].cost - X[i].cost));
            list[X[i + 1].n].add(new Node(X[i].n, X[i + 1].cost - X[i].cost));
            
            list[Y[i].n].add(new Node(Y[i + 1].n, Y[i + 1].cost - Y[i].cost));
            list[Y[i + 1].n].add(new Node(Y[i].n, Y[i + 1].cost - Y[i].cost));
            
            list[Z[i].n].add(new Node(Z[i + 1].n, Z[i + 1].cost - Z[i].cost));
            list[Z[i + 1].n].add(new Node(Z[i].n, Z[i + 1].cost - Z[i].cost));
        }

		visited = new boolean[N];
		res = 0;
		
		prim(0);
		
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
	public int compareTo (Node o) {
		return this.cost - o.cost;
	}
}