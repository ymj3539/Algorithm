package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14890_���� {
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
	
	// ���� Ž��
	static void searchCol() {
		
		for(int r=0; r<N; r++) {
			boolean canGo = false;
			// ���ΰ� �������� Ȯ���ϴ� �迭
			boolean[] haveSlope = new boolean[N];
			
			outer : for(int c=0; c<N-1; c++) {
				// ���� ���� ���̰� ���� ���̿� �ٸ��ٸ� 
				if(map[r][c] != map[r][c+1]) {
					// 2�̻� ���� �� ��
					if(Math.abs(map[r][c] - map[r][c+1]) > 1) {
						canGo = false;
						break outer;
					}
					
					// ���̰� 1�϶�
					else if(Math.abs(map[r][c] - map[r][c+1]) == 1) {
						
						// ���� ���� �� ���� ��
						if(map[r][c] < map[r][c+1]) {
							
							// ���θ� ���� �� �ִ��� �Ǵ�
							for(int i = 0; i<L; i++) {
								// ���� ��
								if(!isIn(r, c-i)) {
									canGo = false;
									break outer;
								}
								
								// �̹� ���� ���� or �������� ����
								if(haveSlope[c-i] || (map[r][c] != map[r][c-i])) {
									canGo = false;
									break outer;
								}
								
								// ���� ���� �� ����
								canGo = true;
								haveSlope[c-i] = true;
							}
							
						}
							 
						
						// ���� ���� �� ���� ��
						else if(map[r][c] > map[r][c+1]) {
							// ���θ� ���� �� �ִ��� �Ǵ� 
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
				// ���̰� ������ continue
				else {
					canGo = true;
					continue;
				}
			}
			// �� �� Ž�� ��
			colChecked[r] = canGo;
		}
	}
	
	static void searchRow() {
		
		for(int c=0; c<N; c++) {
			boolean canGo = false;
			boolean[] haveSlope = new boolean[N];
			outer : for(int r=0; r<N-1; r++) {
				// ���ΰ� �������� Ȯ���ϴ� �迭
				
				// ���� ���� ���̰� ���� ���̿� �ٸ��ٸ� 
				if(map[r][c] != map[r+1][c]) {
					// 2�̻� ���� �� ��
					if(Math.abs(map[r][c] - map[r+1][c]) > 1) {
						canGo = false;
						break outer;
					}
					// ���̰� 1�϶�
					else if(Math.abs(map[r][c] - map[r+1][c]) == 1) {
						
						// ���� ���� �� ���� ��
						if(map[r][c] < map[r+1][c]) {
							// ���θ� ���� �� �ִ��� �Ǵ�
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
							 
						
						// ���� ���� �� ���� ��
						else if(map[r][c] > map[r+1][c]) {
							// ���θ� ���� �� �ִ��� �Ǵ� 
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
				// ���̰� ������ continue
				else {
					canGo = true;
					continue;
				}
				
			}
			// �� �� Ž�� ��
			rowChecked[c] = canGo;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}