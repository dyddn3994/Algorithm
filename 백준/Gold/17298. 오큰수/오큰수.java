import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> res = new Stack<>();
        int n1 = Integer.parseInt(br.readLine());
        StringTokenizer n2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n1; i++) {
            s1.push(Integer.parseInt(n2.nextToken()));
        }
        res.push(-1);
        s2.push(s1.pop());
        while(!s1.isEmpty()) {
            while (!s2.isEmpty() && s2.peek() <= s1.peek()) s2.pop();
            if (s2.isEmpty()) res.push(-1);
            else res.push(s2.peek());
            s2.push(s1.pop());
        }
        while(!res.isEmpty()) {
            bw.write(res.pop() + " ");
        }
        bw.close();
    }
}

