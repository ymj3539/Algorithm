import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int N;
	static int[][] map;
	static int[] dx = {1, 1, 0}; // 우(0), 대각선(1), 하(2)
	static int[] dy = {0, 1, 1};
	static int[][] pipes = {
			{0, 1},
			{0, 1, 2},
			{1, 2}
	};
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		map = new int[N+1][N+1];
		for(int r=1; r<=N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=1; c<=N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		
//		for(int[] i: map) {
//			System.out.println(Arrays.toString(i));
//		}
		
		dfs(1, 2, 0);
		System.out.println(cnt);
	}
	
	static void dfs(int r, int c, int type) {
		if(r==N && c==N) {
			cnt++;
			return;
		}
		
		for(int i=0; i<pipes[type].length; i++) {
			int dr = r + dy[pipes[type][i]];
			int dc = c + dx[pipes[type][i]];
			
			// 범위 안에 있는지 체크
			if(!isIn(dr,dc)) continue;
			// 벽이 있는지 체크
			if(!canGo(dr, dc, pipes[type][i])) continue;
			
			dfs(dr, dc, pipes[type][i]);
		}
	}
	
	static boolean canGo(int r, int c, int type) {
		// 가로 파이프일때
		if(type == 0) {
			if(map[r][c] == 1) return false;
		}else if(type== 1) {
			if(map[r][c] ==1 || map[r][c-1] ==1 || map[r-1][c] == 1) return false;
		}else if(type == 2){
			if(map[r][c] == 1) return false;
		}
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return (r>0 && c>0 && r<=N && c<=N);
	}
	
}
