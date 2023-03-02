import java.io.*;
import java.util.*;

public class Solution {

	static ArrayList<ArrayList<Integer>> children; // 자식 리스트
	static ArrayList<ArrayList<Integer>> parents; // 부모 리스트
	static int cnt; // 선택된 노드 수
	static boolean finished;
	static int V; // 정점 개수
	static int E; // 간선 개수
	static boolean[] visited;
	static int[] res; // 결과 배열
	
	// node: 확인할 노드
	static void dfs(int node) {
		// 올바른 작업 순서를 찾은 경우 dfs 종료
		if (cnt == V) {
			finished = true;
			return;
		}
		
		// 자식 리스트의 값 중에서 부모 리스트를 모두 방문한 자식 노드 방문
		for (int c : children.get(node)) {
			if (finished) return;
			
			if (!visited[c]) {
				// 부모 리스트를 하나라도 방문하지 않았다면 방문 실패
				ArrayList<Integer> p = parents.get(c);
				int i = 0;
				for (; i < p.size(); i++) {
					if (!visited[p.get(i)]) break;
				}
				if (i < p.size()) continue;
				
				// 방문 처리
				res[cnt++] = c;
				visited[c] = true;
				dfs(c);
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	
        for (int t = 1; t <= 10; t++) {
        	children = new ArrayList<>();
        	parents = new ArrayList<>();
        	cnt = 0;
        	finished = false;
        	
        	// 입력
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	visited = new boolean[V+1];
        	res = new int[V];
        	
        	for (int i = 0; i <= V; i++) {
        		children.add(new ArrayList<>());
        		parents.add(new ArrayList<>());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < E; i++) {
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		children.get(a).add(b);
        		parents.get(b).add(a);
        	}
        	// 입력 끝
        	
        	// 부모가 없는 노드 찾기
        	List<Integer> orphans = new ArrayList<>();
        	for (int i = 1; i <= V; i++) {
        		if (parents.get(i).size() == 0) orphans.add(i);
        	}
        	
        	// 부모가 없는 노드에서 dfs
        	for (int node : orphans) {
        		visited[node] = true;
        		res[cnt++] = node;
        		dfs(node);
        	}
        	
        	// 출력
        	sb.append("#")
        		.append(t)
        		.append(" ");
        	for (int i = 0; i < V; i++) {
        		sb.append(res[i])
        			.append(" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
}