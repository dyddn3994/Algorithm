import java.io.*;
import java.util.*;

public class Solution{
	static boolean isN = false;
	static boolean isS = false;
	static int check(int n) {
		if (!isN && !isS) {
			if (n == 1) {
				isN = true;
			}
			else if (n == 2) {
				isS = true;
			}
			return 0;
		}
		else if (n == 1 && isS) {
			isN = true;
			isS = false;
			return 0;
		}
		else if (n == 2 && isN) {
			isS = true;
			isN = false;
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[100][100];
		for (int t = 1; t <= 10; t++) {
			int len = Integer.parseInt(br.readLine());
			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < len; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			for (int i = 0; i < len; i++) {
				isN = false;
				isS = false;
				for (int j = 0; j < len; j++) {
					if (arr[j][i] > 0) {
						cnt += check(arr[j][i]);
					}
				}
			}
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(cnt)
				.append("\n");
		}
		System.out.println(sb);
	}
}