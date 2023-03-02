import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			int check = 1;
			// 입력
			N = Integer.parseInt(br.readLine());
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if (check == 0) continue; // 이미 불가능한 노드를 발견하면 입력만 받고 추가 동작 없음
				int idx = Integer.parseInt(st.nextToken());
				
				String inp = st.nextToken();
				if (inp.equals("+") || inp.equals("-") || inp.equals("*") || inp.equals("/")) {
					if (!st.hasMoreTokens()) check = 0;
					else {
						while(st.hasMoreTokens()) {
							int child = Integer.parseInt(st.nextToken());
							if (child > N) check = 0;
						}
					}
				}
				else {
					if (Integer.parseInt(inp) > N || st.hasMoreTokens()) check = 0;
				}
			}
			
			// 출력
			sb.append("#")
				.append(t)
				.append(" ")
				.append(check)
				.append("\n");
		}
		System.out.println(sb);
	}
}