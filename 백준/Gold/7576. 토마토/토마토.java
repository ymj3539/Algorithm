import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int M; // 가로 
	static int N; // 세로
	static int greenCnt; // 안 익은 토마토 개수
	static int[][] map;
	static boolean[][] visited;
	static Queue<Tomato> queue = new LinkedList<>();
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		greenCnt = 0;
		
		for(int r=0; r<N; r++){
			tokens = new StringTokenizer(input.readLine());
			
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken()); 
				if(map[r][c] == 1) {
					queue.offer(new Tomato(r,c));
					visited[r][c] = true;
 				}else if(map[r][c] == 0) {
 					greenCnt++;
 				}
			}
		}
		
		
		if(greenCnt == 0) {
			System.out.println(0);
		}else {
			int day = bfs();
			System.out.println(day);
		}
		
		
	}
	
	public static int bfs() {
		int day = -1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Tomato cur = queue.poll();
				
				for(int d =0; d<4; d++) {
					int dr = cur.r + dy[d];
					int dc = cur.c + dx[d];
					
					if(!isIn(dr, dc)) continue;
					if(visited[dr][dc]) continue;
					if(map[dr][dc] == -1) continue;
					
					queue.offer(new Tomato(dr, dc));
					visited[dr][dc] = true;
					greenCnt--;
				}
			}
			day++;
		}
		if(greenCnt > 0) {
			return -1;
		}
		return day;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}

	static class Tomato {
		int r;
		int c;
		public Tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}
