import java.io.*;
import java.util.*;
 
public class Solution {
	// static 변수
	static char[] open = {'(', '[', '{', '<'};
	static char[] close = {')', ']', '}', '>'};
	
	static boolean isOpen(char c) {
		for (int i = 0; i < open.length; i++) {
			if (c == open[i]) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isPair(char c, char p) {
		for (int i = 0; i < open.length; i++) {
			if (p == open[i] && c == close[i]) {
				return true;
			}
		}
		return false;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
        	// 선언 및 초기화
        	int res = 1;
        	
        	// 입력
        	int len = Integer.parseInt(br.readLine());
        	String inp = br.readLine();
        	
        	// 스택
        	Stack<Character> stack = new Stack<>();
        	for (int i = 0; i < len; i++) {
        		char c = inp.charAt(i);
        		
        		// 여는 기호일 경우
        		if (isOpen(c)) stack.push(c);
        		else {
        			// 닫는 기호일 경우 stack에서 pop하여 기호 짝이 맞는지 비교
        			if (!isPair(c, stack.pop())) {
        				res = 0;
        				break;
        			}
        		}
        	}
        	
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