package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_2931_������ {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static Point start;
	static List<Integer> emptyPipeDir = new LinkedList<>();
	static Point emptyPoint;
	static char answer;
	static int[] dx = {0, 1, 0, -1}; // ��(0) ��(1) ��(2) ��(3)
	static int[] dy = {-1, 0, 1, 0};
	static int[][] pipes = {
			{},
			{1, 2}, // ������ 1
			{0, 1}, // ������ 2
			{0, 3}, // ������ 3
			{2, 3}, // ������ 4
			{0, 2}, // ������ 5
			{1, 3}, // ������ 6
			{0, 1, 2, 3}, // ������ 7
	}; 
	
	// ���� � ������������ ã�� �޼ҵ�
	static char findPipe() {
		if(emptyPipeDir.size() == 4) return '+';
		else {
			int a = emptyPipeDir.get(0);
			int b = emptyPipeDir.get(1);
			if(a == 1 && b == 2) return '1';
			else if(a == 0 && b == 1) return '2';
			else if(a == 0 && b == 3) return '3';
			else if(a == 2 && b == 3) return '4';
			else if(a == 0 && b == 2) return '|';
			else if(a == 1 && b == 3) return '-';
			
		}
		return 'X';
	}
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new char[R+1][C+1];
		visited = new boolean[R+1][C+1];
		
		for(int r=1; r<=R; r++ ) {
			String str = input.readLine();
			for(int c=1; c<=C; c++) {
				map[r][c] = str.charAt(c-1);
				if(map[r][c] == 'M') {
					start = new Point(r,c);
					visited[r][c] = true;
				}
				if(map[r][c] == '|') map[r][c] = '5';
				if(map[r][c] == '-') map[r][c] = '6';
				if(map[r][c] == '+') map[r][c] = '7';
			}
		}
		// ������ M���� ������ ã��
		for(int i=0; i<dx.length; i++) {
			int dr = start.r + dy[i];
			int dc = start.c + dx[i];
			
			// ���� �� 
			if(!isIn(dr,dc)) continue;
			
			// ��ĭ�̸� �Ѿ
			if(map[dr][dc] == '.') continue;
			
			// �������� ������
			if(map[dr][dc] >='1' && map[dr][dc] <= '7') {
				
				// ������ �� �ִ��� üũ
				if(isPossible ((map[dr][dc] -'0'), i)) {
					
					// ������ �� ������ dfs ����
					visited[dr][dc] = true;
					dfs(new Point(dr,dc));
				}
			}
		}
		
		Collections.sort(emptyPipeDir);
	
		// ���
		System.out.println(emptyPoint.r+" "+emptyPoint.c+" "+findPipe());
	}
	
	static void dfs(Point cur) {
		// ���� �������� Ÿ��
		int t = map[cur.r][cur.c] -'0';
		
		for(int i=0; i<pipes[t].length; i++) {
			// ���� �������� �� �� �ִ� ���� Ž��
			int dir = pipes[t][i];
			int dr = cur.r + dy[dir];
			int dc = cur.c + dx[dir];
			
			// ���� �� 
			if(!isIn(dr, dc)) continue;
			
			// Z�� �����ϰų� �湮�� ���̸�
			if(map[dr][dc] =='Z' || visited[dr][dc]) {
				visited[dr][dc] = true;
				continue;
			}
			
			
			// �������� ������ �� �ڸ��� ������ �������� �ڸ�!
			if(map[dr][dc] == '.') {
				emptyPoint = new Point(dr, dc);
				visited[dr][dc] = true;
				
				// �ֺ��� ���ᰡ���� ������ ã��
				for(int j =0; j<dx.length; j++) {
					int er = dr + dy[j];
					int ec = dc + dx[j];
					if(!isIn(er, ec)) continue;
					
					if(map[er][ec] =='Z') {
						continue;
					}
					
					if(map[er][ec] >='1' && map[er][ec] <= '7') {
						// ������ �� �ִ��� üũ
						if(isPossible ((map[er][ec] -'0'), j)) {
							
							// ������ �� ������ dfs ����
							visited[er][ec] = true;
							emptyPipeDir.add(j);
							dfs(new Point(er,ec));
						}
					}
				}
				continue;
			}
			
			
			// ���� �������� ���� �������� �Ǵ�
			if(isPossible((map[dr][dc] -'0'), dir)) {
				visited[dr][dc] = true;
				dfs(new Point(dr,dc));
			}
		}
	}
	
	// ���������� ������ ��ȣ�� ������ġ������ �̵� ������ �־����� �� ������ �� �ִ��� �Ǵ��ϴ� �Լ�
	static boolean isPossible(int pipeNum, int dir) {
		int[] pipe = pipes[pipeNum];
		for(int i=0; i<pipe.length; i++) {
			// pipe�� �շ��ִ� ����� �� ���� ������ ���� 2�� ���� ����
			if( Math.abs(pipe[i] - dir) == 2 ) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=R && c<=C);
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
