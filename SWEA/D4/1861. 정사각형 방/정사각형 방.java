import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] arr;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int max = Integer.MIN_VALUE; // 출력할 최대 이동가능거리
	static int maxStart = Integer.MAX_VALUE; // 최대 이동가능거리의 시작점
	
	// 이동 가능 위치 있으면 이동, 횟수 세서 max값과 비교
	static void move(int x, int y) {
		int cnt = 1; // 이동 횟수
		int startX = x; // 이동 시작점 x
		int startY = y; // 이동 시작점 y
		
		while (true) {
			int i = 0;
			for (; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (arr[nx][ny] == arr[x][y] + 1) {
					x = nx;
					y = ny;
					cnt++;
					break;
				}
			}
			if (i == 4) break; // 4방향 확인 후 이동 불가능하면 반복 종료
		}
		
		// 최대값 비교
		if (max < cnt) {
			max = cnt;
			maxStart = arr[startX][startY];
		}
		else if (max == cnt) {
			if (maxStart > arr[startX][startY]) {
				maxStart = arr[startX][startY];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			max = Integer.MIN_VALUE;
			maxStart = Integer.MAX_VALUE;
			
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N+2][N+2];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 이중for 순회하면서 i, j부터 이동 시작
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					move(i, j);
				}
			}
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(maxStart)
				.append(" ")
				.append(max)
				.append("\n");
		}
		System.out.println(sb);
	}
}