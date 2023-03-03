import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[100];
		for (int t = 1; t <= 10; t++) {
			int cnt = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int min = 101;
			int minIdx = 0;
			int max = -1;
			int maxIdx = 0;
			for (int j = 0; j <= cnt; j++) {
				min = 101;
				max = -1;
				for(int i = 0; i < 100; i++) {
					if (max < arr[i]) {
						max = arr[i];
						maxIdx = i;
					}
					if (min > arr[i]) {
						min = arr[i];
						minIdx = i;
					}
				}
				if (max == min) {
					break;
				}
				arr[maxIdx]--;
				arr[minIdx]++;
			}
			for(int i = 0; i < 100; i++) {
				if (max < arr[i]) {
					max = arr[i];
				}
				if (min > arr[i]) {
					min = arr[i];
				}
			}
			sb.append("#")
				.append(t)
				.append(" ")
				.append(max-min)
				.append("\n");
		}
		System.out.println(sb);
	}
}