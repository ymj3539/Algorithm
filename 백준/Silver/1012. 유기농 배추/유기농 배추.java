import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] map;
	static int M, N, K;
	static int ans;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	static Queue<Point> queue;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(input.readLine());
		
		for(int t=1; t<=T; t++) {
			queue = new LinkedList<>();
			ans = 0;
			tokens = new StringTokenizer(input.readLine());
			M = Integer.parseInt(tokens.nextToken());
			N = Integer.parseInt(tokens.nextToken());
			
			
			map = new int[N][M];
			visited = new boolean[N][M];
			K = Integer.parseInt(tokens.nextToken());
			
			for(int i=0; i<K; i++) {
				tokens = new StringTokenizer(input.readLine());
				int c = Integer.parseInt(tokens.nextToken());
				int r = Integer.parseInt(tokens.nextToken());
				map[r][c] = 1;
				queue.offer(new Point(r,c));
			}
			
			
			while(!queue.isEmpty()) {
				Point tmp = queue.poll();
				if(!visited[tmp.r][tmp.c]) {
					visited[tmp.r][tmp.c] = true;
					dfs(tmp.r, tmp.c);
					ans++;
				}
				
			}
			sb.append(ans+"\n");
			
			
		}
		System.out.println(sb);
			
	}
	
	static void dfs(int r, int c) {
		if(map[r][c] == 0) return;
		
		for(int i=0; i<dx.length; i++) {
			int dr = r + dy[i];
			int dc = c + dx[i];
			
			if(!isIn(dr, dc)) continue;
			
			if(!visited[dr][dc]){
				visited[dr][dc] = true;
				dfs(dr, dc);
				
			}
		}
		
		
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}
	
}
