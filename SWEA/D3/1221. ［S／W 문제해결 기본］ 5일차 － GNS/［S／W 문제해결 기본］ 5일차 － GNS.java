import java.io.*;
import java.util.*;

public class Solution {
	
	static String[] NUMS = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String tc = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			
			
			// 단어별 개수 세기
			int[] cnt = new int[10]; // 숫자 개수 저장 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				String word = st.nextToken();
				
				for (int j = 0; j < 10; j++) {
					if (word.equals(NUMS[j])) {
						cnt[j]++;
						break;
					}
				}
			}
			
			// 출력 문장 생성
			StringBuilder res = new StringBuilder(); // 문장 결과
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < cnt[i]; j++) {
					res.append(NUMS[i])
						.append(" ");
				}
			}
			
			sb.append(tc)
				.append("\n")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
