import java.io.*;
import java.util.*;

public class Solution {
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int R; // 맨홀 세로 위치
	static int C; // 맨홀 가로 위치
	static int L; // 탈출 후 소요시간
	static int[][] arr;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] time; // 위치별 소요시간
	static int res;
	
	static boolean isWall(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return true;
		return false;
	}
	
	// 이동 가능한 방향 찾기
	static int[] direction(int type) {
		int[] tmp = new int[2];
		if (type == 1) {
			// 우 하 좌 상
			tmp = new int[4];
			for (int i = 0; i < 4; i++) tmp[i] = i;
		}
		else if (type == 2) {
			// 하 상
			tmp[0] = 1;
			tmp[1] = 3;
		}
		else if (type == 3) {
			// 우 좌
			tmp[0] = 0;
			tmp[1] = 2;
		}
		else if (type == 4) {
			// 우 상
			tmp[0] = 0;
			tmp[1] = 3;
		}
		else if (type == 5) {
			// 우 하
			tmp[0] = 0;
			tmp[1] = 1;
		}
		else if (type == 6) {
			// 하 좌
			tmp[0] = 1;
			tmp[1] = 2;
		}
		else if (type == 7) {
			// 좌 상
			tmp[0] = 2;
			tmp[1] = 3;
		}
		return tmp;
	}
	
	static void bfs(int x, int y) {
		Deque<Integer> xq = new ArrayDeque<>();
		Deque<Integer> yq = new ArrayDeque<>();
		xq.add(x);
		yq.add(y);
		time[x][y] = 1;
		res++;
		
		while (!xq.isEmpty() && !yq.isEmpty()) {
			x = xq.poll();
			y = yq.poll();
			if (time[x][y] == L) break;
			
			// 이동 가능한 위치 찾기
			int[] dir = direction(arr[x][y]);
			for (int i = 0; i < dir.length; i++) {
				int nx = x + dx[dir[i]];
				int ny = y + dy[dir[i]];
				
				if (!isWall(nx, ny) && arr[nx][ny] != 0 && time[nx][ny] == 0) {
					// 벽이 아니고 0이 아니면 다음 터널 모양 확인해서 연결되어 있는지 확인
					int[] ndir = direction(arr[nx][ny]);
					for (int j = 0; j < ndir.length; j++) {
						if ((ndir[j] + 2) % 4 == dir[i]) {
							xq.add(nx);
							yq.add(ny);
							time[nx][ny] = time[x][y] + 1;
							res++;
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = 0;
			
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new int[N][M];
			time = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(R, C);
			
			// 출력
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		System.out.println(sb);
	}
}