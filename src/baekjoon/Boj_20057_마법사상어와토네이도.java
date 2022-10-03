package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_20057_마법사상어와토네이도 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};	// 좌(0) 하(1) 우(2) 상(3)
	static int[] dy = {0, 1, 0, -1};
	// 모래 좌표
	static int[][] sand = {
			{-1, 1, 1}, // dy, dx, %
			{1, 1, 1},
			{-2, 0, 2},
			{-1, 0, 7},
			{1, 0, 7},
			{2, 0, 2},
			{-1, -1, 10},
			{1, -1, 10},
			{0, -2, 5},
			{0, -1, -1} // 알파
	};
	static int ans;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(input.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		for(int r=1; r<=N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=1; c<=N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
//		for(int[] i : map) {
//			System.out.println(Arrays.toString(i));
//		}
		makeTornado(N/2+1, N/2+1);
		System.out.println(ans);
	}
	
	static void makeTornado(int mr, int mc) {
		Tornado cur = new Tornado(mr, mc, 0);
		visited[mr][mc] = true;
		while(true) {
			if(cur.r == 1 && cur.c ==1) break;
			// 이동
			int dir = cur.dir;
			int cr = cur.r + dy[dir];
			int cc = cur.c + dx[dir];
			visited[cr][cc] = true;
			// 모래 흩날림
			moveSand(cr, cc, dir);
			map[cr][cc] = 0;
			// 다음 이동 방향
			cur.dir = next(cr, cc, dir);
			cur.r = cr;
			cur.c = cc;
		}
	}
	static int next(int cr, int cc, int dir) {
		if(dir == 0) {
			// 아래방향 갈수 있는지 체크
			int nr = cr + dy[1];
			int nc = cc + dx[1];
			if(visited[nr][nc]) return 0;
			else return 1;
		}
		else if(dir == 1) {
			// 오른쪽으로 갈 수 있는지 체크
			int nr = cr + dy[2];
			int nc = cc + dx[2];
			if(visited[nr][nc]) return 1;
			else return 2;
		}
		else if(dir == 2) {
			// 위쪽으로 갈 수 있는지 체크
			int nr = cr + dy[3];
			int nc = cc + dx[3];
			if(visited[nr][nc]) return 2;
			else return 3;
		}
		else if(dir == 3) {
			// 왼쪽으로 갈 수 있는지 체크
			int nr = cr + dy[0];
			int nc = cc + dx[0];
			if(visited[nr][nc]) return 3;
			else return 0;
		}
		return 0;
	}
	
	static void moveSand(int cr, int cc, int dir) {
		int total = map[cr][cc]; // y 위치의 모래양
		int moveSandTotal = 0;	// 비율적힌 칸으로 이동한 모래양의 합
		// 왼쪽이면
		if(dir == 0) {
			for(int d=0; d<sand.length; d++) {
				int sr = cr + sand[d][0];
				int sc = cc + sand[d][1];
				int per = sand[d][2];
				// 좌표가 알파일때
				if(per == -1) {
					int tmp = total - moveSandTotal;
					if(!isIn(sr,sc)) {
						ans += tmp;
					}else map[sr][sc] += tmp;
				}else {
					if(!isIn(sr,sc)) {
						// 모래가 밖으로 나갔을 때
						ans += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
					// 모래가 밖으로 나가지 않았을 때
					else {
						// 퍼센트 계산
						map[sr][sc] += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
				}

			}
		}
		// 아래방향이면
		else if(dir == 1) {
			for(int d=0; d<sand.length; d++) {
				int sr = cr + sand[d][1] * (-1);
				int sc = cc + sand[d][0];
				int per = sand[d][2];
				// 좌표가 알파일때
				if(per == -1) {
					int tmp = total - moveSandTotal;
					if(!isIn(sr,sc)) {
						ans += tmp;
					}else map[sr][sc] += tmp;
				}else {
					if(!isIn(sr,sc)) {
						// 모래가 밖으로 나갔을 때
						ans += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
					// 모래가 밖으로 나가지 않았을 때
					else {
						// 퍼센트 계산
						map[sr][sc] += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
				}
			}
		}
		else if(dir == 2) {
			for(int d=0; d<sand.length; d++) {
				int sr = cr + sand[d][0];
				int sc = cc + sand[d][1] * (-1);
				int per = sand[d][2];
				// 좌표가 알파일때
				if(per == -1) {
					int tmp = total - moveSandTotal;
					if(!isIn(sr,sc)) {
						ans += tmp;
					}else map[sr][sc] += tmp;
				}else {
					if(!isIn(sr,sc)) {
						// 모래가 밖으로 나갔을 때
						ans += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
					// 모래가 밖으로 나가지 않았을 때
					else {
						// 퍼센트 계산
						map[sr][sc] += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
				}
			}
		}
		else if(dir == 3) {
			for(int d=0; d<sand.length; d++) {
				int sr = cr + sand[d][1];
				int sc = cc + sand[d][0] * (-1);
				int per = sand[d][2];
				// 좌표가 알파일때
				if(per == -1) {
					int tmp = total - moveSandTotal;
					if(!isIn(sr,sc)) {
						ans += tmp;
					}else map[sr][sc] += tmp;
				}else {
					if(!isIn(sr,sc)) {
						// 모래가 밖으로 나갔을 때
						ans += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
					// 모래가 밖으로 나가지 않았을 때
					else {
						// 퍼센트 계산
						map[sr][sc] += (int)(total * 0.01 * per); 
						moveSandTotal+=(int)(total * 0.01 * per);
					}
				}
			}
		}
	}
	
	static class Tornado{
		int r, c, dir;

		public Tornado(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ") dir : " + dir;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=N && c<=N);
	}
}
