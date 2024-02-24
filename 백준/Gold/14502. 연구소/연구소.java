import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, M;
	static int[][] map;
	static List<Point> blanks = new ArrayList<>();
	static List<Point> virus = new ArrayList<>();
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int safeMax = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c] == 0) {
					blanks.add(new Point(r,c));
				}
				if(map[r][c] == 2) {
					virus.add(new Point(r,c));
				}
			}
		}
		
		makeCombi(0, new Point[3], 0);
		
//		for(int[] i : map) {
//			System.out.println(Arrays.toString(i));
//		}
		
		System.out.println(safeMax);
	}
	
	static void makeCombi(int index, Point[] choosed, int startIdx) {
		if(index == choosed.length) {

//			System.out.println(Arrays.toString(choosed));
			int[][] copymap = new int[N][M];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					copymap[i][j] = map[i][j];
				}
			}
			
			// 선택한 벽 표시 후 bfs
			for(int i=0; i<choosed.length; i++) {
				int r = choosed[i].r;
				int c = choosed[i].c;
				copymap[r][c] = 1;
			}
			
			bfs(copymap);
//			System.out.println("=====bfs 결과=======");
//			for(int[] i : copymap) {
//				System.out.println(Arrays.toString(i));
//			}
			// 0의 갯수 세기
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(copymap[i][j] == 0) {
						cnt++;
					}
				}
			}
			
			safeMax = Math.max(safeMax, cnt);
			
			return;
		}
		
		for(int i= startIdx; i<blanks.size(); i++) {
			choosed[index] = blanks.get(i);
			makeCombi(index+1, choosed, i+1);
		}
	}
	
	static void bfs(int[][] map) {
		// 준비물
		Queue<Point> queue = new LinkedList<>();
		
		for(int i=0; i<virus.size(); i++) {
			queue.offer(virus.get(i));
		}
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<dx.length; i++) {
				int nr = cur.r + dx[i];
				int nc = cur.c + dy[i];
				
				if(isIn(nr, nc) && map[nr][nc] == 0) {
					queue.offer(new Point(nr, nc));
					map[nr][nc] = 1;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
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
