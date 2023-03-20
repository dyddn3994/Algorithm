import java.io.*;
import java.util.*;

public class Solution {
	
	static String[] codes = {
		"0001101", "0011001", "0010011", "0111101", "0100011", 
		"0110001", "0101111", "0111011", "0110111", "0001011"
	};
	
	static char[] hex = {
		'0', '1', '2', '3', '4', 
		'5', '6', '7', '8', '9', 
		'A', 'B', 'C', 'D', 'E', 'F'
	};
	
	static String[] binaries = {
		"0000", "0001", "0010", "0011", "0100", 
		"0101", "0110", "0111", "1000", "1001", 
		"1010", "1011", "1100", "1101", "1110", "1111"
	};
	
	// 16진수 -> 2진수 변경
	static String hexToBinary(char c) {
		int idx = 0;
		for (; idx < 16; idx++) {
			if (c == hex[idx]) break;
		}
		
		return binaries[idx];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			List<String> checkedCodes = new ArrayList<>(); // 동일한 코드 처리를 위한 문자열
			int res = 0; // 결과 출력
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 가로 크기
			
			String zeros = ""; // 0으로만 구성된 빈 공간
			zeros = String.format("%-" + M +"s", zeros).replace(" ", "0");
			for (int tc = 0; tc < N; tc++) {
				String input = br.readLine(); // 코드 입력
				if (input.equals(zeros)) continue; // 0으로만 구성되어 있으면 continue
				
				char[] inputArr = input.toCharArray();
				
				// 16진수 -> 2진수
				StringBuilder binaryCode = new StringBuilder();
				for (int i = 0; i < inputArr.length; i++) {
					binaryCode.append(hexToBinary(inputArr[i]));
				}

				int idx = binaryCode.length() - 1;
				while (idx >= 0) {

					// 끝자리가 1인 위치 찾기
					for (; idx >= 0; idx--) {
						if (binaryCode.charAt(idx) == '1') break;
					}
					if (idx < 0) break;
					
					// 굵기 찾기 : 맨 뒤에서 만나는 1부터 0을 사이에 둔 기준으로 만나는 세번째 1까지의 길이로 계산
					int lastIdx = idx;
					for (int i = 0; i < 2; i++) {
						while (binaryCode.charAt(idx) == '1') {
							idx--;
						}
						while (binaryCode.charAt(idx) == '0') {
							idx--;
						}
					}
					int depth = (lastIdx - idx) / 7; // 굵기
					
					// 동일한 코드를 이미 확인했으면 넘어감
					boolean isSame = false;
					String subCode = binaryCode.substring(lastIdx + 1 - 7 * depth * 8, lastIdx+1);
					for (String code : checkedCodes) {
						if (code.equals(subCode)) {
							isSame = true;
							break;
						}
					}
					if (isSame) {
						idx = lastIdx + 1 - 7 * depth * 8;
						continue;
					}
					checkedCodes.add(subCode);
					
					// i부터 7개 숫자 받아서 복호화
					idx = lastIdx - 7 * depth + 1;
					int[] decCode = new int[8];
					for (int i = 0; i < 8; i++) {
						// 암호코드 굵기 1로 변환
						String substrWithDepth = binaryCode.substring(idx, idx+7*depth);
						StringBuilder substr = new StringBuilder();
						for (int j = 0; j < substrWithDepth.length(); j++) {
							if (j % depth == 0) substr.append(substrWithDepth.charAt(j));
						}
						
						for (int j = 0; j < 10; j++) {
							if (substr.toString().equals(codes[j])) {
								decCode[i] = j;
								break;
							}
						}
						
						idx -= 7 * depth;
					}
					idx += 7 * depth - 1;
					
					// 암호 검증
					int verifCode = 0;
					int sum = 0;
					for (int i = 0; i < 8; i++) {
						if (i % 2 == 0) verifCode += decCode[i];
						else verifCode += 3 * decCode[i];
						
						sum += decCode[i];
					}
					if (verifCode % 10 != 0) sum = 0;
					
					res += sum;
				}
				
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
