package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Boj_1018 {
	static char[][] map;	//입력 받은 보드
	static char[][] map1 = new char[8][8];	// 정상적인 체스판(시작점 : W)
	static char[][] map2 = new char[8][8];	// 정상적인 체스판(시작점 : B)
	static int N;
	static int M;
	static int min = Integer.MAX_VALUE;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static StringTokenizer tokens;
	
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new char[N][M];
		
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			for(int c=0; c< M; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		// 입력 끝
		
		// 정상적인 체스판
		for(int r=0; r<8; r++) {
			if(r%2 ==0) {
				for(int c=0; c<8; c++) {
					if(c%2 ==0) {
						map1[r][c] = 'W';
						map2[r][c] = 'B';
					}else {
						map1[r][c] = 'B';
						map2[r][c] = 'W';
					}
				}
			}else {
				for(int c=0; c<8; c++) {
					if(c%2 ==0) {
						map1[r][c] = 'B';
						map2[r][c] = 'W';
					}else {
						map1[r][c] = 'W';
						map2[r][c] = 'B';
					}
				}
			}
		}
		
		// 체스판과 비교하여 다시 칠해야되는 개수 확인
		for(int r=0; r<=N-8; r++) {
			for(int c=0; c<=M-8; c++) {
				min = Math.min(min,check(r,c,map1));
				min = Math.min(min,check(r,c,map2));
			}
		}
		
		System.out.println(min);
		
	}
	static int check (int r, int c,char mapColor[][]) {
		int cnt=0;
		for(int i=0, R=r; i<8; i++, R++) {
			for(int j=0, C=c; j<8; j++,C++) {
				if(mapColor[i][j] != map[R][C]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
