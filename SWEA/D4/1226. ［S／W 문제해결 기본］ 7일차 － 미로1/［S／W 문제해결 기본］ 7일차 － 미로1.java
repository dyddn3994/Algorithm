import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[16][16];
		
		for (int t = 1; t <= 10; t++) {
			Deque<Point> q = new ArrayDeque<>();
			
			// 입력
			int tc = Integer.parseInt(br.readLine());
			for (int i = 0; i < 16; i++) {
				String inp = br.readLine();
				for (int j = 0; j < 16; j++) {
					arr[i][j] = inp.charAt(j)-'0';
				}
			}
			
			int canReach = 0; // 목적지 도달 여부
			q.add(new Point(1, 1));
			while (!q.isEmpty()) {
				if (canReach == 1) break;
				
				Point p = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if (arr[nx][ny] == 3) {
						canReach = 1;
						break;
					}
					if (arr[nx][ny] == 0) {
						arr[nx][ny] = 4;
						q.add(new Point(nx, ny));
					}
				}
			}
			
			// 출력
			sb.append("#")
				.append(tc)
				.append(" ")
				.append(canReach)
				.append("\n");
		}
		System.out.println(sb);
	}
}
class Point {
	int x;
	int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}