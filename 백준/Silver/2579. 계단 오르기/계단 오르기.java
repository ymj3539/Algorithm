import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int n;
    static int[] stairs;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(input.readLine());
        stairs = new int[n+1];
        for(int i=1; i<=n; i++){
            stairs[i] = Integer.parseInt(input.readLine());
        }

        int[] dp = new int[n+1];

        dp[1] = stairs[1];

        for(int i=2; i<=n; i++){
            if(i == 2){
                dp[2] = stairs[1] + stairs[2];
            }else if(i == 3){
                dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];
            }else {
                // 계단을 2+1로 왔을 때와 2로 올라왔을 때를 비교
                dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
            }
        }

        System.out.println(dp[n]);

    }
}