package Boj_1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N; //���� ����
	static int M; // ���� ����
	static int V; // ������
	static int[][] check; // ���� ���� ����
	static boolean[] visited; //�湮 ����
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		
		check = new int[N+1][N+1]; 
		visited = new boolean[N+1]; 
		
		//���� ������� ����
		for(int i=0; i<M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			check[x][y] = check[y][x] = 1;  
		}
		sc.close();
		dfs(V);
		
		visited = new boolean[N+1]; //Ȯ�λ��� �ʱ�ȭ
		System.out.println();
		
		bfs();
	}
	
	public static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+ " ");
		
		for(int i =1; i <= N; i++) {
			if(check[v][i] == 1 && visited[i] ==false ) { //v�� i�� ����Ǿ��ְ�, i�� �湮�� ���� ���ٸ�
				dfs(i);
			}
		}
	}
	
	public static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(V); // �������� Queue�� ����
		visited[V] = true;
		System.out.print(V+" ");
		
		//Queue�� ����� ������ �ݺ�, �湮 ���� Ȯ��, ��� �� Queue�� �־� ������� Ȯ��
		while(!queue.isEmpty()) {
			int tmp = queue.poll(); 
			
			for(int i=1; i<= N; i++) {
				if(check[tmp][i] == 1 && visited[i] ==false ) {
					queue.offer(i);
					visited[i] = true;
					System.out.print(i+" ");
				}
			}
		}
	}

}
