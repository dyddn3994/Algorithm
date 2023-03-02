import java.io.*;
import java.util.*;
 
public class Solution {
	static int sqr(int res, int idx) {
		if (idx == 1) return res;
		return res*sqr(res, idx-1);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
        	// 입력
        	int c = Integer.parseInt(br.readLine());
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int res = Integer.parseInt(st.nextToken());
        	int idx = Integer.parseInt(st.nextToken());
        	
        	res = sqr(res, idx);
        	
        	// 출력
        	sb.append("#")
        		.append(t)
        		.append(" ")
        		.append(res)
        		.append("\n");
        }
        System.out.println(sb);
    }
}