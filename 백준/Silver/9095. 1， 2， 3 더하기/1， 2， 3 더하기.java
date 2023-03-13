import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 11까지의 경우의 수 배열로 구해놓기
		int[] arr = new int[10+1];
		arr[1] = 1; arr[2] = 2; arr[3] = 4;
		for (int i = 4; i <= 10; i++) {
			arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
		}

	    // 입력
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(arr[n])
				.append("\n");
		}

		System.out.println(sb);
	}
}