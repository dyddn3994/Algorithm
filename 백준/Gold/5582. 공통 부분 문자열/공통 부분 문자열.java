import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int[][] arr = new int[str1.length+1][str2.length+1];
		
		int max = 0;
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				if (str1[i] == str2[j]) {
					arr[i+1][j+1] = arr[i][j] + 1;
					max = Math.max(max, arr[i+1][j+1]);
				}
				else arr[i+1][j+1] = 0;
			}
		}

		System.out.println(max);
	}
}