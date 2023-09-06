import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        int[] W = new int[N+1];
        int[] V = new int[N+1];
        for(int i=1; i<=N; i++){
            tokens = new StringTokenizer(input.readLine());
            W[i] = Integer.parseInt(tokens.nextToken());
            V[i] = Integer.parseInt(tokens.nextToken());
        }

        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++){
            for(int w=i; w<=K; w++){
                dp[i][w] = dp[i-1][w];
                if(W[i] <= w){
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - W[i]] + V[i]);
                }

            }
        }

        System.out.println(dp[N][K]);
    }
}