import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		char[] str3 = br.readLine().toCharArray();

		int[][][] arr = new int[str1.length+1][str2.length+1][str3.length+1];
		for (int i = 0; i < str1.length; i++) {
			for (int j = 0; j < str2.length; j++) {
				for (int k = 0; k < str3.length; k++) {
					if (str1[i] == str2[j] && str2[j] == str3[k]) {
						arr[i+1][j+1][k+1] = arr[i][j][k]+1;
					}
					else arr[i+1][j+1][k+1] = Math.max(arr[i][j+1][k+1], Math.max(arr[i+1][j][k+1], arr[i+1][j+1][k]));
				}
			}
		}

		System.out.println(arr[str1.length][str2.length][str3.length]);
	}
}