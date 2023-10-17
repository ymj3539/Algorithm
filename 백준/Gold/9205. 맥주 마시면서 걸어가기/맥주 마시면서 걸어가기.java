import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb;
	
	static int T, N;
	static Point start, end;
	static List<Point> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			start = new Point(x,y);
			
			list = new LinkedList<>();
			for(int i=0; i<N; i++) {
				tokens = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				
				list.add(new Point(a,b));
			}
			
			tokens = new StringTokenizer(input.readLine());
			x = Integer.parseInt(tokens.nextToken());
			y = Integer.parseInt(tokens.nextToken());
			end = new Point(x,y);
			
			boolean ans = bfs();
			System.out.println(ans? "happy":"sad");
		}
		
	}
	static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			
			Point cur = queue.poll();
			// 락페 바로 갈수 있나?
			if(canGo(cur.x, cur.y, end.x, end.y)) {
				return true;
			}
			
			// 갈수 없으면 편의점 꺼내
			for(int i=0; i<list.size(); i++) {
				Point store = list.get(i);
				
				if(visited[i]) continue;
				
				// 1000m 이내인 편의점
				if(canGo(cur.x, cur.y, store.x, store.y)) {
					queue.offer(store);
					visited[i] = true;
				}
				
			}
			
			
		}
		return false;
	}
	
	static boolean canGo(int x1, int y1, int x2, int y2) {
		int d = Math.abs(x1-x2) + Math.abs(y1-y2);
		if(d<=1000) return true;
		
		else return false;
		
	}
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
		
	}
	
}
