import java.io.*;
import java.util.*;

public class Solution {
	
	static int N; // 행열 크기
	static int[][] arr; // 코어 배치 상태

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int line; // 연결된 선 개수
	static int core; // 연결된 코어 개수
	
	static boolean isWall(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return true;
		return false;
	}
	
	// 코어 찾아서 선 놓기
	static void connect(int xIdx, int yIdx, int[][] tmp, int lineCnt, int coreCnt) {
		for (int i = xIdx; i < N-1; i++) {
			for (int j = yIdx; j < N-1; j++) {
				if (arr[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						int[][] lineTmp = new int[N][N];
						for (int a = 0; a < N; a++) {
							for (int b = 0; b < N; b++) {
								lineTmp[a][b] = tmp[a][b];
							}
						}
						
						// 코어 기준으로 한쪽으로 계속 이동이 가능하다면 선 연결
						int tmpLineCnt = 0;
						while (!isWall(nx, ny) && lineTmp[nx][ny] == 0) {
							lineTmp[nx][ny] = 2;
							tmpLineCnt++;
							nx += dx[k];
							ny += dy[k];
						}
						
						// 벽까지 연결되었을때 재귀
						if (isWall(nx, ny)) connect(i, j+1, lineTmp, lineCnt+tmpLineCnt, coreCnt+1);
					}
				}
			}
			yIdx = 1;
		}
		
		if (core < coreCnt) {
			core = coreCnt;
			line = lineCnt;
		}
		if (core == coreCnt && line > lineCnt) line = lineCnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			line = Integer.MAX_VALUE;
			core = 0;
			
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && (i == 0 || i == N-1 || j == 0 || j == N-1)) core++;
				}
			}
			
			// 좌표당 코어를 찾아서 선을 놓는 경우의 수 확인
			connect(1, 1, arr, 0, core);
			
			// 출력
			sb.append("#")
				.append(t)
				.append(" ")
				.append(line)
				.append("\n");
		}
		System.out.println(sb);
	}
}