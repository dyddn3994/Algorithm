import java.io.*;
import java.util.*;

public class Solution {
	
	static int N; // 고객 수
	static int[][] arr; // 회사, 집, N명 고객 좌표순 
	
	static boolean[] visited; // 고객 방문여부
	static int res; // 출력 결과
	
	// cnt: 들른 집 수, sum: 거리 합, x,y: 현재 위치
	static void solve(int cnt, int sum, int x, int y) {
		if (cnt == N) {
			// 고객 다 들름, 집으로 이동
			int dist = Math.abs(x - arr[1][0]) + Math.abs(y - arr[1][1]); // 현재 위치에서 집까지 거리
			sum += dist;
			
			res = Math.min(res, sum);
			
			return;
		}
		
		for (int i = 2; i < N+2; i++) {
			if (visited[i]) continue;
			
			int dist = Math.abs(x - arr[i][0]) + Math.abs(y - arr[i][1]); // 현재 위치에서 거리
			
			visited[i] = true;
			solve(cnt+1, sum+dist, arr[i][0], arr[i][1]);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N+2][2];
			visited = new boolean[N+2];
			for (int i = 0; i < N+2; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			res = Integer.MAX_VALUE;
			
			solve(0, 0, arr[0][0], arr[0][1]);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
