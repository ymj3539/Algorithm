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
	
	static int N;
	static char[][] map;
	static List<Point> obs = new ArrayList<>();
	static List<Point> teachers = new ArrayList<>();
	static int[] dx = {0, 0, -1, 1};	// 상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static String answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		map = new char[N][N];
		
		for(int r = 0; r<N; r++) {
			String[] src = input.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = src[c].charAt(0);
				if(map[r][c] == 'X') {
					obs.add(new Point(r,c));
				}
				if(map[r][c] == 'T') {
					teachers.add(new Point(r,c));
				}
			}
		}
		
		// 장애물을 3개 선택
		answer = "NO";
		makeCombination(0, new Point[3], 0);
		System.out.println(answer);
	}
	
	static void makeCombination(int nth, Point[] choosed, int startIdx) {
		if(answer.equals("YES")) return;
		if(nth == 3) {
			// 선생님과 만나는지 체크
//			System.out.println(Arrays.toString(choosed));
			int[][] copyMap = new int[N][N];
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					copyMap[r][c] = map[r][c];
				}
			}
			
			for(int i=0; i<choosed.length; i++) {
				Point tmp = choosed[i];
				copyMap[tmp.r][tmp.c] = 'O';
			}
			
			for(int i=0; i<teachers.size(); i++) {
				Point teacher = teachers.get(i);
				check(copyMap, teacher);
//				System.out.println(teacher.toString()+" :"+answer);
				if(answer.equals("NO")) return;
			}
			return;
		}
		
		for(int i = startIdx; i<obs.size(); i++) {
			choosed[nth] = obs.get(i);
			makeCombination(nth+1, choosed, i+1);
		}
	}
	
	static void check(int[][] copyMap, Point teacher) {
		int r = teacher.r;
		int c = teacher.c;
		
		for(int i=0; i<dx.length; i++) {
			int nr = r;
			int nc = c;
			while(isIn(nr,nc)) {
				nr += dy[i];
				nc += dx[i];
				
				if(isIn(nr,nc)) {
					if(copyMap[nr][nc] == 'O') {
						break;
					}
					else if(copyMap[nr][nc] == 'S') {
//						System.out.println(nr+","+nc+" :"+answer);
						answer = "NO";
						return;
					}
				}
			}
		}
		answer = "YES";
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
	static class Point {
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
}
