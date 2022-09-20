package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Boj_17141_연구소2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] map;
	static List<Point> list = new LinkedList<>();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == 2) {
					list.add(new Point(r,c));
				}
			}
		}
		
		
		makeComb(0, new Point[M], 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
	}
	
	static void makeComb(int nth, Point[] choosed, int startIdx) {
		if(nth == M) {
			// 뽑은 위치로 bfs 돌리기
			int time = virusMove(choosed);
			min = Math.min(min, time);
			return;
		}
		
		for(int i=startIdx; i<list.size(); i++) {
			choosed[nth] = list.get(i);
			makeComb(nth+1, choosed, i+1);
		}
	}
	
	static int virusMove(Point[] choosed) {
		int[][] copymap = new int[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				copymap[r][c] = map[r][c];
			}
		}
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// map에 표시 & 큐에 넣기
		for(int i = 0; i<choosed.length; i++) {
			int nr = choosed[i].r;
			int nc = choosed[i].c;
			
			// 선택한 바이러스 -1로 표시
			copymap[nr][nc] = -1;
			queue.offer(new Point(nr, nc));
			visited[nr][nc] = true;
		}
		
		
		// bfs
		int depth = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Point cur = queue.poll();
				
				for(int i=0; i<dx.length; i++) {
					int dr = cur.r + dy[i];
					int dc = cur.c + dx[i];
					
					if(!isIn(dr, dc)) continue;
					
					if(copymap[dr][dc] == 1 || visited[dr][dc] == true) continue;
					queue.offer(new Point(dr, dc));
					visited[dr][dc] = true;
					copymap[dr][dc] = -1;
				}
			}
			depth++;
		}
		// 바이러스를 다 퍼트릴 수 있는지 확인
		if(!allVirusCheck(copymap)) {
			return Integer.MAX_VALUE;
		}
		return depth-1;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
	static boolean allVirusCheck(int[][] copymap) {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(copymap[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static class Point{
		int r,c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "("+r + "," + c + ")";
		}
		
	}
	
}
