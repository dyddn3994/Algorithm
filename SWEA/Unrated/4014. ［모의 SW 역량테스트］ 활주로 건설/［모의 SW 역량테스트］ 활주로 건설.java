import java.io.*;
import java.util.*;

class Solution {
	
	static int N; // 지형 크기
	static int X; // 경사로 길이
	static int[][] arr; // 입력 배열
	
	static int res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
		
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 0;
			
			// 행, 열 별로 확인해서 x칸 이상 동일한 값 이후 다음칸이 +1인지, 다음칸이 -1이면 x칸이상 동일한 칸이 있는지 확인
			for (int i = 0; i < N; i++) {
				// 행
				int sameCnt = 1; // 같은 높이의 경사로가 없는 연속된 지형 수
				int j = 0;
				for (; j < N - 1; j++) {
					if (arr[i][j] == arr[i][j + 1]) {
						// 같은 값
						sameCnt++;
					}
					else if (arr[i][j] + 1 == arr[i][j + 1]) {
						// 한칸 증가
						if (sameCnt < X) break; // 경사로 길이에 부족
						sameCnt = 1;
					}
					else if (arr[i][j] - 1 == arr[i][j + 1]) {
						// 한칸 감소
						j++;
						
						int k = 0;
						for (; k < X - 1; k++) {
							// X칸만큼 같은 값이 있는지 확인
							if (j + 1 >= N || arr[i][j] != arr[i][j + 1]) break;
							j++;
						}
						if (k == X - 1) {
							sameCnt = 0;
							j--;
						}
						else {
							j = N;
							break;
						}
					}
					else break;
				}
				if (j == N - 1) res++;
				
				// 열
				sameCnt = 1; // 같은 높이의 경사로가 없는 연속된 지형 수
				j = 0;
				for (; j < N - 1; j++) {
					if (arr[j][i] == arr[j + 1][i]) {
						// 같은 값
						sameCnt++;
					}
					else if (arr[j][i] + 1 == arr[j + 1][i]) {
						// 한칸 증가
						if (sameCnt < X) break; // 경사로 길이에 부족
						sameCnt = 1;
					}
					else if (arr[j][i] - 1 == arr[j + 1][i]) {
						// 한칸 감소
						j++;
						
						int k = 0;
						for (; k < X - 1; k++) {
							// X칸만큼 같은 값이 있는지 확인
							if (j + 1 >= N || arr[j][i] != arr[j + 1][i]) break;
							j++;
						}
						if (k == X - 1) {
							sameCnt = 0;
							j--;
						}
						else {
							j = N;
							break;
						}
					}
					else break;
				}
				if (j == N - 1) res++;
			}
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
