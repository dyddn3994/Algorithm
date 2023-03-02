import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[100][100];
		for (int t = 1; t <= 10; t++) {
			int c = Integer.parseInt(br.readLine());
			int max = 0;
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 100; i++) {
				sum = 0;
				for (int j = 0; j < 100; j++) {
					sum += arr[i][j];
				}
				if (max < sum) max = sum;
			}
			for (int i = 0; i < 100; i++) {
				sum = 0;
				for (int j = 0; j < 100; j++) {
					sum += arr[j][i];
				}
				if (max < sum) max = sum;
			}
			sum = 0;
			for (int i = 0; i < 100; i++) {
				sum += arr[i][i];
			}
			if (max < sum) max = sum;
			sum = 0;
			for (int i = 0; i < 100; i++){
				sum += arr[i][99-i];
			}
			if (max < sum) max = sum;
			sb.append("#")
				.append(c)
				.append(" ")
				.append(max)
				.append("\n");
		}
		System.out.println(sb);
	}
}