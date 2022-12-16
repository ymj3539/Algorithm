package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17836_���ִ������ض� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,M,T;
	static int[][] map;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int minTime;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[N+1][M+1];
		minTime = Integer.MAX_VALUE;
		
		for(int r=1; r<=N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=1; c<=M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		
		int answer = bfs();
		System.out.println(answer == Integer.MAX_VALUE ? "Fail" : answer);
		
	}
	
	static int bfs() {
		Queue<Man> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[N+1][M+1][2];
		Man man = new Man(1,1,0);
		queue.offer(man);
		visited[1][1][0] = true;
		
		int time = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				Man cur = queue.poll();
				
				// ���ֿ��� �������� �� �ּ� �ð� ����
				if(cur.r == N && cur.c == M) {
					minTime = Math.min(time, minTime);
				}
				
				for(int d=0; d<4; d++) {
					int dr = cur.r + dy[d];
					int dc = cur.c + dx[d];
					
					// ����üũ
					if(!isIn(dr,dc)) continue;
					// �湮üũ
					if(visited[dr][dc][cur.gum]) continue;
					// ���̰� ���� ����
					if(map[dr][dc] == 1 && cur.gum == 0) continue;
					
					// ���� ���� �� 
					if(cur.gum == 1) {
						queue.offer(new Man(dr,dc,1));
						visited[dr][dc][1] = true;
					}
					
					// ���� ���� ��
					else {
						// ������ġ�� ���� ��
						if(map[dr][dc] == 2) {
							queue.offer(new Man(dr,dc,1));
							visited[dr][dc][1] = true;
							
						}
						// ���� ��ġ�� ���� �ƴҶ� 
						else {
							queue.offer(new Man(dr,dc,0));
							visited[dr][dc][0] = true;
						}
					}
				}
			}
			time++;
			if(time > T) break;
		}
		
		
		return minTime;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=N && c<=M);
	}
	
	static class Man {
		int r,c;
		int gum;
		
		public Man(int r, int c, int gum) {
			this.r = r;
			this.c = c;
			this.gum = gum;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + "), gum=" + gum;
		}
		
		
	}
}
