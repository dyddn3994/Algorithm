import java.io.*;
import java.util.*;

class Main {

	static int M; // 행
	static int N; // 열
	static int[][] arr; // 입력 배열

	static int[][] visited; // 해당 위치에서 도착지까지 갈 수 있는 경우의 수, 방문하지 않았으면 -1
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static boolean isWall(int nx, int ny) {
		if (nx < 0 || nx >= M || ny < 0 || ny >= N) return true;
		return false;
	}

	static int dfs(int x, int y) {
		if (x == M - 1 && y == N - 1) {
			// 도착지일때
			return 1;
		}

		int cnt = 0; // 해당 위치에서 도착지까지 갈 수 있는 경우의 수

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (isWall(nx, ny) || arr[nx][ny] >= arr[x][y]) continue;

			// 방문하지 않았으면 방문해서 도착지까지의 경우의수 배열에 저장
			if (visited[nx][ny] == -1) visited[nx][ny] = dfs(nx, ny);
			cnt += visited[nx][ny];
		}

		return cnt;
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[M][N];
		visited = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				visited[i][j] = -1; // 방문하지 않은곳 -1로 초기화
			}
		}

		int res = dfs(0, 0);

		System.out.println(res);
    }
}