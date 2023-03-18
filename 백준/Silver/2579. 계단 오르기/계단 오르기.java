// 처음에 step+2로 범위 설정해서 처리했는데 그렇게 하면 0인 곳에서 오는게 더 값이 큰 반례가 있어서(4 1 100 100 1) 안됨. 

import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int step = Integer.parseInt(br.readLine()); // 계단 수
		int[] arr = new int[step]; // 계단 점수
		for (int i = 0; i < step; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[][] point = new int[2][step]; // 계단 별 최대점수, [0][i] / [1][i] : 한계단 / 두계단 위에서 올수있는 최대점수
		point[0][step-1] = point[1][step-1] = arr[step-1];
		if (step > 1) point[0][step-2] = arr[step-2] + point[0][step-1];
		for (int i = step-3; i >= 0; i--) {
			point[0][i] = arr[i] + point[1][i+1]; // 한계단 위에서 오는 점수는 그 곳에서 두계단 위에서만 가능
			point[1][i] = arr[i] + (point[0][i+2] > point[1][i+2] ? point[0][i+2] : point[1][i+2]); // 두계단 위에서 오는 점수는 그 곳에서 최대점수
		}
		
		int max1 = point[0][0] > point[1][0] ? point[0][0] : point[1][0];
		int max2 = 0;
		if (step > 1) max2 = point[0][1] > point[1][1] ? point[0][1] : point[1][1];
		System.out.println(max1 > max2 ? max1 : max2);
	}
} 