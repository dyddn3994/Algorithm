import java.io.*;
import java.util.*;

class Main {

	static int N; // 행 열 크기
	static int[][] arr; // 입력 배열

	static long[][] visited; // 0: 방문하지 않음, -1: 방문했고 목적지까지 갈 수 없음, 1이상: 방문했고 목적지까지 가는 경우의 수

	static boolean isOutOfBoundary(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return true;
		return false;
	}

	static long dfs(int x, int y) {
		// 목적지이면 1 반환
		if (x == N-1 && y == N-1) {
			return 1;
		}
		
		// 목적지가 아닌데 0이면 이동불가
		if (arr[x][y] == 0) return 0;

		long cnt = 0; // x, y에서 목적지까지 가는 경우의 수
		int dist = arr[x][y]; // 이동할 거리
		
		// 오른쪽으로 이동
		if (!isOutOfBoundary(x, y+dist)) {
			if (visited[x][y+dist] == 0) {
				cnt += dfs(x, y+dist);
			}
			else if (visited[x][y+dist] > 0) {
				cnt += visited[x][y+dist];
			}
		}

		// 아래로 이동
		if (!isOutOfBoundary(x+dist, y)) {
			if (visited[x+dist][y] == 0) {
				cnt += dfs(x+dist, y);
			}
			else if (visited[x+dist][y] > 0) {
				cnt += visited[x+dist][y];
			}
		}

		// 내 위치에서 목적지까지 가는 경우의 수 저장, 불가능하면 -1
		if (cnt == 0) visited[x][y] = -1;
		else visited[x][y] = cnt;

		return cnt;
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new long[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 원점에서 dfs 순회하면서 목적지까지 갈 수 있는지 확인, 들렀던 지점에서 원점까지 갈 수 있다면 순회 멈추고 +1
		long cnt = dfs(0, 0);
		
		// 출력
		System.out.println(cnt);
	}
}