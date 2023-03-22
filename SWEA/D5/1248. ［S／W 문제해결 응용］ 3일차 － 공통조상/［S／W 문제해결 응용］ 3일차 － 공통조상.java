import java.io.*;
import java.util.*;

public class Solution {
	
	static int V; // 정점 수
	static int E; // 간선 수
	static int n1, n2; // 조상 찾을 번호
	static int[][] arr; // 트리, [i][0]/[i][1] : i번 노드의 왼쪽/오른쪽 자식노드
	
	static List<Integer> list1, list2; // n1, n2의 조상 노드 리스트
	
	static int anc; // 공통 조상노드
	static int res; // 서브 트리 크기
	
	// v가 루트인 서브트리 크기 반환
	static int getTreeSize(int v) {
		if (v == 0) return 0;
		
		int size = 1;
		size += getTreeSize(arr[v][0]);
		size += getTreeSize(arr[v][1]);
		
		return size;
	}

	// v를 기준으로 n의 조상노드 찾기
	static boolean findAnc(int v, int n, List<Integer> list) {
		if (v == n) return true;
		
		boolean ret = false; // 자식노드 순회하여 찾는 노드 있는지 여부
		if (arr[v][0] != 0) ret = findAnc(arr[v][0], n, list);
		if (ret) {
			// 자식노드 있었으면 성공
			list.add(v);
			return true;
		}

		ret = false; // 자식노드 순회하여 찾는 노드 있는지 여부
		if (arr[v][1] != 0) ret = findAnc(arr[v][1], n, list);
		if (ret) {
			// 자식노드 있었으면 성공
			list.add(v);
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			
			arr = new int[V+1][2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				
				if (arr[parent][0] == 0) arr[parent][0] = child;
				else arr[parent][1] = child;
			}
			
			list1 = new ArrayList<>();
			list2 = new ArrayList<>();
			
			// 조상노드 찾기
			findAnc(1, n1, list1);
			findAnc(1, n2, list2);
			
			// 가장 가까운 공통 조상 
			int idx1 = 0, idx2 = 0;
			while (list1.get(idx1) - list2.get(idx2) != 0) {
				if (list1.size() - idx1 < list2.size() - idx2) idx2++;
				else idx1++;
			}
			anc = list1.get(idx1);
			
			// 트리 크기 구하기
			res = getTreeSize(anc);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(anc)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
