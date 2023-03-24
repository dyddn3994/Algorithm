import java.io.*;
import java.util.*;

public class Main {
	
	static int n; // n+1개의 집합
	
	static int[] p; // 부모 노드

	static void makeSet(int x) {
		p[x] = x;
	}
	
	static int findSet(int x) {
		if (x != p[x]) p[x] = findSet(p[x]);
		return p[x];
	}
	
	static void union(int x, int y) {
		p[findSet(x)] = findSet(y);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		p = new int[n+1];
		int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산 개수

		for (int i = 0; i < n+1; i++) {
			makeSet(i);
		}
		
		for (int t = 0; t < m; t++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken()); // 0: 합집합, 1: 같은 집합인지 확인
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (cal == 0) {
				union(a, b);
			}
			else {
				if (findSet(a) == findSet(b)) sb.append("YES");
				else sb.append("NO");
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
