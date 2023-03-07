
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		M = Integer.parseInt(input.readLine());
		
		// 부모 저장 배열 만들기
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<=N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=1; j<=N; j++) {
				int tmp = Integer.parseInt(tokens.nextToken());
				
				// 만약 1이면(연결되어 있으면) union 연산
				if(tmp == 1) {
					union(i , j);
				}
			}
		}
		
		
		tokens = new StringTokenizer(input.readLine());
		int start = Integer.parseInt(tokens.nextToken());
		String str = "YES";
		for(int i=1; i<M; i++) {
			int tmp = Integer.parseInt(tokens.nextToken());
			if(find(start) != find(tmp)) {
				str = "NO";
				break;
			}
		}
		
		System.out.println(str);
	}
	
	
	// find(부모 찾기) 
	public static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	
	// union 연산
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) {
			if(x < y) {
				parent[y] = x;
			}else parent[x] = y;
		}
	}
}
