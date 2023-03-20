import java.io.*;
import java.util.*;

public class Solution {
	
	static String[] codes = {
			"0001101", "0011001", "0010011", "0111101", "0100011", "0110001",
			"0101111", "0111011", "0110111", "0001011"
			};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			List<String> checkedCodes = new ArrayList<>(); // 동일한 코드 처리를 위한 문자열
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 가로 크기
			
			String zeros = ""; // 0으로만 구성된 빈 공간
			zeros = String.format("%-" + M +"s", zeros).replace(" ", "0");
			for (int tc = 0; tc < N; tc++) {
				String input = br.readLine(); // 코드 입력
				if (input.equals(zeros)) continue; // 0으로만 구성되어 있으면 continue
				
				// 동일한 코드를 이미 확인했으면 넘어감
				boolean isSame = false;
				for (String code : checkedCodes) {
					if (code.equals(input)) {
						isSame = true;
						break;
					}
				}
				if (isSame) continue;
				
				checkedCodes.add(input);
				
				// 끝자리가 1인 위치 찾기
				int idx = input.length() - 1;
				for (; idx >= 0; idx--) {
					if (input.charAt(idx) == '1') break;
				}
				idx -= 6;
				
				// i부터 7개 숫자 받아서 복호화
				int[] decCode = new int[8];
				for (int i = 0; i < 8; i++) {
					String substr = input.substring(idx, idx+7);
					for (int j = 0; j < 10; j++) {
						if (substr.equals(codes[j])) {
							decCode[i] = j;
							break;
						}
					}
					
					idx -= 7;
				}
				
				// 암호 검증
				int verifCode = 0;
				int sum = 0;
				for (int i = 0; i < 8; i++) {
					if (i % 2 == 0) verifCode += decCode[i];
					else verifCode += 3 * decCode[i];
					
					sum += decCode[i];
				}
				if (verifCode % 10 != 0) sum = 0;
				
				sb.append("#")
					.append(t)
					.append(" ")
					.append(sum)
					.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
