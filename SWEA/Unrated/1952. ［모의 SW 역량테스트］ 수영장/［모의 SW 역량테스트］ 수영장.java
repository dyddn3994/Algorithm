import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] price;
	static int[] month;
	
	static int min;
	
	static void selec(int idx, int sum) {
		while(idx < 12 && month[idx] == 0) {
			idx++;
		}
		if (idx >= 12) {
			if (min > sum) min = sum;
			return;
		}
		int daily = sum+price[0]*month[idx];
		int onemonth = sum+price[1];
		int dailyoronemonth = (daily > onemonth) ? onemonth : daily;
		
		selec(idx+1, dailyoronemonth);
		selec(idx+3, sum+price[2]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 입력
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			price = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			month = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			min = price[3];
			selec(0, 0);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(min)
				.append("\n");
		}
		System.out.println(sb);
	}
}