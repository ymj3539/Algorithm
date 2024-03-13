import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    static int[] coin;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        coin = new int[N+1];
        int[] dp = new int[K+1]; // dp[i] : 동전 가치의 합이 i가 되는 경우의 수
        for(int i=1; i<=N; i++){
            coin[i] = Integer.parseInt(input.readLine());
        }

        dp[0] = 1;

        // 동전 하나씩 돌기
        for(int i =1; i<=N; i++){
            // K까지 1씩 증가시키면서 dp 테이블 갱신
            for(int k = 1; k<=K; k++){
                if(k < coin[i]) continue;
                // 이전까지 가치 k를 만들수 있는 경우의 수 + 현재 코인(coin[i])을 포함했을 때의 경우의 수
                dp[k] = dp[k] + dp[k - coin[i]];
            }
        }

        System.out.println(dp[K]);
    }
}