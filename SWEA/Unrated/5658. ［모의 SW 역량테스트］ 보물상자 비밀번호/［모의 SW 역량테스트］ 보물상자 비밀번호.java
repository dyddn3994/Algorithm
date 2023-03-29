import java.io.*;
import java.util.*;

class Solution {
	
	static int N; // 숫자 개수
	static int K; // K번째로 큰 수 찾기
	static String input; // 입력 문자열

	static List<String> list; // 생성 가능한 숫자 저장 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			input = br.readLine();
			
			int numSize = N / 4;
			list = new ArrayList<>();
			
			// N / 4회 숫자 생성
			for (int i = 0; i < numSize; i++) {
				String str = input.substring(i, input.length()) + input.substring(0, i);
				
				for (int j = 0; j < 4; j++) {
					String substr = str.substring(j * numSize, j * numSize + numSize);
					
					// 리스트에 없는 값이면 입력
					if (list.contains(substr)) continue;
					list.add(substr);
				}
			}
			
			// 리스트 역순 정렬 및 16진수 10진수로 변환
			Collections.sort(list, Collections.reverseOrder());
			String selectHex = "0x" + list.get(K - 1);
			int res = Integer.decode(selectHex);

			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
