import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int K;
	static int[][] arr;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] top; // 봉우리 위치
	static int topCnt; // 봉우리 개수
	static int topVal; // 봉우리 값
	static int res; // 최대 이동 가능 길이

	static boolean isWall(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return true;
		return false;
	}
	
	static int dfs(int x, int y) {
		int max = 0; // 현재 위치로부터 최대 이동 가능 거리
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isWall(nx, ny) && arr[nx][ny] < arr[x][y]) {
				int dist = dfs(nx, ny);
				max = (max < dist) ? dist : max;
			}
		}
		return max+1; // 이동에 성공했다면 이동 최대거리 반환, 더이상 이동 못하면 현재 위치인 1 반환
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			topCnt = 0;
			topVal = 0;
			res = 0;
			
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					topVal = (topVal < arr[i][j]) ? arr[i][j] : topVal;
				}
			}

			// 봉우리 위치 찾기
			top = new int[N*N][2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == topVal) {
						top[topCnt][0] = i;
						top[topCnt++][1] = j;
					}
				}
			}
			
			// 땅을 깎지 않은 상황에서 dfs
			for (int i = 0; i < topCnt; i++) {
				int x = top[i][0];
				int y = top[i][1];
				int dist = dfs(x, y);
				res = (dist < res) ? res : dist;
			}
			
			// 이중 for문으로 특정 땅을 K번 깎아서 봉우리에서 dfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						arr[i][j] -= 1;
						for (int topIdx = 0; topIdx < topCnt; topIdx++) {
							int x = top[topIdx][0];
							int y = top[topIdx][1];
							int dist = dfs(x, y);
							res = (dist < res) ? res : dist;
						}
					}
					arr[i][j] += K;
				}
			}
			
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