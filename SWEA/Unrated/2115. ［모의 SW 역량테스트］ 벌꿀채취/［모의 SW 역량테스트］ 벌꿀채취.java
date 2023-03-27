import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M, C; // 벌통 크기, 선택 가능한 벌통 수, 채취 가능한 꿀 최대 양
	static int[][] arr; // 입력 배열
	static int[][] honey; // M개 우측값 채취 가능 최대 꿀 양 계산
	
	static int res;
	
	// x, y에서 honey배열 저장
	static void getHoney(int x, int y) {
		int sum = 0;
		int powSum = 0;
		for (int i = 0; i < M; i++) {
			sum += arr[x][y+i];
			powSum += arr[x][y+i] * arr[x][y+i];
		}
		
		if (sum <= C) honey[x][y] = powSum;
		else honey[x][y] = selectHoney(x, y, y+M, 0, 0);
	}
	
	// 조합으로 채취 가능한 꿀 찾기
	// h : 선택한 꿀의 합 / ph : 선택한 꿀의 제곱 합
	static int selectHoney(int x, int y, int ym, int h, int ph) {
		int ret = 0;
		
		for (int i = y; i < ym; i++) {
			int nh = arr[x][i];
			int nph = arr[x][i] * arr[x][i];
			
			if (h + nh > C) continue;
			
			ret = Math.max(ret, selectHoney(x, i+1, ym, h + nh, ph + nph));
		}
		
		if (ret == 0) return ph;
		else return ret;
	}
	
	// 최대값 행에서 조합
	static void getMax(int max1Idx, int max2Idx, int cnt, int idx1, int idx2, int sum) {
		if (cnt == 2) {
			res = Math.max(res, sum);
			return;
		}
		
		for (int i = idx1; i < honey[max1Idx].length; i++) {
			getMax(max1Idx, max2Idx, cnt+1, i+M, 0, sum+honey[max1Idx][i]);
		}
		for (int i = idx2; i < honey[max2Idx].length; i++) {
			getMax(max1Idx, max2Idx, cnt+1, honey[max1Idx].length, i+M, sum+honey[max2Idx][i]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 제곱 합 계산
			honey = new int[N][N-M+1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N-M+1; j++) {
					getHoney(i, j);
				}
			}
			
			// 각 행별로 최대값, 두번째 최대값 가진 행 선택
			int max1 = 0, max1Idx = 0;
			int max2 = 0, max2Idx = 0;
			for (int i = 0; i < N; i++) {
				int rowMax = 0;
				for (int j = 0; j < honey[i].length; j++) {
					rowMax = Math.max(rowMax, honey[i][j]);
				}
				
				if (rowMax > max1) {
					max2 = max1;
					max2Idx = max1Idx;
					
					max1 = rowMax;
					max1Idx = i;
				}
				else if (rowMax > max2) {
					max2 = rowMax;
					max2Idx = i;
				}
			}
			
			// 선택한 두 행에서 조합으로 최대 수익 찾기
			res = 0;
			getMax(max1Idx, max2Idx, 0, 0, 0, 0);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
