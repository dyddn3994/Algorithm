import java.io.*;
import java.util.*;
 
public class Solution {
	// static 변수
	static int res; // 출력 결과
	static int[][] arr; // 입력 배열
	static boolean[] visited; // 방문한 노드
	
	static void dfs(int node) {
		visited[node] = true;
		for (int i = 0; i < 2; i++) {
			int next = arr[i][node]; // 다음으로 방문 가능한 노드
			if (next == 99) {
				res = 1;
				return;
			}
			if (!visited[next]) {
				dfs(next);
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
        	// 선언 및 초기화
        	res = 0;
        	arr = new int[2][100];
        	visited = new boolean[100];
        	
        	// 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
        	int c = Integer.parseInt(st.nextToken());
        	int cnt = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < cnt; i++) {
        		int idx = Integer.parseInt(st.nextToken());
        		int des = Integer.parseInt(st.nextToken());
        		if (arr[0][idx] == 0) arr[0][idx] = des;
        		else arr[1][idx] = des;
        	}
        	// 입력 끝
        	
        	dfs(0);
        	
        	// 출력
        	sb.append("#")
        		.append(c)
        		.append(" ")
        		.append(res)
        		.append("\n");
        }
        System.out.println(sb);
    }
}