import java.io.*;
import java.util.*;

class Solution {
	
	static int n; // 막대 개수
	static int[] fronts; // 앞부분 배열
	static int[] rears; // 뒷부분 배열
	
	static boolean[] visited;
	static int[] selected; // 선택된 배열
	static boolean finished; // 종료 여부
	
	static void select(int cnt) {
		if (cnt == n*2 - 1) finished = true;
		
		for (int i = 0; i < n; i++) {
			if (finished) return;
			if (visited[i] || fronts[i] != selected[cnt]) continue;
			
			selected[cnt+1] = fronts[i];
			selected[cnt+2] = rears[i];
			visited[i] = true;
			select(cnt+2);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			n = Integer.parseInt(br.readLine());
			fronts = new int[n];
			rears = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				fronts[i] = Integer.parseInt(st.nextToken());
				rears[i] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[n];
			selected = new int[n*2];
			finished = false;
			
			for (int i = 0; i < n; i++) {
				if (finished) break;
				
				selected[0] = fronts[i];
				selected[1] = rears[i];
				visited[i] = true;
				select(1);
				visited[i] = false;
			}
			select(0);
			
			sb.append("#")
				.append(t)
				.append(" ");
			for (int i = 0; i < n*2; i++) {
				sb.append(selected[i])
					.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
