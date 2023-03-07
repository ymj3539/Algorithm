package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_18111_����ũ����Ʈ {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, B;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int height;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				
			}
		}
		
		height = 0;
		int h = 0; // ����
		for(int i=0; i<=256; i++) {
			int b = B;
			h = i;
			int time = 0;
			// ���� �� ����
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					// ���̰� ���� ��
					if(map[r][c] == h) continue;
					// �� ���̰� �־��� ���̺��� Ŭ ��(������� �� �κ��� �߰�)
					else if(map[r][c] > h) {
						time += ((map[r][c] - h) * 2); // �ð� ���
						b += (map[r][c] - h);
					}
				}
			}
			
			boolean flag = true;
			
			outer : for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					// ���̰� ���� ��
					if(map[r][c] == h) continue;
					// �� ���̰� �־��� ���̺��� ���� ��(�κ����� ��� ������)
					else if(map[r][c] < h) {
						if(b < (h - map[r][c])) {
							flag = false;
							break outer; // �κ��� ������� �Ѿ
						}else {
							// �κ��� ������� ������ 2�� �۾� ����
							time += (h - map[r][c]); // �ð� ���
							b -= (h - map[r][c]);
						}
						
					}
				}
			}
			
			// �ð� ���
			if(flag) {
				if(min > time) {
					min = time;
					height = h;
				}else if(min == time) {
					height = Math.max(height, h);
				}
			}
		}
		
		System.out.println(min+" "+height);
	}
}
