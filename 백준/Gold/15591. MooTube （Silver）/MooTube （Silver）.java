import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, Q;
	static List<Node>[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		
		arr = new List[N+1];
		// arr 내부의 list 초기화
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		// 입력값 list에 추가
		for(int i=0; i<N-1; i++) {
			tokens = new StringTokenizer(input.readLine());
			int p = Integer.parseInt(tokens.nextToken());
			int q = Integer.parseInt(tokens.nextToken());
			int r = Integer.parseInt(tokens.nextToken());
			
			arr[p].add(new Node(q, r));
			arr[q].add(new Node(p, r));
		}
		
		// Q개의 질문 처리
		for(int i=0; i<Q; i++) {
			tokens = new StringTokenizer(input.readLine());
			int k = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			
			sb.append(bfs(k, v)+"\n");
		}
		
		System.out.println(sb);
	}
	
	static int bfs(int k, int v) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		queue.offer(new Node(v, Integer.MAX_VALUE));
		visited[v] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int i=0; i<arr[cur.next].size(); i++) {
				Node node = arr[cur.next].get(i);
				
				if(!visited[node.next] && node.weight >= k) {
					int min = Math.min(node.weight, cur.weight);
					queue.offer(new Node(node.next, min));
					visited[node.next] = true;
					cnt++;
					
				}
				
			}
		}
		
		
		return cnt;
	}
	
	static class Node{
		int next, weight;

		public Node(int next, int weight) {
			super();
			this.next = next;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "(" + next + ", " + weight + ")";
		}

		
	}
}