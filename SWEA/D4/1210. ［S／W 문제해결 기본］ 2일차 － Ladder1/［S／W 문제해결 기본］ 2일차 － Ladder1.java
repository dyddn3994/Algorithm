import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[102][102];
		int x = 100, y = 0;
		for (int t = 1; t <= 10; t++) {
			int c = Integer.parseInt(br.readLine());
			for (int i = 1; i <= 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 2) {
						x = i; y = j;
					}
				}
			}
			
			while (x > 1) {
				if (arr[x][y-1] == 1) {
					while (arr[x][y-1] == 1) {
						y--;
					}	
				}
				else if (arr[x][y+1] == 1) {
					while (arr[x][y+1] == 1) {
						y++;
					}
				}
				x--;
			}
			
			sb.append("#")
				.append(c)
				.append(" ")
				.append(y-1)
				.append("\n");
		}
		System.out.println(sb);
	}
}