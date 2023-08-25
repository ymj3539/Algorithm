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
	static int ans;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];
		for(int r=1; r<=N; r++) {
			String str = input.readLine();
			for(int c=1; c<=M; c++) {
				map[r][c] = (str.charAt(c-1)-'0');
			}
		}
		
//		for(int[] i: map) {
//			System.out.println(Arrays.toString(i));
//		}
		ans = bfs();
		System.out.println(ans);
	}
	
	static int bfs() {
		Queue<Point> queue= new LinkedList<>();
		boolean[][][] visited = new boolean[N+1][M+1][2];

		queue.offer(new Point(1, 1, 0));
		visited[1][1][0] = true;
		int depth = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- >0) {
			
				Point cur = queue.poll();
				int r = cur.r;
				int c = cur.c;
				int bCnt = cur.breakCnt;
				if(r==N && c == M) {
					return depth;
				}
				
				for(int i=0; i<dx.length; i++) {
					int dr = r + dy[i];
					int dc = c + dx[i];
					
					if(!isIn(dr,dc)) continue;
					if(visited[dr][dc][bCnt]) continue;
					// 진행방향에 벽이 있을 때
					if(map[dr][dc] == 1) {
						// 이미 벽을 한번 부쉈다면
						if(bCnt == 1) {
							continue;
						}else {
							// 벽을 한번도 안부쉈다면
							visited[dr][dc][bCnt] = true;
							queue.offer(new Point(dr, dc, bCnt+1));
						}
					}else {
						// 진행방향에 벽이 없을때
						visited[dr][dc][bCnt] = true;
						queue.offer(new Point(dr, dc, bCnt));
					}
					
				}
			}
			depth++;
		}
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=N && c<=M);
	}
	
	static class Point {
		int r, c;
		int breakCnt;

		public Point(int r, int c, int breakCnt) {
			this.r = r;
			this.c = c;
			this.breakCnt = breakCnt;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", breakCnt=" + breakCnt + "]";
		}
		
	
	
	}
}
