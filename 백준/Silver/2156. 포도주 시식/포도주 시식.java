import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] wine;
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(input.readLine());
        wine = new int[n+1];
        for(int i=1; i<=n; i++){
            wine[i] = Integer.parseInt(input.readLine());
        }

        int[] dp = new int[n+1];

        dp[1] = wine[1];
        if(n>1){
            dp[2] = wine[1] + wine[2];
        }

        for(int i=3; i<=n; i++){
            dp[i] = Math.max(dp[i-1], Math.max(wine[i] + dp[i-2], wine[i] + wine[i-1] + dp[i-3]));
        }

        System.out.println(dp[n]);
    }


}