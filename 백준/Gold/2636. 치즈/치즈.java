import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R, C;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1}; // 상하좌우
	static int[] dy = {-1, 1, 0, 0};
	static int answerCnt, totalCheeseCnt, time;
	public static void main (String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		for(int r=0; r<R; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == 1) totalCheeseCnt++; 
 			}
		}
	    
        // 치즈가 다 녹을 때까지 반복
		while(totalCheeseCnt >0 ) {
			answerCnt = totalCheeseCnt;
			Queue<Point> cheeses = bfs(0,0);
			time++;
			if(cheeses.size() > 0) {
				totalCheeseCnt -= cheeses.size();
				while(!cheeses.isEmpty()) {
					Point tmp = cheeses.poll();
					map[tmp.r][tmp.c] = -1;
				}
			}
		}
		
		System.out.println(time);
		System.out.print(answerCnt);
	}
	
	static Queue<Point> bfs(int r, int c) {
		// 1. 준비물 - 큐, 리스트
		Queue<Point> queue = new LinkedList<>();
		Queue<Point> sideCheeses = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(new Point(r,c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<dx.length; i++) {
				int dr = cur.r + dy[i];
				int dc = cur.c + dx[i];
				
				if(!isIn(dr, dc)) continue;
				
				if(visited[dr][dc]) continue;
				
				if(map[dr][dc] == 1) {
					sideCheeses.offer(new Point(dr, dc));
					visited[dr][dc] = true;
					continue;
				}
				if(map[dr][dc] <= 0) {
					queue.offer(new Point(dr,dc));
					map[dr][dc]--;
					visited[dr][dc] = true;
				}
			}
		}
		return sideCheeses;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<R && c<C);
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}
