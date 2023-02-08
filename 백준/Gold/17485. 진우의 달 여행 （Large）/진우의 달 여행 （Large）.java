import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int[][] map;
	static int minCost = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		int[][][] dp = new int[N][M][3]; // 맨 끝은 방향
		
		// 초기화
		for(int r=0; r<N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				// 초기식
				if(r==0) {
					dp[r][c][0] = map[r][c];
					dp[r][c][1] = map[r][c];
					dp[r][c][2] = map[r][c];
				}
				
				else if(c==M-1) {
				// 오른쪽 끝일 때
					dp[r][c][0] = Integer.MAX_VALUE;
				}
				else if(c == 0){
				// 왼쪽 끝일 때 
					dp[r][c][2] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int i = 1; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(isValid(i-1, j+1)) {
					// 0방향으로 올 수 없음
					dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2])+map[i][j];
				}
				if(isValid(i-1,j)) {
					// 1방향으로 올 수 없음
					dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2])+map[i][j];
				}
				if(isValid(i-1, j-1)) {
					dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + map[i][j];
				}
			}
		}
		
		// dp 마지막 줄
		for(int j=0; j<M; j++) {
			for(int k=0; k<3; k++) {
				if(minCost > dp[N-1][j][k])
					minCost = dp[N-1][j][k];
			}
		}
		
		System.out.println(minCost);
		
		
	}
	static boolean isValid(int y, int x) {
		return (0 <= y && y<N) && (0<=x && x<M);
	}

}