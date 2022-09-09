package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15683_���� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N,M;
	static int[][] map;
	static List<Point> cctvs = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	static int zeroCnt;
	// ��:0, ��:1, ��:2, ��:3
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][][] dirSet = {
			// ��:0, ��:1, ��:2, ��:3
			{},
			{{0}, {1}, {2}, {3}}, // CCTV 1
			{{1, 3}, {2, 0}}, // CCTV 2
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // CCTV 3
			{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // CCTV 4
			{{0, 1, 2, 3}} // CCTV 5
	};

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		for(int r = 0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] >0 && map[r][c] <6) {
					cctvs.add(new Point(r, c, map[r][c]));
				}
				if(map[r][c] == 0) zeroCnt++;
			}
		}
		
		dfs(0, 0);
		System.out.println(min);
		
	}
	
	static void dfs(int nth, int canSee) {
		if(nth == cctvs.size()) {
			// �簢 ���밡 �ּ� ũ������ �Ǵ�
			min = Math.min(min, (zeroCnt - canSee));
			return;
		}
		// cctv ������� ������
		Point cur = cctvs.get(nth);
		for(int i = 0; i< dirSet[cur.t].length; i++) {
			// cur cctv�� i�������� scan -> ���õǴ� ������ ���� ����
			int watch = cur.scan(i, -1);
			// dfs -> ���� cctv�� ���� ����
			dfs(nth+1, canSee + watch);
			// map �ѹ�
			cur.scan(i, 1);
		}
	}
	
	static class Point{
		int r, c, t;

		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
		
		public int scan(int index, int flag) {
			int watch = 0;
			for(int d=0; d<dirSet[this.t][index].length; d++) {
				// ex) {0, 1, 2} ������
				int nr = this.r;
				int nc = this.c;
				int dir =  dirSet[this.t][index][d];
				
				while(true) {
					nr += dy[dir];
					nc += dx[dir];
					
					// ���� ������ ������ ����
					if(!isIn(nr, nc)) {
						break;
					}
					
					// ���� ������ ����
					if(map[nr][nc] == 6) {
						break;
					}
					
					// cctv������ continue
					if(map[nr][nc] > 0) continue;
					
					// ���� ������ �� ������ ��
					if(map[nr][nc] == 0) {
						watch++;
					}
					map[nr][nc] += flag;
				}
			}
			return watch;
		}
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}
}
