import java.io.*;
import java.util.*;
 
public class Main {
	// static
	static int N;
	static int[][] arr;
	static int max = 0;
	
	static void dfs(int sum, int idx) {
		for (int i = idx; i < N; i++) {
			int nextDay = arr[0][i] + i;
			int nextSum = arr[1][i] + sum;
			
			// 일자를 초과할경우 선택하지 않음
			if (nextDay > N) continue;
			
			dfs(nextSum, nextDay);
		}
		max = Math.max(max, sum);
	}
	
    public static void main(String[] args) throws Exception {
    	// 선언 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	arr[0][i] = Integer.parseInt(st.nextToken());
        	arr[1][i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0, 0);
        
        System.out.println(max);
    }
}