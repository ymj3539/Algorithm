import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R, C;
	static char[][] map;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map = new char[R][C];
		for(int r=0; r<R; r++) {
			String str = input.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		boolean[] checked = new boolean[91];
		checked[map[0][0]] = true;
		dfs(0, 0, 1, checked);
		System.out.println(max);
	}
	static void dfs(int r, int c, int cnt, boolean[] checked) {
		max = Math.max(max, cnt);
		
		for(int i=0; i<4; i++) {
			int dr = r + dy[i];
			int dc = c + dx[i];
			if(!isIn(dr,dc)) continue;
			if(checked[map[dr][dc]]) continue;
			checked[map[dr][dc]] = true;
			dfs(dr, dc, cnt+1, checked);
			checked[map[dr][dc]] = false;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<R && c<C);
	}

}