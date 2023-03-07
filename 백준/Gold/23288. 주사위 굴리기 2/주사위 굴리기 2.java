import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] map;
	static int N, M, K;
	static int[] dy = {0, 1, 0, -1}; // 동 남 서 북
	static int[] dx = {1, 0, -1, 0};
	static Dice dice;
	static int answer;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		answer = 0;
		dice = new Dice(0,0,0,1,6,3,4,2,5);
		
		// K만큼 이동
		for(int i=0; i<K; i++) {
			// 1. 이동 방향으로 한 칸 구르기
			go();
			
			// 2. 점수 획득
			int B = map[dice.r][dice.c];
			// 연속으로 갈 수 있는 칸 수 구하기
			int C = getCnt(B);
			int score = C * B;
			
			answer += score;
			
			// 3. 이동 방향 결정
			// A>B
			if(dice.bottom > B) {
				dice.dir = dice.dir + 1;
				if(dice.dir >3) dice.dir = dice.dir - 4;
				
			}
			// A<B
			if(dice.bottom < B) {
				dice.dir = dice.dir - 1;
				if(dice.dir < 0) dice.dir = dice.dir + 4;
			}
		}
		
		System.out.println(answer);
	}
	
	static void go() {
		// 이동 방향으로 갈 수 있는지 좌표 체크
		int dr = dice.r + dy[dice.dir];
		int dc = dice.c + dx[dice.dir];
		// 갈 수 없으면 방향 전환
		if(!isIn(dr,dc)) {
			// 동-> 서
			if(dice.dir == 0) dice.dir = 2;
			// 서 -> 동
			else if(dice.dir == 2) dice.dir = 0;
			// 남 -> 북
			else if(dice.dir == 1) dice.dir = 3;
			// 북 -> 남
			else if(dice.dir == 3) dice.dir = 1;
		}
		
		// 동쪽 이동
		if(dice.dir == 0) {
			dice.moveE();
		}
		// 남쪽 이동
		else if(dice.dir == 1) {
			dice.moveS();
		}
		// 서쪽 이동
		else if(dice.dir == 2) {
			dice.moveW();
		}
		// 북쪽 이동
		else if(dice.dir == 3) {
			dice.moveN();
		}
		
		
	}
	
	static int getCnt(int B) {
		int y = dice.r;
		int x = dice.c;
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Point(y, x));
		visited[y][x] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			cnt++;
			
			for(int i=0; i<4; i++) {
				int dr = cur.r + dy[i];
				int dc = cur.c + dx[i];
				
				if(!isIn(dr,dc)) continue;
				if(visited[dr][dc]) continue;
				if(map[dr][dc] != B) continue;
				
				queue.offer(new Point(dr,dc));
				visited[dr][dc] = true;
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<M);
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	// 주사위 객체
	static class Dice{
		// 이동 방향
		int dir;
		// 현재 위치
		int r, c;
		// 주사위 값
		int top, bottom, right, left, front, back;
		
		public Dice(int dir, int r, int c, int top, int bottom, int right, int left, int front, int back) {
			super();
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.top = top;
			this.bottom = bottom;
			this.right = right;
			this.left = left;
			this.front = front;
			this.back = back;
		}

		// 동쪽으로 이동
		public void moveE() {
			int tmp = this.top;
			this.top = this.left;
			this.left = this.bottom;
			this.bottom = this.right;
			this.right = tmp;
			
			// 좌표 변경
			this.r = this.r + dy[0];
			this.c = this.c + dx[0];
		}
		
		// 서쪽으로 이동
		public void moveW() {
			int tmp = this.top;
			this.top = this.right;
			this.right = this.bottom;
			this.bottom = this.left;
			this.left = tmp;
			
			// 좌표 변경
			this.r = this.r + dy[2];
			this.c = this.c + dx[2];
		}
		
		// 북쪽으로 이동
		public void moveN() {
			int tmp = this.top;
			this.top = this.back;
			this.back = this.bottom;
			this.bottom = this.front;
			this.front = tmp;
			
			// 좌표 변경
			this.r = this.r + dy[3];
			this.c = this.c + dx[3];
		}
		
		// 남쪽으로 이동
		public void moveS() {
			int tmp = this.top;
			this.top = this.front;
			this.front = this.bottom;
			this.bottom = this.back;
			this.back = tmp;
			
			// 좌표 변경
			this.r = this.r + dy[1];
			this.c = this.c + dx[1];
		}
		
	}
}