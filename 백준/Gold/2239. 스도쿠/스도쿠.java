import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map = new int[9][9];
	static int[][] ans = new int[9][9];
	static boolean flag;
	static List<Point> list = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		for(int i=0; i<9; i++) {
			String str = input.readLine();
			for(int j=0; j<9; j++) {
				map[i][j] = (str.charAt(j)-'0');
				if(map[i][j] == 0) {
					list.add(new Point(i,j));
				}
			}
		}
		
		dfs(0);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(ans[i][j]);
			}
			System.out.println();
		}
	}
	
	static void dfs(int nth) {
		if(flag) return;
		if(nth == list.size()) {
			flag = true;
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					ans[i][j] = map[i][j];
				}
			}
			return;
		}
		
		// 리스트에서 빈칸 좌표 하나씩 꺼내기
		Point cur = list.get(nth);
		int cr = cur.r;
		int cc = cur.c;
		// 후보 숫자를 체크하기 위한 배열
		boolean[] checked = new boolean[10];
		
		// 가로, 세로, 칸 탐색 -> 후보 숫자 리스트에 담아
		checked = checkedMap(cr, cc, checked);
		// 리스트 돌면서 하나씩 map에 넣고 다음 dfs 진행
		for(int i=1; i<10; i++) {
			if(!checked[i]) {
				map[cr][cc] = i;
				dfs(nth+1);
			}
		}
		map[cr][cc] = 0;
	}
	
	private static boolean[] checkedMap(int r, int c, boolean[] checked) {
		// 가로 탐색
		for(int i=0; i<9; i++) {
			// 0이 아니면 checked 배열에 true로 체크
			if(map[r][i] != 0) {
				checked[map[r][i]] = true;
			}
		}
		
		// 세로 탐색
		for(int i=0; i<9; i++) {
			// 0이 아니면 checked 배열에 true로 체크
			if(map[i][c] != 0) {
				checked[map[i][c]] = true;
			}
		}
		
		// 칸 탐색
		int sr = r/3 * 3;
		int sc = c/3 * 3;
		for(int i = sr; i<sr+3; i++) {
			for(int j = sc; j<sc+3; j++) {
				if(map[i][j] != 0) {
					checked[map[i][j]] = true;
				}
			}
		}
		return checked;
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
}
