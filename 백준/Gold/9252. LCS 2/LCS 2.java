import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int[][] len = new int[str1.length+1][str2.length+1]; // 해당 위치에서 만들 수 있는 최대 LCS 길이
		
		for (int i = str1.length-1; i >= 0; i--) {
			for (int j = str2.length-1; j >= 0 ; j--) {
				if (str1[i] == str2[j]) {
					// 끝 단어가 같을때 각 인덱스 한칸 아래의 최대 LCS길이 +1
					len[i][j] = len[i+1][j+1] + 1;
				}
				else {
					// 끝 단어가 다를때 인덱스 한칸 아래의 최대 LCS 길이 중 최대값
					if (len[i+1][j] < len[i][j+1]) {
						len[i][j] = len[i][j+1];
					}
					else {
						len[i][j] = len[i+1][j];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder(); // 결과 출력
		int i = 0, j = 0;
		while (i < str1.length && j < str2.length) {
			while (i < str1.length && len[i][j] == len[i+1][j]) i++;
			while (j < str2.length && len[i][j] == len[i][j+1]) j++;
			
			if (i < str1.length) sb.append(str1[i]);
			i++; 
			j++;
		}
		

		System.out.println(len[0][0]);
		if (len[0][0] > 0) System.out.println(sb);
	}
} 