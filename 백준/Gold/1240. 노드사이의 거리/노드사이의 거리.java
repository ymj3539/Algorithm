
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[][] tree;
	static int N, M;
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		tree = new int[N+1][N+1];
		
		for(int[] i : tree) {
			Arrays.fill(i, Integer.MAX_VALUE);
		}
		
		
		
		for(int i=0; i<N-1; i++) {
			tokens = new StringTokenizer(input.readLine());
			int n1 = Integer.parseInt(tokens.nextToken());
			int n2 = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			
			tree[n1][n2] = d;
			tree[n2][n1] = d;
			
		}
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int n1 = Integer.parseInt(tokens.nextToken());
			int n2 = Integer.parseInt(tokens.nextToken());
			int d = bfs(n1, n2);
			sb.append(d+"\n");
		}
		
		System.out.println(sb);
		
	}
	
	static int bfs(int n1, int n2) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int sum = 0;
		
		queue.offer(new Node(n1, 0));
		visited[n1] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.n1 == n2) return cur.d;
			
			
			for(int i=1; i<N+1; i++) {
				if(tree[cur.n1][i] == Integer.MAX_VALUE) continue;
				if(visited[i]) continue;
				queue.offer(new Node(i, cur.d + tree[cur.n1][i]));
				visited[i] = true;
				
			}
		}
		return -1;
	}
	
	static class Node{
		int n1, d;

		public Node(int n1, int d) {
			super();
			this.n1 = n1;
			this.d = d;
		}
		
		
	}

}
