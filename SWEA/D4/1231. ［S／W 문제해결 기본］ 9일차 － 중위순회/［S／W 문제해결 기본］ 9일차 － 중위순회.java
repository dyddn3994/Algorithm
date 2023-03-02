import java.io.*;
import java.util.*;

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static String[] arr;
	
	static void inorder(int n) {
		if (N >= n*2) inorder(n*2);
		sb.append(arr[n]);
		if (N >= n*2+1) inorder(n*2+1);
	}
	
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new String[N+1];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String text = st.nextToken();
				arr[idx] = text;
				while(st.hasMoreTokens()) {
					int child = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#")
				.append(t)
				.append(" ");
			inorder(1);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}