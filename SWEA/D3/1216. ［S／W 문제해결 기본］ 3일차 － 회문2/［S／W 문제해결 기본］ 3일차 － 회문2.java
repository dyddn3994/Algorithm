import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[][] arr = new String[100][100];
        for (int t = 1; t <= 10; t++) {
            int c = Integer.parseInt(br.readLine());
            for (int i = 0; i < 100; i++) {
                String[] inp = br.readLine().split("");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = inp[j];
                }
            }
 
            int len = 100;
            boolean finished = false;
            for (; len > 1; len--) {
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 101-len; j++) {
                    	int k = 0;
                    	for(; k < len/2; k++) {
                    		if (!arr[i][k+j].equals(arr[i][len+j-1-k])) break;
                    	}
                        if (k >= len/2) {
                            finished = true;
                            break;
                        }
                    	k = 0;
                    	for(; k < len/2; k++) {
                    		if (!arr[k+j][i].equals(arr[len+j-1-k][i])) break;
                    	}
                        if (k >= len/2) {
                            finished = true;
                            break;
                        }
                    }
                    if (finished) break;
                }
                if (finished) break;
            }
             
            sb.append("#")
                .append(c)
                .append(" ")
                .append(len)
                .append("\n");
        }
        System.out.println(sb);
    }
}