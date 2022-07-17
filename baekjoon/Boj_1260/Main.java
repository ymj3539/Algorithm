package Boj_1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N; //정점 갯수
	static int M; // 간선 갯수
	static int V; // 시작점
	static int[][] check; // 간선 연결 상태
	static boolean[] visited; //방문 여부
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		
		check = new int[N+1][N+1]; 
		visited = new boolean[N+1]; 
		
		//간선 연결상태 저장
		for(int i=0; i<M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			check[x][y] = check[y][x] = 1;  
		}
		sc.close();
		dfs(V);
		
		visited = new boolean[N+1]; //확인상태 초기화
		System.out.println();
		
		bfs();
	}
	
	public static void dfs(int v) {
		visited[v] = true;
		System.out.print(v+ " ");
		
		for(int i =1; i <= N; i++) {
			if(check[v][i] == 1 && visited[i] ==false ) { //v와 i가 연결되어있고, i에 방문한 적이 없다면
				dfs(i);
			}
		}
	}
	
	public static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(V); // 시작점도 Queue에 넣음
		visited[V] = true;
		System.out.print(V+" ");
		
		//Queue가 비어질 때까지 반복, 방문 정점 확인, 출력 후 Queue에 넣어 순서대로 확인
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
