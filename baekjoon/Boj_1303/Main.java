package Boj_1303;

import java.util.Scanner;

public class Main {
	static int N; //���� ũ��
	static int M; //���� ũ��
	static char[][] map;
	static boolean[][] visited;
	static int count=0, B=0, W=0; 
	
	// �����¿� ���� üũ���� ����� �迭(��, ��, ��, �� ���� )
	static int dy[] = {1, 0, -1, 0}; 
	static int dx[] = {0, 1, 0, -1}; 
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[M][N];
		visited = new boolean[M][N];
		
		// ���� �Է¹ޱ�
		for(int i=0; i<M; i++) {
			String str = sc.next();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		sc.close();
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] == false) {
					char color = map[i][j];
					count = 1;
					dfs(color, i, j);
					
					if(color=='W')
						W += (count*count);
					else
						B+=(count*count);
				}
			}
		}
		
		System.out.println(W+" "+B);
		
		
	}
	static void dfs(char color, int i, int j) {
		visited[i][j] = true;
		
		for(int k=0; k<dx.length; k++) {
			int nx = i + dx[k]; // ���������� �̵��� ���� ��ġ
			int ny = j + dy[k];
			
			// �迭 ���� ������� Ȯ��
			if(nx >= M || nx <0 || ny>=N || ny<0)
				continue;
			if(visited[nx][ny])
				continue;
			if(map[nx][ny] != color)
				continue;
			
			count++;
			dfs(color, nx, ny); // ���� ��ġ���� �� �����¿쿡 ���� ���� �ֳ� Ž��
			
		}
	}
}
