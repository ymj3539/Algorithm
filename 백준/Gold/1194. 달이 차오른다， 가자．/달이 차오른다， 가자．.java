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
	
	static int N, M;
	static char[][] map;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static Queue<Minsik> queue;
	static boolean[][][] visited;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		queue = new LinkedList<>();
		visited = new boolean[1<<6][N][M];
		map = new char[N][M];
		
		for(int r=0; r<N; r++) {
			String str = input.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == '0') {
					queue.offer(new Minsik(r, c, 0));
					visited[0][r][c] = true;
				}
			}
		}
		
		bfs();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void bfs() {
		
		int depth = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- >0) {
				Minsik cur = queue.poll();
				
				if(map[cur.r][cur.c] == '1') {
					min = Math.min(min, depth);
					return;
				}
				
				for(int i =0; i<dx.length; i++) {
					int nr = cur.r + dy[i];
					int nc = cur.c + dx[i];
					
					// 범위 체크
					if(!isIn(nr, nc)) continue;
					
					// 방문 체크
					if(visited[cur.keyStatus][nr][nc]) continue;
					
					// 벽이면 통과 불가
					if(map[nr][nc] == '#') continue;
					
					// 알파벳 대문자 만났을때 key가 없으면 통과 불가
					if('A' <= map[nr][nc] && map[nr][nc] <= 'F' && !(cur.haveKey(map[nr][nc])))  continue;
					
					
					// 통과 가능! (만약 열쇠가 있는 위치라면 열쇠를 줍기전 상태로 방문체크)
					visited[cur.keyStatus][nr][nc] = true;
					
					// 키를 만나면 처리 (keyStatus update)
					int tmpStatus = cur.keyStatus;
					if('a' <= map[nr][nc] && map[nr][nc] <= 'f' ) {
						// 상태 업데이트
						tmpStatus |= (1 << map[nr][nc]-'a');
					}
					queue.offer(new Minsik(nr, nc, tmpStatus));
				}
			}
			
			depth++;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}
	
	static class Minsik{
		int r, c, keyStatus;

		public Minsik(int r, int c, int keyStatus) {
			this.r = r;
			this.c = c;
			this.keyStatus = keyStatus;
		}
		
		public boolean haveKey(char door) {
			return ( (keyStatus & (1 << (door - 'A'))) > 0 ); 
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ", " + keyStatus + ")";
		}
	}
}
