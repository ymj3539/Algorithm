package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4963_섬의개수 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int h;
	static int w;
	static int[][] map;
	static int[] dy = {-1,-1,  0, 1, 1,  1,  0, -1 };
	static int[] dx = {0,  1,  1, 1, 0, -1, -1, -1};
	static boolean[][] visited;	// 방문 기록
	static int cnt;	// 섬의 개수
	static int answer;
	
	public static void main(String[] args) throws IOException {
		while(true) {
			tokens = new StringTokenizer(input.readLine());
			w = Integer.parseInt(tokens.nextToken());
			h = Integer.parseInt(tokens.nextToken());
			
			visited = new boolean[h][w];
			cnt =0;
			answer =0;
			
			if(w ==0 && h==0) break;
			
			map = new int[h][w];
			for(int r=0; r<h; r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0; c<w; c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			bfs();
			
			/// 각각의 tc에 대해 출력
			sb.append(cnt+"\n");
			
		}
		System.out.println(sb);
		
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		// map을 돌면서 아직 방문하지 않은 1인 지점 찾기
		for(int a=0; a<h; a++) {
			for(int b=0; b<w; b++) {
				if(visited[a][b] == false && map[a][b] ==1) {
					// 조건과 일치하는 좌표(시작점)을 queue 에 넣음
					queue.offer(new Point(a,b));
					
					// 연속된 1이 존재하지 않을때까지 반복
					while(!queue.isEmpty()) {
						Point current = queue.poll();
						int r = current.r;
						int c = current.c;
						
						// 상하좌우,대각선 탐색
						for(int i=0; i<8; i++) {
							int nr = r + dy[i];
							int nc = c + dx[i];
							if(isIn(nr, nc) && map[nr][nc] == 1) {
								queue.offer(new Point(nr, nc));
								visited[nr][nc] = true;
								
							}
						}
					}
					
					cnt++;
				}
			}
		}
		
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<h && c<w && visited[r][c] == false);
	}
	
	public static class Point {
		int r;
		int c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
