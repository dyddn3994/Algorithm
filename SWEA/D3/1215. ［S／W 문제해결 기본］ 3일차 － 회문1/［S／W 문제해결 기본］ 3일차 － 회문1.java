import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[][] arr = new String[8][8];
		for (int t = 1; t <= 10; t++) {
			int len = Integer.parseInt(br.readLine());
			int cnt = 0;
			for (int i = 0; i < 8; i++) {
				String[] inp = br.readLine().split("");
				for (int j = 0; j < 8; j++) {
					arr[i][j] = inp[j];
				}
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9-len; j++) {
					StringBuilder sub = new StringBuilder();
					for (int k = j; k < len+j; k++) {
						sub.append(arr[i][k]);
					}
					int k = 0;
					for (; k < len/2+len%2; k++) {
						if (sub.charAt(k) != sub.charAt(len-1-k)) {
							break;
						}
					}
					if (k >= len/2) cnt++;
				}
				
				for (int j = 0; j < 9-len; j++) {
					StringBuilder sub2 = new StringBuilder();
					for (int k = j; k < len+j; k++) {
						sub2.append(arr[k][i]);
					}
					int k = 0;
					for (; k < len/2+len%2; k++) {
						if (sub2.charAt(k) != sub2.charAt(len-1-k)) {
							break;
						}
					}
					if (k >= len/2+len%2) cnt++;
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