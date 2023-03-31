import java.io.*;
import java.util.*;

class Solution {
	
	static int D; // 보호 필름 두께
	static int W; // 보호 필름 가로크기
	static int K; // 합격 기준
	static int[][] arr; // 입력 배열
	
	static int res;
	
	// 성능검사를 통과할 수 있는지
	static boolean isValid() {
		for (int i = 0; i < W; i++) {
			int prevCell = arr[0][i]; // 이전 셀의 특성
			int sameCellCnt = 1; // 연속된 같은 셀 개수
			
			int j = 1;
			for (; j < D; j++) {
				if (arr[j][i] == prevCell) sameCellCnt++;
				else {
					prevCell = arr[j][i];
					sameCellCnt = 1;
				}
				
				if (sameCellCnt == K) break;
			}
			if (j == D) return false;
		}
		
		return true;
	}

	// 인덱스 행 값 변경
	static void changeRow(int idx, int n) {
		for (int i = 0; i < W; i++) {
			arr[idx][i] = n;
		}
	}
	
	// 약품 주입 조합
	static boolean addMed(int cnt, int select, int idx) {
		if (cnt == select) {
			if (isValid()) {
				res = cnt;
				return true;
			}
			else return false;
		}
		
		for (int i = idx; i < D; i++) {
			int[] tmp = arr[i].clone();
			changeRow(i, 0);
			if (addMed(cnt, select + 1, i + 1)) return true;
			changeRow(i, 1);
			if (addMed(cnt, select + 1, i + 1)) return true;
			arr[i] = tmp;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 0;
			
			for (int i = 0; i < D; i++) {
				if (addMed(i, 0, 0)) break;
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
