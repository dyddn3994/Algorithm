import java.io.*;
import java.util.*;

public class Solution {
	
	static int N; // 식재료 수 
	static int[][] arr;
	
	static int[] select1; // 선택한 식재료
	static int[] select2; // 선택안된 식재료
	static int res; // 궁합 차
	
	// 조합 찾기
	static void selec(int cnt, int idx) {
		if (cnt == N/2) {
			// 선택 안된 식재료 찾기
			int selectIdx = 0;
			for (int i = 0; i < N; i++) {
				int j = 0;
				for (; j < N/2; j++) {
					if (select1[j] == i) break;
				}
				if (j == N/2) select2[selectIdx++] = i;
			}
			
			// 식재료 궁합의 합 계산
			int sum1 = 0; // 선택 식재료 궁합의 합
			int sum2 = 0; // 선택안된 식재료 궁합의 합
			for (int i = 0; i < N/2; i++) {
				for (int j = i+1; j < N/2; j++) {
					sum1 += arr[select1[i]][select1[j]];
					sum1 += arr[select1[j]][select1[i]];
					sum2 += arr[select2[i]][select2[j]];
					sum2 += arr[select2[j]][select2[i]];
				}
			}
			int sub = Math.abs(sum1 - sum2);
			if (sub < res) res = sub;
			return;
		}
		
		for (int i = idx; i < N; i++) {
			select1[cnt] = i;
			selec(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = Integer.MAX_VALUE;
			
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			select1 = new int[N/2];
			select2 = new int[N/2];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			selec(0, 0);
			
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