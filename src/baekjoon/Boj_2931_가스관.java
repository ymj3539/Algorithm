package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_2931_가스관 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static Point start;
	static List<Integer> emptyPipeDir = new LinkedList<>();
	static Point emptyPoint;
	static char answer;
	static int[] dx = {0, 1, 0, -1}; // 상(0) 우(1) 하(2) 좌(3)
	static int[] dy = {-1, 0, 1, 0};
	static int[][] pipes = {
			{},
			{1, 2}, // 파이프 1
			{0, 1}, // 파이프 2
			{0, 3}, // 파이프 3
			{2, 3}, // 파이프 4
			{0, 2}, // 파이프 5
			{1, 3}, // 파이프 6
			{0, 1, 2, 3}, // 파이프 7
	}; 
	
	// 원래 어떤 파이프였는지 찾는 메소드
	static char findPipe() {
		if(emptyPipeDir.size() == 4) return '+';
		else {
			int a = emptyPipeDir.get(0);
			int b = emptyPipeDir.get(1);
			if(a == 1 && b == 2) return '1';
			else if(a == 0 && b == 1) return '2';
			else if(a == 0 && b == 3) return '3';
			else if(a == 2 && b == 3) return '4';
			else if(a == 0 && b == 2) return '|';
			else if(a == 1 && b == 3) return '-';
			
		}
		return 'X';
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R+1][C+1];
		visited = new boolean[R+1][C+1];
		
		for(int r=1; r<=R; r++ ) {
			String str = input.readLine();
			for(int c=1; c<=C; c++) {
				map[r][c] = str.charAt(c-1);
				if(map[r][c] == 'M') {
					start = new Point(r,c);
					visited[r][c] = true;
				}
				if(map[r][c] == '|') map[r][c] = '5';
				if(map[r][c] == '-') map[r][c] = '6';
				if(map[r][c] == '+') map[r][c] = '7';
			}
		}
		// 시작점 M에서 파이프 찾기
		for(int i=0; i<dx.length; i++) {
			int dr = start.r + dy[i];
			int dc = start.c + dx[i];
			
			// 범위 밖 
			if(!isIn(dr,dc)) continue;
			
			// 빈칸이면 넘어가
			if(map[dr][dc] == '.') continue;
			
			// 파이프가 있으면
			if(map[dr][dc] >='1' && map[dr][dc] <= '7') {
				
				// 지나갈 수 있는지 체크
				if(isPossible ((map[dr][dc] -'0'), i)) {
					
					// 지나갈 수 있으면 dfs 시작
					visited[dr][dc] = true;
					dfs(new Point(dr,dc));
				}
			}
		}
		
		Collections.sort(emptyPipeDir);
	
		// 출력
		System.out.println(emptyPoint.r+" "+emptyPoint.c+" "+findPipe());
	}
	
	static void dfs(Point cur) {
		// 현재 파이프의 타입
		int t = map[cur.r][cur.c] -'0';
		
		for(int i=0; i<pipes[t].length; i++) {
			// 현재 파이프가 갈 수 있는 방향 탐색
			int dir = pipes[t][i];
			int dr = cur.r + dy[dir];
			int dc = cur.c + dx[dir];
			
			// 범위 밖 
			if(!isIn(dr, dc)) continue;
			
			// Z에 도달하거나 방문한 곳이면
			if(map[dr][dc] =='Z' || visited[dr][dc]) {
				visited[dr][dc] = true;
				continue;
			}
			
			
			// 파이프가 없으면 그 자리가 지워진 파이프의 자리!
			if(map[dr][dc] == '.') {
				emptyPoint = new Point(dr, dc);
				visited[dr][dc] = true;
				
				// 주변에 연결가능한 파이프 찾기
				for(int j =0; j<dx.length; j++) {
					int er = dr + dy[j];
					int ec = dc + dx[j];
					if(!isIn(er, ec)) continue;
					
					if(map[er][ec] =='Z') {
						continue;
					}
					
					if(map[er][ec] >='1' && map[er][ec] <= '7') {
						// 지나갈 수 있는지 체크
						if(isPossible ((map[er][ec] -'0'), j)) {
							
							// 지나갈 수 있으면 dfs 시작
							visited[er][ec] = true;
							emptyPipeDir.add(j);
							dfs(new Point(er,ec));
						}
					}
				}
				continue;
			}
			
			
			// 다음 파이프로 진행 가능한지 판단
			if(isPossible((map[dr][dc] -'0'), dir)) {
				visited[dr][dc] = true;
				dfs(new Point(dr,dc));
			}
		}
	}
	
	// 지나가려는 파이프 번호와 현재위치에서의 이동 방향이 주어졌을 때 지나갈 수 있는지 판단하는 함수
	static boolean isPossible(int pipeNum, int dir) {
		int[] pipe = pipes[pipeNum];
		for(int i=0; i<pipe.length; i++) {
			// pipe가 뚫려있는 방향과 내 진행 방향의 차가 2면 갈수 있음
			if( Math.abs(pipe[i] - dir) == 2 ) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=R && c<=C);
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
		
	}
}
