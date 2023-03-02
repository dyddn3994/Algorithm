import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Deque<Integer> q = new ArrayDeque<>();
		
		for (int t = 1; t <= 10; t++) {
			// 입력
			int tc = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int cycle = 1; // 감소 사이클
			while(true) {
				int val = q.poll();
				val -= cycle;
				if (val <= 0) {
					q.add(0);
					break;
				}
				q.add(val);
				cycle = cycle % 5 + 1;
			}
			
			// 출력
			sb.append("#")
				.append(tc);
			while (!q.isEmpty()) {
				sb.append(" ")
				.append(q.poll());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
