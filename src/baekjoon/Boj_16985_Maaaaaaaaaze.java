package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16985_Maaaaaaaaaze {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int[][][] map, copymap;
	static int min = Integer.MAX_VALUE;
	static int dx[] = {0, 0, 0, 0, 1, -1};
	static int dy[] = {0, 0, 1, -1, 0, 0};	
	static int dz[] = {1, -1, 0, 0, 0, 0};	
	public static void main(String[] args) throws IOException {
		
		map = new int[5][5][5];
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				tokens = new StringTokenizer(input.readLine());
				for(int k=0; k<5; k++) {
					map[i][j][k] = Integer.parseInt(tokens.nextToken());
				}
			}
		}
		
		
		makePerm(0, new int[5], new boolean[5]);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	// 쌓는 순서 순열 만들기
	static void makePerm(int nth, int[] choosed, boolean[] visited) {
		if(nth == 5) {
			// 4방향으로 돌리기
			makeDupPerm(0, new int[5], choosed);
			return;
		}
		
		for(int i=0; i<5; i++) {
			if(!visited[i]) {
				choosed[nth] = i;
				visited[i] = true;
				makePerm(nth+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	// 회전 방향 정하기 - 중복 순열
	static void makeDupPerm(int nth, int[] dirChoosed, int[] choosed) {
		if(nth == 5) {
			copymap = new int[5][5][5];
			for(int i=0; i<5; i++) {
				copymap[i] = map[choosed[i]];
			}
			
			// 회전시키기
			for(int i=0; i<5; i++) {
				copymap[i] = rotate(dirChoosed[i], copymap[i]);
			}
			
			// 출입구가 막혔으면 return
			if(copymap[0][0][0] == 0 || copymap[4][4][4] == 0) {
				return;
			}
			
			min = Math.min(bfs(copymap), min);
			return;
		}
		
		for(int i=1; i<=4; i++) {
			dirChoosed[nth] = i;
			makeDupPerm(nth+1, dirChoosed, choosed);
		}
	}
	
	// 판 회전시키기
	static int[][] rotate(int n, int[][] copymap){
		
		for(int i=0; i<n; i++) {
			int[][] tmp = new int[5][5];
			for(int r=4; r>=0; r--) {
				for(int c=0; c<5; c++) {
					tmp[r][c] = copymap[c][4-r];
				}
			}
			copymap = tmp;
		}
		
		return copymap;
	}
	
	// 미로 탐색
	static int bfs (int[][][] copymap) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		
		queue.offer(new Point(0,0,0));
		visited[0][0][0] = true;
		int cnt =0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- >0) {
				Point cur = queue.poll();
				if(cur.z==4 &&  cur.y==4 && cur.x == 4 ) return cnt;
				int nz = 0;
				int ny = 0;
				int nx = 0;
				
				for(int i=0; i<6; i++) {
					nz = cur.z + dz[i];
					ny = cur.y + dy[i];
					nx = cur.x + dx[i];
					
					if(isIn(nz, ny, nx) && !visited[nz][ny][nx] && copymap[nz][ny][nx] == 1) {
						queue.offer(new Point(nz, ny, nx));
						visited[nz][ny][nx] = true;
					}
				}
				
			}
			cnt++;
		}
		
		return min;
		
	}
	
	static boolean isIn(int z, int y, int x) {
		return (z>=0 && y>=0 && x>=0 && z<5 && y<5 && x<5);
	}
	
	static class Point{
		int z,y,x;

		public Point(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "(" + z + ", " + y + ", " + x + ")";
		}
	}
	
	
}
