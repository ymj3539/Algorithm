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
	static int N;
	static int[][] map;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int tc = 1;
		
		while(true) {
			N = Integer.parseInt(input.readLine());
			if(N == 0) break;
			
			map = new int[N][N];
			
			for(int r=0; r<N; r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			
			int min_cost = bfs();
			sb.append("Problem "+ tc++ + ": "+min_cost+"\n");
			
		}
		
		System.out.println(sb);
	}
	
	public static int bfs() {
		int[][] min_rupy = new int[N][N];
		for(int[] i : min_rupy) {
			Arrays.fill(i, Integer.MAX_VALUE);
		}
		
		Queue<rupy_sum> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.offer(new rupy_sum(0, 0, map[0][0]));
		min_rupy[0][0] = map[0][0];
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			rupy_sum cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int dr = cur.r + dy[i];
				int dc = cur.c + dx[i];
				
				if(!isIn(dr, dc)) continue;
				
				int new_sum = cur.sum + map[dr][dc];
				int cur_sum = min_rupy[dr][dc];
				
				if(new_sum < cur_sum) {
					queue.offer(new rupy_sum(dr,dc,new_sum));
					visited[dr][dc] = true;
					min_rupy[dr][dc] = new_sum;
					
				}
				
			}
			
		}
		
		return min_rupy[N-1][N-1];
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
	static class rupy_sum{
		int r, c, sum;

		public rupy_sum(int r, int c, int sum) {
			this.r = r;
			this.c = c;
			this.sum = sum;
		}
		
	}
}
