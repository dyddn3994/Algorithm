import java.io.*;
import java.util.*;

public class Solution {
	
	static int M; // 총 이동시간
	static int A; // BC의 개수
	static int[] moveA; // A의 이동 정보  0: 이동하지않음, 1: 상, 2: 우, 3: 하, 4: 좌
	static int[] moveB; // B의 이동 정보
	static int[][] bc; // BC의 정보[A][4] 0: x좌표, 1: y좌표, 2: 충전범위: 3: 처리량
	
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	static List<Integer>[][] info; // 좌표당 닿는 전파 리스트
	static boolean[][] visited;
	
	static boolean isWall(int x, int y) {
		if (x < 0 || x >= 10 || y < 0 || y >= 10) return true;
		return false;
	}
	
	// bc당 전파가 닿는 범위 확인
	static void bfs(int x, int y, int dist, int bc, int c) {
		Deque<Integer> xq = new ArrayDeque<>();
		Deque<Integer> yq = new ArrayDeque<>();
		Deque<Integer> dq = new ArrayDeque<>();
		xq.add(x);
		yq.add(y);
		dq.add(dist);
		visited[x][y] = true;
		
		while (!xq.isEmpty()) {
			x = xq.poll();
			y = yq.poll();
			dist = dq.poll();
			
			info[x][y].add(bc);
			
			if (dist == c) continue;
			
			for (int i = 1; i <= 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (isWall(nx, ny) || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				xq.add(nx);
				yq.add(ny);
				dq.add(dist+1);
			}
		}
	}
	
	// 두 위치에 대해 충전량 합 구하기
	static int getCharge(List<Integer> apos, List<Integer> bpos) {
		int sum = 0;
		
		int asize = apos.size();
		int bsize = bpos.size();
		
		if (asize == 0 && bsize == 0) sum = 0;
		else if (asize == 0) sum = bc[bpos.get(0)][3];
		else if (bsize == 0) sum = bc[apos.get(0)][3];
		else if (asize == 1 && bsize == 1) {
			// 둘다 하나씩일때 같은 bc면 반씩 나누어 가짐, 다르면 총합
			int aBC = apos.get(0);
			int bBC = bpos.get(0);
			if (aBC == bBC) sum = bc[aBC][3];
			else {
				sum = bc[aBC][3];
				sum += bc[bBC][3];
			}
		}
		else if (asize == 1 && bsize > 1) {
			// 한쪽은 한개, 한쪽은 여러개이면 여러개인 쪽에서 최대값이 한개쪽과 같을때 두번째로 큰 값으로 처리 
			int aBC = apos.get(0);
			int bBC = bpos.get(0);
			
			if (aBC == bBC) bBC = bpos.get(1);
			
			sum = bc[aBC][3];
			sum += bc[bBC][3];
		}
		else if (asize > 1 && bsize == 1) {
			int aBC = apos.get(0);
			int bBC = bpos.get(0);
			
			if (aBC == bBC) aBC = apos.get(1);
			
			sum = bc[aBC][3];
			sum += bc[bBC][3];
		}
		else if (asize > 1 && bsize > 1) {
			// 최대값 비교해서 같으면 두번째값 비교해서 더 큰거 선택
			int aBC = apos.get(0);
			int bBC = bpos.get(0);
			
			if (aBC == bBC) {
				int aBC2 = apos.get(1);
				int bBC2 = bpos.get(1);
				
				if (bc[aBC2][3] < bc[bBC2][3]) bBC = bBC2;
				else aBC = aBC2;
			}
			
			sum = bc[aBC][3];
			sum += bc[bBC][3];
		}
		
		return sum;
	}
	
	// 이동 시작
	static int start() {
		int ax = 0, ay = 0, bx = 9, by = 9;
		List<Integer> apos = info[ax][ay];
		List<Integer> bpos = info[bx][by];
		
		int sum = getCharge(apos, bpos);
		
		for (int i = 0; i < M; i++) {
			int am = moveA[i];
			int bm = moveB[i];
			
			ax += dx[am];
			ay += dy[am];
			bx += dx[bm];
			by += dy[bm];
			
			apos = info[ax][ay];
			bpos = info[bx][by];
			
			sum += getCharge(apos, bpos);
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			info = new List[10][10];
			
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			moveA = new int[M];
			moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) moveB[i] = Integer.parseInt(st.nextToken());
			
			bc = new int[A+1][4];
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				bc[i][0] = Y-1; bc[i][1] = X-1; bc[i][2] = C; bc[i][3] = P;
			}
			// 입력 끝
			
			// info 배열 리스트 할당
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					info[i][j] = new ArrayList<>();
				}
			}
			
			// BC당 충전 범위 확인
			for (int i = 1; i <= A; i++) {
				visited = new boolean[10][10]; 
				int x = bc[i][0];
				int y = bc[i][1];
				int c = bc[i][2];
				bfs(x, y, 0, i, c);
			}
			
			// 전파 세기에 따라 리스트 내림차순 정렬
			for (int i = 0; i < 10; i++) {
				for (int j = 0 ; j < 10; j++) {
					Collections.sort(info[i][j], (a, b) -> bc[b][3] - bc[a][3]);
				}
			}
			
			// A와 B 동시에 이동하면서 충전
			int res = start();
			
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