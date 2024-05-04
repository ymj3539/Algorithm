import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, T;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        int[] days = new int[N+1];
        int[] money = new int[N+1];

        int totalMoney = 0;

        for(int i=1; i<=N; i++){
            tokens = new StringTokenizer(input.readLine());
            days[i] = Integer.parseInt(tokens.nextToken());
            money[i] = Integer.parseInt(tokens.nextToken());
            totalMoney += money[i];
        }

        int[][] dp = new int[N+1][T+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=T; j++){
                // 주어진 일수 > 과제 하는데 걸리는 일수 -> 그래야 과제할 수 있음
                if(j >= days[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], money[i] + dp[i - 1][j - days[i]]);
                }else {
                    // 현재 과제는 할 수 없으면 이전 과제까지가 최대값
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(totalMoney - dp[N][T]);


    }
}