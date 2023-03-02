import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			String c = br.readLine();
			String[] comp = br.readLine().split("");
			String[] str = br.readLine().split("");
			int cnt = 0;
			
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals(comp[0])) {
					int idx = 1;
					while (idx < comp.length){
						if (i+idx >= str.length) break; 
						if (!str[i+idx].equals(comp[idx])) break;
						idx++;
					}
					if (idx == comp.length) {
						cnt++;
					}
				}
			}
			
			sb.append("#")
				.append(c)
				.append(" ")
				.append(cnt)
				.append("\n");
		}
		System.out.println(sb);
	}
}