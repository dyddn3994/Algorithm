import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    // 입력
		int x = Integer.parseInt(br.readLine());

		int[] arr = new int[x+4]; // 연산 횟수 배열
		arr[1] = 0; arr[2] = 1; arr[3] = 1;
		for (int i = 4; i <= x; i++) {
			int min = arr[i-1]; // 3나눈값, 2나눈값, 1뺀값 중 최솟값 가져오기
			if (i % 3 == 0) min = (min < arr[i/3]) ? min : arr[i/3];
			if (i % 2 == 0) min = (min < arr[i/2]) ? min : arr[i/2];

			arr[i] = min+1;
		}

		System.out.println(arr[x]);
	}
}