import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long cnt;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        int[][] dp = new int[N+1][10];
        for(int i=0; i<=9; i++){
            dp[0][i] = 1;
        }

        for(int i=1; i<=N; i++){
            for(int j=0; j<10; j++){
                for(int k=j; k<10; k++){
                    dp[i][j] += dp[i-1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        System.out.println(dp[N][0]);
    }

}