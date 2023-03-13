import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int K, W, H;
	static int[][] map;
	static int[] dyM = {-1, 0, 1, 0};
	static int[] dxM = {0, 1, 0, -1};
	static int[] dyH = {-2, -1, 1, 2, 2, 1 ,-1, -2};
	static int[] dxH = {1, 2, 2, 1, -1, -2, -2, -1};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		
		map = new int[H][W];
		for(int r=0; r<H; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<W; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		bfs();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void bfs() {
		Queue<Monkey> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[H][W][K+1];
		
		queue.offer(new Monkey(0, 0, 0));
		visited[0][0][0] = true;
		
		int depth = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- > 0) {
				Monkey cur = queue.poll();
				if(cur.r == H-1 && cur.c == W-1) {
					min = Math.min(min, depth);
					return;
				}
				
				// 아직 말 이동 횟수가 남았다면 말로 이동하는 경우 queue에 넣어주기
				if(cur.moveCnt < K) {
					for(int i=0; i<dxH.length; i++) {
						int dr = cur.r + dyH[i];
						int dc = cur.c + dxH[i];
						if(!isIn(dr, dc)) continue;
						if(visited[dr][dc][cur.moveCnt+1]) continue;
						if(map[dr][dc] == 1) continue;
						
						queue.offer(new Monkey(dr, dc, cur.moveCnt+1));
						visited[dr][dc][cur.moveCnt+1] = true;
					}
				}
				
				// 원숭이로 이동하는 경우 queue에 넣기
				for(int i=0; i<dxM.length; i++) {
					int dr = cur.r + dyM[i];
					int dc = cur.c + dxM[i];
					if(!isIn(dr, dc)) continue;
					if(visited[dr][dc][cur.moveCnt]) continue;
					if(map[dr][dc] == 1) continue;
					
					queue.offer(new Monkey(dr, dc, cur.moveCnt));
					visited[dr][dc][cur.moveCnt] = true;
					
				}
			}
			depth++;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<H && c<W);
	}
	
	static class Monkey{
		int r, c, moveCnt;

		public Monkey(int r, int c, int moveCnt) {
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
		}

		@Override
		public String toString() {
			return "Monkey [r=" + r + ", c=" + c + ", moveCnt=" + moveCnt + "]";
		}
	}
}
