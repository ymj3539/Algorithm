package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14503_�κ�û�ұ� {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static Robot robot;
	static int ans;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(input.readLine());
		int r = Integer.parseInt(tokens.nextToken());
		int c = Integer.parseInt(tokens.nextToken());
		int d = Integer.parseInt(tokens.nextToken());
		
		robot = new Robot(r, c, d);
		
		map = new int[N][M];
		ans = 0;
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		boolean flag = true;
		outer: while(true) {
			if(flag) {
				// �۵� 1
				robot.clean();
			}
			
			flag = false;
			int leftDir = robot.d -1;
			// �۵� 2
			for(int i=0; i<4; i++) {
				// cd : ��������� ���� ����
				if(leftDir < 0) leftDir = 3;
				
				int dr = robot.r + dy[leftDir];
				int dc = robot.c + dx[leftDir];
			    
				// �۵� 2-1
				if(map[dr][dc] == 0) { // û�� ���� ��ĭ : 0
					// ���� �޼ҵ�
					robot.go(leftDir);
					flag = true;
					break;
				} 
				leftDir -= 1;
			}
			
			if(!flag) {
				// �۵� 2-3,4 	
				int backDir =0 ;
				if(robot.d <= 1) {
					backDir = robot.d + 2; 
				}
				else {
					backDir = robot.d -2;
				}
				int br = robot.r + dy[backDir];
				int bc = robot.c + dx[backDir];
				// ���� ���� ���� ���̸� ����
				if(map[br][bc] == 1) {
					break outer;
				}else {
					robot.r = br;
					robot.c = bc;
					flag = false;
				}
			}
			
		}
		
		System.out.println(ans);
		
	}
	
	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ", d=" + d + ")";
		}
		
		// û�Ҹ޼ҵ�
		public void clean() {
			map[this.r][this.c] = 2;
			
			ans++;
		}
		
		// �����޼ҵ�
		public void go(int dir) {
			// ȸ��
			this.d = dir;
			// ����
			this.r += dy[dir];
			this.c += dx[dir];
	
		}
	}
	
}
