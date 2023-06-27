import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int empty_cnt;
	static List<Point> v_start_list = new ArrayList();
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	static int min_time = Integer.MAX_VALUE;

	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][N];
		empty_cnt = 0;
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c]  == 1) {
					map[r][c] = -1;
				} else if(map[r][c] == 2) {
					v_start_list.add(new Point(r,c));
					empty_cnt++;
				}else {
					empty_cnt++;
				}
			}
		}
		
		empty_cnt -= M;
		
		// 바이러스 놓는 곳 조합
		comb(0, new Point[M], 0);
		System.out.println(min_time == Integer.MAX_VALUE ? -1 : min_time);
		
	}
	
	static void comb(int nth, Point[] points, int start_idx) {
		if(nth == M) {
			// 바이러스 확산 시간 체크(bfs)
			min_time = Math.min(min_time, bfs(points));
			return;
		}
		
		int size = v_start_list.size();
		for(int i = start_idx; i<size; i++) {
			points[nth] = v_start_list.get(i);
			comb(nth+1, points, i+1);
		}
	}
	
	// 바이러스 확산 시간 체크 bfs
	static int bfs(Point[] points) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int empty = empty_cnt;
		
		for(int i=0; i<points.length; i++) {
			queue.offer(points[i]);
			visited[points[i].r][points[i].c] = true;
		}
		int depth = -1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				Point cur = queue.poll();
				
				
				for(int d=0; d<4; d++) {
					int dr = cur.r + dy[d];
					int dc = cur.c + dx[d];
					
					if(!isIn(dr,dc)) continue;
					// 바이러스 놓을 수 있는지 확인
					if(visited[dr][dc]) continue;
					if(map[dr][dc] == -1) continue;
					
					// 바이러스 확산
					visited[dr][dc] = true;
					queue.offer(new Point(dr, dc));
					empty--;
				}
				

			}
			depth++;
		}
		
		if(empty>0) return Integer.MAX_VALUE;
		else return depth;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
	static class Point{
		int r,c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		
	}
}