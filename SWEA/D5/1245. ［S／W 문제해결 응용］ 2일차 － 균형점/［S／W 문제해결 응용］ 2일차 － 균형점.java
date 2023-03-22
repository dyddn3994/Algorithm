import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static double[] X;
	static int[] M;
	
	static double cal(double d, int m) {
		return m / (d * d);
	}
	
	static double solve(double l, double r) {
		double mid;
		
		while (true) {
			mid = (l + r) / 2.0;
			
			double lf = 0; // 좌측 f합
			double rf = 0; // 우측 f합
			
			for (int i = 0; i < N; i++) {
				double x = X[i];
				int m = M[i];
				
				double F = cal(mid - x, m);
				
				if (mid > x) lf += F;
				else if (mid < x) rf += F;
			}
			
			if (lf < rf) r = mid;
			else l = mid;
			
			if (r - l < 1e-12) break;
		}
		
		return mid;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			X = new double[N];
			M = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정렬
			for (int i = 0; i < N; i++) {
				int min = i;
				for (int j = i+1; j < N; j++) {
					if (X[min] > X[j]) min = j;
				}
				double tmp1 = X[min];
				X[min] = X[i];
				X[i] = tmp1;
				int tmp2 = M[min];
				M[min] = M[i];
				M[i] = tmp2;
			}
			
			// 균형점 찾기
			StringBuilder res = new StringBuilder();
			for (int i = 0; i < N-1; i++) {
				res.append(String.format("%.10f", solve(X[i], X[i+1]))).append(" ");
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
