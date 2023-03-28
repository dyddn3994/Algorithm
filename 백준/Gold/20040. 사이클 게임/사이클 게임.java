import java.io.*;
import java.util.*;

class Main {
	
	static int n; // 점의 개수
	static int m; // 진행 차례 수
	static int[] p; // disjoint set
	
	static int findSet(int x) {
		if (p[x] != x) p[x] = findSet(p[x]);
			
		return p[x];
	}
	
	static void union(int x, int y) {
		int p1 = findSet(x);
		int p2 = findSet(y);
		
		p[p1] = p2;
	}
	
	// 사이클이 생성되는지 여부
	static boolean isCycle(int x, int y) {
		if (findSet(x) == findSet(y)) return true;
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// disjoint set 초기화
		p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
		
		// 사이클이 만들어지는지 여부
		int i = 1;
		for (; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (isCycle(x, y)) break;
			
			union(x, y);
		}
		
		if (i == m + 1) System.out.println(0);
		else System.out.println(i);
	}
}
