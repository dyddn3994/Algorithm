import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] arr;
	
	static int inorder(int n) {
		if (arr[n][1] == 0) return arr[n][0];
		
		if (arr[n][0] == 0) return inorder(arr[n][1]) + inorder(arr[n][2]);
		else if (arr[n][0] == 1) return inorder(arr[n][1]) - inorder(arr[n][2]);
		else if (arr[n][0] == 2) return inorder(arr[n][1]) * inorder(arr[n][2]);
		else if (arr[n][0] == 3) return inorder(arr[n][1]) / inorder(arr[n][2]);
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1][3];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				
				String inp = st.nextToken();
				if (inp.equals("+")) arr[idx][0] = 0;
				else if (inp.equals("-")) arr[idx][0] = 1;
				else if (inp.equals("*")) arr[idx][0] = 2;
				else if (inp.equals("/")) arr[idx][0] = 3;
				else arr[idx][0] = Integer.parseInt(inp);
				
				int childIdx = 1;
				while(st.hasMoreTokens()) {
					arr[idx][childIdx++] = Integer.parseInt(st.nextToken());
				}
			}
			
			int res = inorder(1);
			
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