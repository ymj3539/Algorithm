package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4963_���ǰ��� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int h;
	static int w;
	static int[][] map;
	static int[] dy = {-1,-1,  0, 1, 1,  1,  0, -1 };
	static int[] dx = {0,  1,  1, 1, 0, -1, -1, -1};
	static boolean[][] visited;	// �湮 ���
	static int cnt;	// ���� ����
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
			
			/// ������ tc�� ���� ���
			sb.append(cnt+"\n");
			
		}
		System.out.println(sb);
		
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		// map�� ���鼭 ���� �湮���� ���� 1�� ���� ã��
		for(int a=0; a<h; a++) {
			for(int b=0; b<w; b++) {
				if(visited[a][b] == false && map[a][b] ==1) {
					// ���ǰ� ��ġ�ϴ� ��ǥ(������)�� queue �� ����
					queue.offer(new Point(a,b));
					
					// ���ӵ� 1�� �������� ���������� �ݺ�
					while(!queue.isEmpty()) {
						Point current = queue.poll();
						int r = current.r;
						int c = current.c;
						
						// �����¿�,�밢�� Ž��
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
