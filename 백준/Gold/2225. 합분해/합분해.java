import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        int[][] dp = new int[N+1][K+1];

        for(int i=0; i<=N; i++){
            dp[i][1] = 1;
        }

        for(int k=0; k<=K; k++){
            dp[0][k] = 1;
        }

        for(int n=1; n<=N; n++){
            for(int k=1; k<=K; k++){
                dp[n][k] = (dp[n][k-1] + dp[n-1][k]) % 1000000000;
            }
        }

        System.out.println(dp[N][K]);

    }
}