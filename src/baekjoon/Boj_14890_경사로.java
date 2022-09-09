package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14890_경사로 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, L;
	static int[][] map;
	static boolean[] colChecked;
	static boolean[] rowChecked;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		colChecked = new boolean[N];
		rowChecked = new boolean[N];
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		
		int answer = 0;

		searchCol();
		for(boolean col : colChecked) {
			if(col) answer++; 
		}
		
		searchRow();
		for(boolean row : rowChecked) {
			if(row) answer++; 
		}

		System.out.println(answer);
	
	}
	
	// 가로 탐색
	static void searchCol() {
		
		for(int r=0; r<N; r++) {
			boolean canGo = false;
			// 경사로가 놓였는지 확인하는 배열
			boolean[] haveSlope = new boolean[N];
			
			outer : for(int c=0; c<N-1; c++) {
				// 만약 현재 높이가 이전 높이와 다르다면 
				if(map[r][c] != map[r][c+1]) {
					// 2이상 차이 날 때
					if(Math.abs(map[r][c] - map[r][c+1]) > 1) {
						canGo = false;
						break outer;
					}
					
					// 차이가 1일때
					else if(Math.abs(map[r][c] - map[r][c+1]) == 1) {
						
						// 이전 값이 더 작을 때
						if(map[r][c] < map[r][c+1]) {
							
							// 경사로를 놓을 수 있는지 판단
							for(int i = 0; i<L; i++) {
								// 범위 밖
								if(!isIn(r, c-i)) {
									canGo = false;
									break outer;
								}
								
								// 이미 경사로 존재 or 평평하지 않음
								if(haveSlope[c-i] || (map[r][c] != map[r][c-i])) {
									canGo = false;
									break outer;
								}
								
								// 경사로 놓을 수 있음
								canGo = true;
								haveSlope[c-i] = true;
							}
							
						}
							 
						
						// 다음 값이 더 작을 때
						else if(map[r][c] > map[r][c+1]) {
							// 경사로를 놓을 수 있는지 판단 
							for(int i = 0; i<L; i++) {
								if(!isIn(r, c+1+i)) {
									canGo = false;
									break outer;
								}
								if(haveSlope[c+1+i] || map[r][c+1] != map[r][c+1+i]) {
									canGo = false;
									break outer;
								}
								
								canGo = true;
								haveSlope[c+1+i] = true;
							}
							
						}
						
					}
				}
				// 높이가 같으면 continue
				else {
					canGo = true;
					continue;
				}
			}
			// 한 줄 탐색 끝
			colChecked[r] = canGo;
		}
	}
	
	static void searchRow() {
		
		for(int c=0; c<N; c++) {
			boolean canGo = false;
			boolean[] haveSlope = new boolean[N];
			outer : for(int r=0; r<N-1; r++) {
				// 경사로가 놓였는지 확인하는 배열
				
				// 만약 현재 높이가 이전 높이와 다르다면 
				if(map[r][c] != map[r+1][c]) {
					// 2이상 차이 날 때
					if(Math.abs(map[r][c] - map[r+1][c]) > 1) {
						canGo = false;
						break outer;
					}
					// 차이가 1일때
					else if(Math.abs(map[r][c] - map[r+1][c]) == 1) {
						
						// 이전 값이 더 작을 때
						if(map[r][c] < map[r+1][c]) {
							// 경사로를 놓을 수 있는지 판단
							for(int i = 0; i<L; i++) {
								if(!isIn(r-i, c)) {
									canGo = false;
									break outer;
								}
								if(haveSlope[r-i] || (map[r][c] != map[r-i][c])) {
									canGo = false;
									break outer;
								}
								
								canGo = true;
								haveSlope[r-i] = true;
							}
							
						}
							 
						
						// 다음 값이 더 작을 때
						else if(map[r][c] > map[r+1][c]) {
							// 경사로를 놓을 수 있는지 판단 
							for(int i = 0; i<L; i++) {
								if(!isIn(r+1+i, c)) {
									canGo = false;
									break outer;
								}
								if(haveSlope[r+1+i] || map[r+1][c] != map[r+1+i][c]) {
									canGo = false;
									break outer;
								}
								
								canGo = true;
								haveSlope[r+1+i] = true;
							}
							
						}
						
					}
				}
				// 높이가 같으면 continue
				else {
					canGo = true;
					continue;
				}
				
			}
			// 한 줄 탐색 끝
			rowChecked[c] = canGo;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}