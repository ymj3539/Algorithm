import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int[] move;
	static Dice dice;
 	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		x = Integer.parseInt(tokens.nextToken());
		y = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		move = new int[K];
		dice = new Dice(x, y, 0, 0, 0, 0, 0, 0);
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0; i<K; i++) {
			move[i] = Integer.parseInt(tokens.nextToken());
		}
		/////// 입력 끝
		
		// 이동명령 돌아
		for(int i=0; i<move.length; i++) {
			int dir = move[i];
			// 주사위 좌표 갱신
			int ddx = dice.x + dx[dir];
			int ddy = dice.y + dy[dir];
			
			if(!isIn(ddx, ddy)) continue;
			dice.x = ddx;
			dice.y = ddy;
			
			// 주사위 굴려(위치값 변경)
			if(dir == 1) {
				dice.moveE();
			}else if(dir == 2) {
				dice.moveW();
			}else if(dir == 3) {
				dice.moveN();
			}else if(dir == 4) {
				dice.moveS();
			}
			
			// 윗면 값 출력
			sb.append(dice.up+"\n");
			
			// 바닥값 비교, 갱신
			if(map[dice.x][dice.y] == 0) {
				map[dice.x][dice.y] = dice.down;
			}else {
				dice.down = map[dice.x][dice.y];
				map[dice.x][dice.y] = 0;
			}
			
		}
		
		System.out.println(sb);
		
	}

 	
 	
	static class Dice{
 		int x, y;
 		int up, down, dirE, dirW, dirN, dirS;
 		
		public Dice(int x, int y, int up, int down, int dirE, int dirW, int dirN, int dirS) {
			this.x = x;
			this.y = y;
			this.up = up;
			this.down = down;
			this.dirE = dirE;
			this.dirW = dirW;
			this.dirN = dirN;
			this.dirS = dirS;
		}
		
		@Override
		public String toString() {
			return "Dice [x=" + x + ", y=" + y + ", up=" + up + ", down=" + down + ", dirE=" + dirE + ", dirW=" + dirW
					+ ", dirN=" + dirN + ", dirS=" + dirS + "]";
		}
 		
 		
 		
 		// 1. 동쪽 이동 메소드
 		public void moveE() {
 			int tmp = this.dirE;
 			this.dirE = this.up;
 			this.up = this.dirW;
 			this.dirW = this.down;
 			this.down = tmp;
 			
 		}
 		
 		// 2. 서쪽 이동 메소드
 		public void moveW() {
 			int tmp = this.dirE;
 			this.dirE = this.down;
 			this.down = this.dirW;
 			this.dirW = this.up;
 			this.up = tmp;
 		}

 		// 3. 북쪽 이동 메소드
 		public void moveN() {
 			int tmp = this.up;
 			this.up = this.dirS;
 			this.dirS = this.down;
 			this.down = this.dirN;
 			this.dirN = tmp;
 		}
 		
 		// 4. 남쪽 이동 메소드
 		public void moveS() {
 			int tmp = this.up;
 			this.up = this.dirN;
 			this.dirN = this.down;
 			this.down = this.dirS;
 			this.dirS = tmp;
 		}
 	}
 	
 	static boolean isIn(int r, int c) {
 		return (r>=0 && c>=0 && r<N && c<M);
 	}
}
