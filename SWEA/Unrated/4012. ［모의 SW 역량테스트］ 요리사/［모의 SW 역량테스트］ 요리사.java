import java.io.*;
import java.util.*;

public class Solution {
	
	static int N; // 행 열 크기
	static int[][] arr; // 입력 배열
	
	static int sum; // 전체 합
	static int[] select; // 선택 배열
	static int res;
	
	static void selectFood(int idx, int cnt) {
		if (cnt == N/2) {
			int[] notSelect = new int[N/2];
			int notSelectIdx = 0;
			for (int i = 0; i < N; i++) {
				boolean isSelected = false;
				for (int j = 0; j < N/2; j++) {
					if (select[j] == i) {
						isSelected = true;
						break;
					}
				}
				if (isSelected) continue;
				
				notSelect[notSelectIdx++] = i;
			}
			
			int s1 = 0, s2 = 0;
			for (int i = 0; i < N/2; i++) {
				for (int j = i+1; j < N/2; j++) {
					s1 += arr[select[i]][select[j]];
					s2 += arr[notSelect[i]][notSelect[j]];
				}
			}
		
			int sub = Math.abs(s1 - s2);
			if (sub < res) res = sub;
			
			return;
		}
		
		for (int i = idx; i < N; i++) {
			select[cnt] = i;
			selectFood(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			sum = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (i > j) {
						arr[j][i] += arr[i][j];
						sum += arr[j][i];
					}
				}
			}
			
			select = new int[N/2];
			res = Integer.MAX_VALUE;
			
			selectFood(0, 0);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
