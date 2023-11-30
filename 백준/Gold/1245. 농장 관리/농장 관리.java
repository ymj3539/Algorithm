import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] map;
	static int answer;
	static boolean[][] visited;
	static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	static int[] dx = {1, -1, 0, 0, 1, -1, -1, 1};
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		answer = 0; // 총 산봉우리 수 
		visited = new boolean[N][M];	// 산봉우리 방문체크
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		// 좌표 전부 돌면서 각 좌표가 산봉우리가 될 수 있는지 체크
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				// 이미 산봉우리면 패스
				if(!visited[r][c]) {
					boolean check = bfs(r, c);
					if(check) answer++;
				}
			}
		}
		
		System.out.println(answer);
		
	}
	static boolean bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(sr, sc));
		
		visited[sr][sc] = true;
		int top = map[sr][sc];
		boolean check = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int d=0; d<8; d++) {
				int dr = cur.r + dy[d];
				int dc = cur.c + dx[d];
				
				if(!isIn(dr,dc)) continue;
				
				// 현재 높이보다 높을 때 --> 산봉우리 될 수 없음(return)
				if(map[dr][dc] > top) check = false;
				
				// 현재 높이보다 작을 때
				else if(map[dr][dc] < top) continue;
				
				// 현재 높이와 같을 때
				else if(map[dr][dc] == top && !visited[dr][dc]) {
					queue.offer(new Point(dr,dc));
					visited[dr][dc] = true;
					
				}
				
			}
		}
		return check;
	}
	
	static boolean isIn(int r, int c) {
		return (r>= 0 && c>=0 && r<N && c<M);
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