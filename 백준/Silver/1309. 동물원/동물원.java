// i번째 없는 경우 + i번째 한쪽만 있으면 각자 대치되는 반대쪽이 있다 생각하면 arr[i-2] * 2, 대치되는 반대의 상황에서 i-1번째가 없을때면 양쪽 다 되니까 arr[i-2]

import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int N = Integer.parseInt(br.readLine()); // 우리 세로 크기
		int[] arr = new int[N+1]; // i번째값은 i번째까지 호랑이가 들어갈 수 있는 경우의 수

		arr[0] = 1; arr[1] = 3;

		for (int i = 2; i <= N; i++) {
			arr[i] = (arr[i-1] * 2 + arr[i-2]) % 9901;
		}
		
		System.out.println(arr[N]);
	}
}