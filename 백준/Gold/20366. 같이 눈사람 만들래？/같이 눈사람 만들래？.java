import java.io.*;
import java.util.*;

class Main {
	
	static int N; // 눈덩이 개수
	static int[] arr; // 눈덩이 배열

	static int[][] sum; // 두개 눈덩이의 합 배열 
	static int sumCnt;
	static int res;
	
	static void comb(int idx, int snowCnt, int snowSum, int[] selectIdx) {
		if (snowCnt == 2) {
			// 선택된 눈덩이 배열에 저장
			sum[sumCnt][0] = snowSum;
			sum[sumCnt][1] = selectIdx[0];
			sum[sumCnt++][2] = selectIdx[1];
			
			return;
		}

		for (int i = idx; i < N; i++) {
			selectIdx[snowCnt] = i;
			comb(i + 1, snowCnt + 1, snowSum + arr[i], selectIdx);
		}
	}
	
	// 같은 눈덩이를 사용했는지
	static boolean isSameSnow(int[] s1, int[] s2) {
		if (s1[1] == s2[1] || s1[1] == s2[2] || s1[2] == s2[1] || s1[2] == s2[2]) return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		sum = new int[200_000][3];
		res = Integer.MAX_VALUE;
		
		int[] selectIdx = new int[2];
		comb(0, 0, 0, selectIdx);
		
		Arrays.sort(sum, (o1, o2) -> {
			if (o1[0] == 0 || o2[0] == 0) return 0;
			return o1[0] - o2[0];
		});
		
		for (int i = 0; i < sumCnt - 1; i++) {
			int[] nextNum = sum[i + 1];
			int j = 2;
			while (isSameSnow(sum[i], nextNum) && i + j < sumCnt) {
				nextNum = sum[i + j];
				j++;
			}
			
			if (i + j == sumCnt) continue;
			
			res = Math.min(res, nextNum[0] - sum[i][0]);
			
			if (res == 0) break;
		}
		
		System.out.println(res);
	}
}