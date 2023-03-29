import java.io.*;
import java.util.*;

class Solution {
	
	static int N; // 도시의 크기
	static int M; // 하나의 집이 지불할 수 있는 비용
	static int[][] arr; // 입력 배열
	
	static int totalHouse; // 전체 집 개수
	static boolean[][] visited;
	static int res;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static boolean isWall(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return true;
		return false;
	}
	
	// 시작위치에서 bfs 순회하면서 k값 증가할때마다 서비스 제공받는 집 계산
	static void bfs(int x, int y) {
		int k = 1; // 서비스 영역
		int profit = 0; // 보안회사 이익
		int houseCnt = 0; // 서비스 제공받는 집 개수
		
		int[] pos = {x, y};
		int[] boundary = {-1, k};
		
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(pos);
		queue.add(boundary);
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			pos = queue.poll();
			
			// k값에 따른 이득 계산
			if (pos[0] == -1) {
				k = pos[1];
				profit = houseCnt * M - (k * k + (k - 1) * (k - 1));
				
				if (profit >= 0) res = Math.max(res, houseCnt);
				if (houseCnt == totalHouse) return; // 범위 안에 전체 집이 있다면 종료
				
				boundary[1] = k + 1;
				queue.add(boundary);
				continue;
			}
			
			// 해당 위치가 집이면 카운트 증가
			int tx = pos[0];
			int ty = pos[1];
			if (arr[tx][ty] == 1) houseCnt++;
			
			for (int i = 0; i < 4; i++) {
				int nx = tx + dx[i];
				int ny = ty + dy[i];
				
				if (isWall(nx, ny) || visited[nx][ny]) continue;
				
				int[] npos = {nx, ny};
				queue.add(npos);
				visited[nx][ny] = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			totalHouse = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) totalHouse++;
				}
			}

			res = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (res == totalHouse) break;
					
					visited = new boolean[N][N];
					bfs(i, j);
				}
			}

			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
