
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
	static char[][] map;
	static int N, M;
	static int[] dy = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new char[N][M];
		Ball redBall = null;
		Ball blueBall = null;
		
		for(int r=0; r<N; r++) {
			String str = input.readLine();
			for(int c=0; c<M; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == 'R') {
					redBall = new Ball(r, c, true);
				} else if(map[r][c] == 'B') {
					blueBall = new Ball(r, c, false);
				}
			}
		}
		
		Board board = new Board(redBall, blueBall);
		bfs(board);
	}
	
	
	
	private static void bfs(Board board) {
		Queue<Board> queue = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		Ball startR = board.redBall;
		Ball startB = board.blueBall;
		visited[startR.r][startR.c][startB.r][startB.c] = true;
		
		queue.offer(board);
		int depth = 1;
		while(!queue.isEmpty()) {
			// depth 체크
			if(depth>10) {
				System.out.println(0);
				return;
			}
			int size = queue.size();
			
			while(size-->0) {
				Board cur = queue.poll();
				
				for(int d =0; d<4; d++) {
					Ball curRed = new Ball(cur.redBall.r, cur.redBall.c, true);
					Ball curBlue = new Ball(cur.blueBall.r, cur.blueBall.c, false);
					
					int nr = curRed.r + dy[d];
					int nc = curRed.c + dx[d];
					
					
					// 빈칸일때 -> 굴려
					// cur 위치로 먼저 굴려야하는 공 판단
					// 위 (r이 더 작은거 먼저)
					boolean checkR = true;
					boolean checkB = true;
					
					if(d == 0) {
						// 빨간공의 r이 더 작으면
						if(curRed.r < curBlue.r) {
							// 빨간공 굴려
							checkR = curRed.move(d);
							// 파란공 굴려
							checkB = curBlue.move(d);
							
						}else {
							checkB = curBlue.move(d);
							checkR = curRed.move(d);
						}
					}
					// 아래 (r이 큰거 먼저)
					else if(d==1) {
						if(curRed.r > curBlue.r) {
							// 빨간공 굴려
							checkR = curRed.move(d);
							// 파란공 굴려
							checkB = curBlue.move(d);
							
						}else {
							checkB = curBlue.move(d);
							checkR = curRed.move(d);
						}
					}
					// 좌 (c가 작은거 먼저)
					else if(d==2) {
						if(curRed.c < curBlue.c) {
							// 빨간공 굴려
							checkR = curRed.move(d);
							
							// 파란공 굴려
							checkB = curBlue.move(d);
							
						}else {
							checkB = curBlue.move(d);
							checkR = curRed.move(d);
						}
					}
					// 우 (c가 큰거 먼저)
					else if(d==3) {
						if(curRed.c > curBlue.c) {
							// 빨간공 굴려
							checkR = curRed.move(d);
							// 파란공 굴려
							checkB = curBlue.move(d);
							
						}else {
							checkB = curBlue.move(d);
							checkR = curRed.move(d);
						}
					}
					//// 굴리기 끝
					if(map[curRed.r][curRed.c] == '#') {
						map[curRed.r][curRed.c] = '.';
					}
					
					if(map[curBlue.r][curBlue.c] == '#') {
						map[curBlue.r][curBlue.c] = '.';
					}
					
					
					// 파란공이 true면 무조건 실패
					if(checkB) continue;
					else {
						if(checkR) { // Blue - false, Red - true 면 성공
							System.out.println(1);
							return; 
						}else { // 다 아니면 queue에 넣자
							
							
							// 방문체크
							if(visited[curRed.r][curRed.c][curBlue.r][curBlue.c]) continue;
							else {
								
								visited[curRed.r][curRed.c][curBlue.r][curBlue.c] = true;
								queue.offer(new Board(new Ball(curRed.r, curRed.c, true), new Ball(curBlue.r, curBlue.c, false)));
							}
						}
					}
					
				}
			}
			depth++;
		}
		System.out.println(0);
		return;
		
	}

	static class Board{
		Ball redBall, blueBall;

		public Board(Ball redBall, Ball blueBall) {
			this.redBall = redBall;
			this.blueBall = blueBall;
		}
		
		
	}

	static class Ball {
		int r, c;
		boolean isRed;
		
		boolean move(int d) {
			// 원래 위치 지우기
			int nr = this.r;
			int nc = this.c;
			map[nr][nc] = '.';
			while(true) {
				nr += dy[d];
				nc += dx[d];
				
				if(map[nr][nc] == '#') {
					nr -= dy[d];
					nc -= dx[d];
					break;
				}
				
				if(map[nr][nc] =='O') {
					this.r = nr;
					this.c = nc;
					return true;
				}
			}
			this.r = nr;
			this.c = nc;
			// 이동 후 위치 표시
			map[this.r][this.c] = '#'; // 공이 자리 차지하고 있음을 나타내기 위한 벽

			return false;
		}

		public Ball(int r, int c, boolean isRed) {
			this.r = r;
			this.c = c;
			this.isRed = isRed;
		}
		
		
	}
}
