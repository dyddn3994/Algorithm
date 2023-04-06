import java.io.*;
import java.util.*;

class Main {

	static int N; // 행 열 크기
	static int M; // 상어 수
	static int k; // 냄새 빠지는 시간
	
	static int[][] arr; // 입력 배열, 상어는 1부터 M까지, 냄새는 -k부터 -1까지
	static int[] directions; // 상어 별 현재 방향
	static int[][][] dirPriority; // 상어 별 방향 우선순위, 상어번호/방향/우선순위

	static int sharkCnt; // 남아있는 상어 수
	static int[][] smell; // 상어 냄새 배열, 상어의 숫자 저장

	static int[] dx = { 0, -1, 1, 0, 0 }; // 조건 순서 방향벡터
	static int[] dy = { 0, 0, 0, -1, 1 };

	static boolean isWall(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) return true;
		return false;
	}

	// 선택한 상어 이동
	static void move(int x, int y) {
		// 방향 찾기
		int sharkNum = arr[x][y]; // 상어 번호
		int dir = directions[sharkNum]; // 현재 바라보고 있는 방향
		int[] sharkDirs = dirPriority[sharkNum][dir]; // 현재 상어의 방향 우선순위

		// 아무 냄새없는 위치 있으면 이동
		for (int i = 0; i < 4; i++) {
			int ndir = sharkDirs[i];
			int nx = x + dx[ndir];
			int ny = y + dy[ndir];	

			if (isWall(nx, ny) || smell[nx][ny] > 0) continue;

			directions[sharkNum] = ndir;

			// 상어가 먼저 와있으면 번호 검증하고 상어 제거
			if (arr[nx][ny] > 0) {
				if (arr[nx][ny] > sharkNum) arr[nx][ny] = sharkNum;
				else arr[x][y] = 0;

				sharkCnt--;
			}
			else {
				arr[nx][ny] = sharkNum;
			}

			arr[x][y] = -k;
			return;
		}

		// 이동 못했으면 내 냄새 찾아서 이동
		for (int j = 0; j < 4; j++) {
			int ndir = sharkDirs[j];
			int nx = x + dx[ndir];
			int ny = y + dy[ndir];	

			if (isWall(nx, ny) || arr[nx][ny] > 0) continue;
			if (smell[nx][ny] != sharkNum) continue;

			directions[sharkNum] = ndir;
			
			// 이동 및 냄새 남기기
			arr[nx][ny] = sharkNum;
			arr[x][y] = -k;
			// smell[nx][ny] = sharkNum;
			break;
		}
	}

	static void test() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", arr[i][j]);
			}
			System.out.println();
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		directions = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			directions[i] = Integer.parseInt(st.nextToken());
		}

		dirPriority = new int[M + 1][5][4];
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					dirPriority[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		smell = new int[N][N];
		sharkCnt = M;
		// 입력 끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] <= 0) continue;

				smell[i][j] = arr[i][j];
			}
		}

		int time = 1; // 걸린 시간
		for (; time <= 1000; time++) {
			boolean[] isMoved = new boolean[M + 1]; // 상어 이동 여부

			// 이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] <= 0 || isMoved[arr[i][j]]) continue; // 상어가 아니면 continue

					isMoved[arr[i][j]] = true;
					move(i, j);
				}
			}
			// System.out.println(sharkCnt);
			// 상어 1만 남으면 종료
			if (sharkCnt == 1) break;

			// 냄새 빼기 및 상어위치 냄새 남기기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0) {
						// 냄새 남기기
						// arr[i][j] = -k;
						smell[i][j] = arr[i][j];
					}
					else if (arr[i][j] < 0) {
						// 냄새 빼고 다 빠지면 smell에서도 냄새 빼기
						arr[i][j]++;
						if (arr[i][j] == 0) smell[i][j] = 0;
					}
				}
			}
			// test();
		}
		
		// 1000초 넘으면 -1출력
		if (time > 1000) time = -1;
		System.out.println(time);
    }
}