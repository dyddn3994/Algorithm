// 최대 자릿수는 6자리, 최대 교환 횟수는 10번
// 6자리 숫자를 최대로 만들려면 5번의 교환이면 최대 가능, 5번 돌려서 최대값이 만들어질때 남은 횟수가 홀수인지 아닌지 따져서
// 짝수이면 최대 가능, 홀수이면 최대에서 뒷 두자리 swap한 경우 출력

import java.io.*;
import java.util.*;

public class Solution {
	
	static StringBuilder input; // 입력값
	static int inputCnt; // 교환 횟수
	
	static int max; // 최대값
	static StringBuilder maxStr; // 최대값을 가지는 문자열
	
	static boolean isOddCnt;  // 최대값이 만들어지고 남은 횟수가 홀수/짝수가 가능한지 여부
	static boolean isEvenCnt;
	
	static void trade(StringBuilder sb, int cnt, int tradeCnt) {
		if (cnt == tradeCnt) {
			// 교환 완료
			int n = Integer.parseInt(sb.toString());
			max = Math.max(n, max);
			return;
		}

		// 최대이면 남은 카운트 저장
		if (sb.toString().equals(maxStr.toString())) {
			if ((inputCnt - cnt) % 2 == 0) {
				isEvenCnt = true;
			}
			else {
				isOddCnt = true;
			}
		}
		
		// 숫자 교환
		for (int i = 0; i < sb.length(); i++) {
			for (int j = i+1; j < sb.length(); j++) {
				StringBuilder tmp = new StringBuilder(sb);
				char t = tmp.charAt(i);
				tmp.setCharAt(i, tmp.charAt(j));
				tmp.setCharAt(j, t);
				
				trade(tmp, cnt+1, tradeCnt);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input = new StringBuilder(st.nextToken());
			inputCnt = Integer.parseInt(st.nextToken());
			max = 0;
			isEvenCnt = false;
			isOddCnt = false;

			// 생성할 수 있는 최대값 생성하여 남은 카운트로 계산
			char[] c = input.toString().toCharArray();
			Arrays.sort(c);
			maxStr = new StringBuilder(new String(c)).reverse();
			
			if (inputCnt < 5) trade(input, 0, inputCnt);
			else {
				trade(input, 0, 5);
				
				if (isEvenCnt) {
					max = Integer.parseInt(maxStr.toString());
				}
				else if (!isEvenCnt && isOddCnt) {
					int i = maxStr.length()-2;
					int j = maxStr.length()-1;
					
					char tmp = maxStr.charAt(i);
					maxStr.setCharAt(i, maxStr.charAt(j));
					maxStr.setCharAt(j, tmp);
					
					max = Integer.parseInt(maxStr.toString());
				}
				else {
					System.out.println("no");
				}
			}
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(max)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
