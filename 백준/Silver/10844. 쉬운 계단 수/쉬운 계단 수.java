import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 // 0 ~ 9까지의 숫자를 N까지 계산하면서 자릿수마다 가능한 경우의 수 구하기
		int[] arr = new int[10];
		arr[0] = 0;
		for (int i = 1; i < 10; i++) arr[i] = 1;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 2; i <= N; i++) {
			// 자릿수 별 계산
			int[] tmp = new int[10];

			tmp[0] = arr[1];
			for (int j = 1; j <= 8; j++) {
				tmp[j] = (arr[j-1] + arr[j+1])% 1_000_000_000;
			}
			tmp[9] = arr[8];

			arr = tmp;
		}
		
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + arr[i]) % 1_000_000_000;
		}
		System.out.println(sum);
	}
}