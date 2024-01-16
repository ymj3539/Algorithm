import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        map = new int[N+1][N+1];

        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        long[][][] dp = new long[N+1][N+1][3];
        dp[1][2][0] = 1;

        // 0: 가로, 1:세로, 2:대각선

        for(int r=1; r<=N; r++){
            for(int c=3; c<=N; c++){
                if(map[r][c] == 1) continue;

                // 가로
                dp[r][c][0] = dp[r][c-1][0] + dp[r][c-1][2];
                // 세로
                dp[r][c][1] = dp[r-1][c][1] + dp[r-1][c][2];
                // 대각선
                if(map[r-1][c] == 1 || map[r][c-1] == 1) continue;
                dp[r][c][2] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
            }
        }
        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }


}