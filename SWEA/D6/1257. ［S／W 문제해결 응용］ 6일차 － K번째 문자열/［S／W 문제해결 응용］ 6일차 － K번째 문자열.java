import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			// 접미어 문자열 배열 생성
			String[] substr = new String[str.length()];
			for (int i = 0; i < substr.length; i++) {
				substr[i] = str.substring(i);
			}
			Arrays.sort(substr);

			// LCP 생성
			int[] lcp = new int[substr.length];
			lcp[0] = 0;
			for (int i = 1; i < substr.length; i++) {
				int minLength = Math.min(substr[i-1].length(), substr[i].length()); // 두 문자열 중 최소 길이
				
				int j = 0;
				for (; j < minLength; j++) {
					if (substr[i-1].charAt(j) != substr[i].charAt(j)) {
						break;
					}
				}
				lcp[i] = j;
			}
			
			int idx = 0; // 현재 찾는 문자열 순서
			String res = ""; // 결과 부분 문자열
			for (int i = 0; i < substr.length; i++) {
				if (idx + substr[i].length() - lcp[i] >= K) {
					res = substr[i].substring(0, K - idx + lcp[i]);
					break;
				}
				idx += substr[i].length() - lcp[i];
			}
			if (res.length() == 0) res = "none";
			
//			for (int i = 0; i < substr.length; i++) {
//				System.out.println(substr[i]);
//			}
//			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
