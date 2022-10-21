package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_9207_페그솔리테어 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static char[][] map;
	static int T;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int ans;
	static int minRemainCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		for(int t =1; t<=T; t++) {
			map = new char[5][9];
			minRemainCnt = 0;
			ans = 0;
			for(int r=0; r<5; r++) {
				String str = input.readLine();
				for(int c=0; c<9; c++) {
					map[r][c] = str.charAt(c);
					if(map[r][c] == 'o') minRemainCnt++;
				}
			}
			if(t != T) input.readLine();
			

			int remainCnt = minRemainCnt;
			for(int r=0; r<5; r++) {
				for(int c=0; c<9; c++) {
					if(map[r][c] == 'o') {
						dfs(r,c, remainCnt, 0);
					}
				}
			}
			

			
			sb.append(minRemainCnt+" "+ans+"\n");
		}///  테케 끝
		System.out.println(sb);
	}
	
	
	
	private static void dfs(int r, int c, int remainCnt, int cnt) {
		if(minRemainCnt >= remainCnt) {
			minRemainCnt = remainCnt;
			ans = cnt;

		}
		
		for(int d=0; d<dx.length; d++) {
			int dr = r + dy[d];
			int dc = c + dx[d];
			
			if(!isIn(dr, dc) || !isIn(dr+dy[d], dc+dx[d])) continue;
			// 인접한 곳에 핀이 있음 && 그 다음 칸이 빈칸
			if(map[dr][dc] == 'o' && map[dr+dy[d]][dc+dx[d]] == '.') {
				// 핀 이동
				map[r][c] = '.';
				map[dr + dy[d]][dc + dx[d]] = 'o';
				// 인접 핀 삭제
				map[dr][dc] = '.';
				for(int i=0; i<5; i++) {
					for(int j=0; j<9; j++) {
						if(map[i][j] == 'o') {
							dfs(i, j, remainCnt-1, cnt+1);
						}
					}
				}
				
				
				// 핀 이동, 삭제 되돌리기
				// 핀 이동
				map[r][c] = 'o';
				map[dr + dy[d]][dc + dx[d]] = '.';
				// 인접 핀 삭제
				map[dr][dc] = 'o';
			}
		}
	}

	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<5 && c<9);
	}
	
	static class Pin{
		int r, c;
		
		public Pin(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}
