import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int V;
	static int max;
	static int totalMax;
	static class Node{
		int n;
		int d;
		public Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
	static List[] nodeArray;
	static List<Integer> finalNode;
	static int startNode;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		V = Integer.parseInt(input.readLine());
		nodeArray = new List[V+1];
		int start = 0;
		int sizeMax = Integer.MIN_VALUE;
		
		for(int i=1; i<=V; i++) {
			List<Node> list = new ArrayList<>();
			tokens = new StringTokenizer(input.readLine());
			int n = Integer.parseInt(tokens.nextToken()); // 정점 번호
			
			// 해당 정점과 연결된 노드를 리스트에 다 넣어줌
			while(true) {
				int v = Integer.parseInt(tokens.nextToken());
				if(v == -1) break;
				int dist = Integer.parseInt(tokens.nextToken());
				list.add(new Node(v, dist));
			}
			nodeArray[n] = list;
			int sizeTmp = list.size();
			if(sizeMax < sizeTmp) {
				sizeMax = sizeTmp;
				start = n;
			}
		}
		
		max = Integer.MIN_VALUE;
		dfs(1, new boolean[V+1], 0);
		max = Integer.MIN_VALUE;
		dfs(startNode, new boolean[V+1], 0);
		totalMax = Math.max(max, totalMax);
		
		
		System.out.println(totalMax);
	}
	
	static void dfs(int start, boolean[] visited, int sum) {
		visited[start] = true;
		if(max < sum) {
			startNode = start;
			max = sum;
		}
		
		List<Node> list = nodeArray[start];
		for(int i=0; i<list.size(); i++) {
			Node cur = list.get(i);
			
			// System.out.println("cur.n: "+cur.n+", cur.d: "+cur.d);
			if(visited[cur.n]) continue;
			dfs(cur.n, visited, sum + cur.d);
		}
		
	}
}
