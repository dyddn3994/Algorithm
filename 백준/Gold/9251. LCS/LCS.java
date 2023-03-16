import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		// 문자열 길이+1만큼 해당 위치의 최대 길이 부분 수열을 저장할 배열 생성
		int[][] len = new int[str1.length+1][str2.length+1];

		// 마지막 문자부터 만들 수 있는 최대 부분 수열 계산
		for (int i = str1.length-1; i >= 0; i--) {
			for (int j = str2.length-1; j >= 0; j--) {
				if (str1[i] == str2[j]) {
					// 찾는 문자와 같으면 이전 인덱스의 최대 길이 부분 수열 +1 저장
					len[i][j] = len[i+1][j+1] + 1;
				}
				else {
					// 찾는 문자와 다를때 이전 인덱스의 값과 같음. 제일 끝자리면 나를 제외한 문자열의 최대길이
					// if (j == str2.length-1) len[i][j] = len[i+1][j];
					// else len[i][j] = len[i][j+1];

					// i의 이전 인덱스와 j의 이전 인덱스의 최대길이 중 최대값
					len[i][j] = (len[i+1][j] < len[i][j+1]) ? len[i][j+1] : len[i+1][j];
				}
			}
		}

		System.out.println(len[0][0]);
	}
}