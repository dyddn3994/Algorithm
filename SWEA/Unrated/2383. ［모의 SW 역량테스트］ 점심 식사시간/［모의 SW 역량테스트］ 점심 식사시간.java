import java.io.*;
import java.util.*;

class Solution {
	
	static int N; // 배열 크기
	static int[][] arr; // 입력 배열

	static int peopleCnt; // 사람수
	static int[][] people; // 사람 위치, 행위치/열위치/선택한계단
	static int[][] stair; // 계단 위치

	static int res;

	// 이동 시간 계산
	static void move() {
		int[] stairUsingCnt = new int[2]; // 계단 별 사람 수
		
		int[][] peopleStep = new int[peopleCnt][2]; // 사람 별 남은 걸음 수
		for (int i = 0; i < peopleCnt; i++) {
			int selectStair = people[i][2]; // 선택한 계단
			peopleStep[i][0] = Math.abs(people[i][0] - stair[selectStair][0]) + Math.abs(people[i][1] - stair[selectStair][1]) + 1;
		}
		
		int finishCnt = 0; // 도착한 사람 수
		int time = 0; // 걸린 시간
		while (finishCnt < peopleCnt) {
			for (int i = 0; i < peopleCnt; i++) {
				// 도착한 사람
				if (peopleStep[i][0] == -1 && peopleStep[i][1] == 0) continue; 
				
				if (peopleStep[i][1] > 0) {
					// 계단 내려가는중
					peopleStep[i][1]--;

					// 도착 완료
					if (peopleStep[i][1] == 0) {
						stairUsingCnt[people[i][2]]--;
						finishCnt++;
						
						// 이전 인덱스 중에 계단에 도착한 사람 계단 내려가기 시작
						for (int j = i - 1; j >= 0; j--) {
							if (peopleStep[j][0] != 0) continue;
							
							int selectStair = people[j][2];
							peopleStep[j][0] = -1;
							peopleStep[j][1] = arr[stair[selectStair][0]][stair[selectStair][1]];
							stairUsingCnt[selectStair]++;
							break;
						}
					}
				}
				else if (peopleStep[i][0] > 0) {
					// 계단으로 가는중
					peopleStep[i][0]--;
				}
				
				if (peopleStep[i][0] == 0) {
					// 계단 도착
					int selectStair = people[i][2];
					if (stairUsingCnt[selectStair] >= 3) continue; // 계단 사용자가 3명 이상이면 사용불가
					
					peopleStep[i][0] = -1;
					peopleStep[i][1] = arr[stair[selectStair][0]][stair[selectStair][1]];
					stairUsingCnt[selectStair]++;
				}
			}
			
			time++;
		}
		
		res = Math.min(res, time);
	}
	
	static void comb(int selectCnt, int idx) {
		move();
		
		// 사람 별 계단 선택
		for (int i = idx; i < peopleCnt; i++) {
			people[i][2] = 1;
			comb(selectCnt + 1, i + 1);
			people[i][2] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			peopleCnt = 0;
			people = new int[11][3];
			stair = new int[2][2];
			
			int stairIdx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) {
						people[peopleCnt][0] = i;
						people[peopleCnt++][1] = j;
					}
					else if (arr[i][j] > 1) {
						stair[stairIdx][0] = i;
						stair[stairIdx++][1] = j;
					}
				}
			}
			
			res = Integer.MAX_VALUE;
			
			comb(0, 0);
			
			sb.append("#")
				.append(t)
				.append(" ")
				.append(res)
				.append("\n");
		}
		
		System.out.println(sb);
	}
}
