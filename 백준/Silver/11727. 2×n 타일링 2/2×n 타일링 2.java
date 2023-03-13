import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+2];
		arr[1] = 1; arr[2] = 3;
		for (int i = 3; i <= n; i++) {
			arr[i] = (arr[i-1] + arr[i-2]*2) % 10_007;
		}

		System.out.println(arr[n]);
	}
}