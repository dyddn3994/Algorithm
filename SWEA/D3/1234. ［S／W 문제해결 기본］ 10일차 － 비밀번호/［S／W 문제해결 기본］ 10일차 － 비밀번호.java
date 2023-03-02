import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
    	// 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int t = 1; t <= 10; t++) {
        	// 입력
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int len = Integer.parseInt(st.nextToken());
        	char[] inp = st.nextToken().toCharArray();
        	
        	// 스택 top과 값이 같으면 소거, 다르면 push
        	for (int i = 0; i < len; i++) {
        		if (!stack.isEmpty() && stack.peek() == inp[i]) {
        			stack.pop();
        		}
        		else {
        			stack.push(inp[i]);
        		}
        	}
        	
        	// 스택에 남은 값들은 출력결과에 넣기
        	char[] res = new char[stack.size()];
        	for (int i = 0; i < res.length; i++) {
        		res[i] = stack.pollLast();
        	}
        	
        	// 출력
        	sb.append("#")
        		.append(t)
        		.append(" ")
        		;
        	for (int i = 0; i < res.length; i++) {
        		sb.append(res[i]);
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
}