import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			Deque<Integer> q = new ArrayDeque<>();
			int[][] arr = new int[101][101];
			int[] dist = new int[101]; // start부터 해당 위치까지 거리
			int maxDist = 0; // 가장 먼 노드까지의 거리
			int maxNode = 0; // 가장 먼 노드의 값
			
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arr[from][to] = 1;
			}
			
			dist[start] = 1; // 시작점을 1로 초기화하여 다음으로 이동 가능한 위치를 1씩 증가
			q.add(start);
			while(!q.isEmpty()) {
				int val = q.poll();
				
				if (maxDist < dist[val]) {
					maxNode = val;
					maxDist = dist[val];
				}
				if (maxDist == dist[val]) {
					if (maxNode < val) {
						maxNode = val;
						maxDist = dist[val];
					}
				}
				
				for (int i = 1; i <= 100; i++) {
					if (arr[val][i] == 1 && dist[i] == 0) {
						dist[i] = dist[val] + 1;
						q.add(i);
					}
				}
			}
			
			// 출력
			sb.append("#")
				.append(t)
				.append(" ")
				.append(maxNode)
				.append("\n");
		}
		System.out.println(sb);
	}
}