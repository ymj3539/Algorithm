import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N, I, M;
	static Point[] fishes;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		I = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		fishes = new Point[M];
		
		for(int i=0; i<M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
//			map[r][c] = 1;
			fishes[i] = new Point(r, c);
		}
		
//		for(int[] i : map) {
//			System.out.println(Arrays.toString(i));
//		}
		
		// i는 세로 길이, j는 가로 길이
		for(int h=1, w=I/2-1; h<I/2; h++, w--) {
			for(int i =0; i<fishes.length; i++) {
					int r = fishes[i].r;
					int c = fishes[i].c;
					
					for(int dc=c-w; dc<=c; dc++) {
						int dr = r;
						if(!isIn(dr,dc)) continue;
						// 그물에 잡히는 물고기 개수 구하기
						int cnt = catchFish(dr, dc, h, w);
//						System.out.println(r+" "+c+" "+h+" "+w+" cnt: "+cnt);
						max = Math.max(max, cnt);
					}
			}
		}
		
		
		System.out.println(max);
	}
	
	// r,c는 시작점, h,w는 그물의 높이,너비
	static int catchFish(int r, int c, int h, int w) {
		int cnt =0;
//		for(int i =r; r<=h+r; r++) {
//			for(int j=c; j<=w+c; c++) {
//				if(map[i][j] ==1 ) cnt++;
//			}
//		}
		
		for(int i =0; i<fishes.length; i++) {
			int nr = fishes[i].r;
			int nc = fishes[i].c;
			
			if(nr>=r && nr<=h+r && nc>=c && nc<=w+c) {
				cnt++;
			}
		}
		
		return cnt;
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
		
		
	}
	
	static boolean isIn(int r, int c) {
		return (r>=1 && c>=1 && r<=N && c<=N);
	}
}
