import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N, x, y;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		Point[] arr = new Point[N];
		for(int i=0; i<N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			arr[i] = new Point(x, y);
		}
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++) {
			int x= arr[i].x1;
			int y = arr[i].y1;
			sb.append(x+" "+y+"\n");
		}
		System.out.println(sb);
	}
	
	static class Point implements Comparable<Point>{
		int x1, y1;

		public Point(int x1, int y1) {
			super();
			this.x1 = x1;
			this.y1 = y1;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if(this.y1 == o.y1) {
				return (this.x1 - o.x1);
			}else return (this.y1-o.y1);
		}
		
	}
}