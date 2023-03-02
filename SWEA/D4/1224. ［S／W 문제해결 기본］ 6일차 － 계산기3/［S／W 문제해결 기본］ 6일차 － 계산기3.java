import java.io.*;
import java.util.*;

public class Solution {
	
	static boolean isNumber(char c) {
		if (c == '(' || c == ')' || c == '+' || c == '*') return false;
		return true;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>(); // 중위연산식을 후위연산식으로 바꾸기 위한 스택
        Deque<Integer> intStack = new ArrayDeque<>(); // 후위연산식 계산을 위한 스택
    	Deque<Character> postfix = new ArrayDeque<>();
    	
        for (int t = 1; t <= 10; t++) {
        	// 입력
        	int len = Integer.parseInt(br.readLine());
        	char[] infix = br.readLine().toCharArray();

        	// 중위연산식 -> 후위연산식
        	for (int i = 0; i < len; i++) {
        		if (isNumber(infix[i])) postfix.add(infix[i]);
        		else {
        			if (infix[i] == '(') stack.push(infix[i]);
        			else if (infix[i] == ')') {
        				while (stack.peek() != '(') {
        					postfix.add(stack.pop());
        				}
        				stack.pop(); // 여는 기호 없애기
        			}
        			else if (infix[i] == '+') {
        				while (stack.peek() == '+' || stack.peek() == '*') {
        					postfix.add(stack.pop());
        				}
        				stack.push(infix[i]);
        			}
        			else if (infix[i] == '*') {
        				while (stack.peek() == '*') {
        					postfix.add(stack.pop());
        				}
        				stack.push(infix[i]);
        			}
        		}
        	}
        	while (!stack.isEmpty()) {
        		postfix.add(stack.pop());
        	}
        	
        	// 후위연산식 계산
        	while (!postfix.isEmpty()) {
        		char val = postfix.poll();
        		if (isNumber(val)) {
        			int n = val - '0';
        			intStack.push(n);
        		}
        		else {
        			int secNum = intStack.pop();
        			int firNum = intStack.pop();

        			if (val == '+') intStack.push(firNum + secNum);
        			else if (val == '*') intStack.push(firNum * secNum);
        		}
        	}
        	int res = intStack.pop();
        	
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