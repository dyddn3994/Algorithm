import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[] operator;
	static int[] operand;

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] pos;
	
	static void calculate(int cnt) {
		if (cnt == N-1) {
			int prevVal = operand[0];
			for (int i = 0; i < N-1; i++) {
				if (pos[i] == 0) prevVal += operand[i+1];
				else if (pos[i] == 1) prevVal -= operand[i+1];
				else if (pos[i] == 2) prevVal *= operand[i+1];
				else if (pos[i] == 3) prevVal /= operand[i+1];
			}
			if (max < prevVal) max = prevVal;
			if (min > prevVal) min = prevVal;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				pos[cnt] = i;
				calculate(cnt+1);
				operator[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			operator = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			operand = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				operand[i] = Integer.parseInt(st.nextToken());
			}
			pos = new int[N-1];
			
			calculate(0);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(max - min)
				.append("\n");
		}
		System.out.println(sb);
	}
}