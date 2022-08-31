package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4179_�� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R, C;
	static char[][] map;
	static int[][] visitedF;	// �� �湮ǥ��
	static int[][] visitedJ;	// ������ �湮ǥ��
	static int[] dx = {1, 0, -1, 0};	// �� �� �� ��
	static int[] dy = {0, 1, 0, -1};
	static String answer;
	static Queue<Point> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R][C];
		visitedF = new int[R][C];
		visitedJ = new int[R][C];
		
		Point fire = null;
		Point JH = null;
		
		String str;
		for(int r =0; r<R; r++) {
			str = input.readLine();
			for(int c =0; c<C; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c] == 'F') {
					fire =  new Point(r,c,'F',1);
					queue.offer(fire);
					visitedF[fire.r][fire.c] = fire.cnt;
				}
				if(map[r][c] == 'J') {
					JH = new Point(r,c, 'J',1);
				}
			}
		}
		// �Է³�
		
		bfs(JH);
		System.out.println(answer);
	}
	
	static void bfs(Point JH) {
		queue.offer(JH);
		visitedJ[JH.r][JH.c] = JH.cnt;
		int fr;
		int fc;
		int jr;
		int jc;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
//			System.out.println(current.toString());
			if(current.type == 'F') {
				map[current.r][current.c] = 'F';
				
				for(int i=0; i<4; i++) {
					fr = current.r + dy[i];
					fc = current.c + dx[i];
					
					// map�� ���� ��, ���� �湮���� ����, #�� �ƴ� ���� ��⿭�� ����
					if(isIn(fr,fc) && visitedF[fr][fc] == 0 && !(map[fr][fc] == '#')) {
						queue.offer(new Point(fr, fc, 'F', current.cnt+1));
						visitedF[fr][fc] = current.cnt+1;
					}
				}
			// current.type = 'B' �� ��
			}else {
				for(int i=0; i<4; i++) {
					jr = current.r + dy[i];
					jc = current.c + dx[i];
					
					// ������ Ż���ϸ� Ż��ð� ���
					if(!isIn(jr,jc)) {
						answer = String.valueOf(current.cnt);
						return;
					// ���� ���� �̵��� ���� '.'�̰�, ���� �湮���� �ʾ����� ��⿭�� ����
					}else if(isIn(jr,jc) && map[jr][jc] == '.' && visitedJ[jr][jc] == 0) {
						// ���� ���� �湮���� �ʾҰų�, ���� �ܰ躸�� �ʰ� �����ϴ� ���̸� Go
						if(visitedF[jr][jc] == 0 || visitedF[jr][jc] > current.cnt+1)
						queue.offer(new Point(jr, jc, 'J', current.cnt+1));
						visitedJ[jr][jc] = current.cnt+1;
					}
				}
			}
		}
		answer = "IMPOSSIBLE";
		
	}
	
	public static class Point{
		int r;
		int c;
		char type;
		int cnt;
		
		Point(int r, int c, char type, int cnt){
			this.r = r;
			this.c = c;
			this.type = type;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", type=" + type + ", cnt=" + cnt + "]";
		}

		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r< R && c<C);
	}
}
