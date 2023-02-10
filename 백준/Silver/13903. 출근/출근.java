import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R,C;
	static int[][] map;
	static int N;
	static int[] dy;
	static int[] dx;
	static int answer = -1;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		N = Integer.parseInt(input.readLine());
		
		dy = new int[N];
		dx = new int[N];
		
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			dy[i] = Integer.parseInt(tokens.nextToken());
			dx[i] = Integer.parseInt(tokens.nextToken());
		}
		
		bfs();
		System.out.println(answer);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		// 첫번째 줄 좌표 다 queue에 넣기
		for(int i=0; i<C; i++) {
			if(map[0][i] == 1) {
				queue.add(new Point(0,i));
				visited[0][i] = true;
			}
		}
		
		// bfs
		int depth = 0;
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			while(size-->0) {
				Point cur = queue.poll();
				if(cur.r == R-1) {
					answer = depth;
					return;
				}
				
				for(int d=0; d<N; d++) {
					int dr = cur.r + dy[d];
					int dc = cur.c + dx[d];
					
					if(!isIn(dr,dc)) continue;
					if(visited[dr][dc]) continue;
					if(map[dr][dc] == 0) continue;
					
					queue.add(new Point(dr, dc));
					visited[dr][dc] = true;
				}
			}
			depth++;
		}
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<R && c<C);
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}