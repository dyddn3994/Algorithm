import java.io.*;
import java.util.*;

class Solution {
	
	static int n; // 행 열 크기
	static int[][] arr; // 입력 배열
	
	static List<Integer> rows; // 행 저장 리스트
	static List<Integer> cols; // 열 저장 리스트
	static List<Integer> muls; // 행열 곱셈 저장 리스트
	
	// x, y를 기준으로 행렬 크기 계산
	static void getProc(int x, int y) {
		int row = 0;
		int col = 0;
		
		while (arr[x][y] > 0) {
			// 행 계산	
			row++;
			x++;
		}
		x--;
		
		while (arr[x][y] > 0) {
			// 열 계산
			col++;
			y++;
		}
		
		// 곱셉 오름차순 리스트 순회
		int mul = row * col;
		for (int i = 0; i < muls.size(); i++) {
			if (mul < muls.get(i) || (mul == muls.get(i) && row < rows.get(i))) {
				rows.add(i, row);
				cols.add(i, col);
				muls.add(i, mul);
				return;
			}
		}
		
		// 가장 큰 경우 끝에 삽입
		rows.add(row);
		cols.add(col);
		muls.add(mul);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			n = Integer.parseInt(br.readLine());
			arr = new int[n+2][n+2];
			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rows = new ArrayList<>();
			cols = new ArrayList<>();
			muls = new ArrayList<>();
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (arr[i][j] == 0) continue;
					else if (arr[i-1][j] > 0 || arr[i][j-1] > 0) continue;
					
					getProc(i, j);
				}
			}
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(rows.size())
				.append(" ");
			
			for (int i = 0; i < rows.size(); i++) {
				sb.append(rows.get(i))
					.append(" ")
					.append(cols.get(i))
					.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
