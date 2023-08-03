import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        String str1 = input.readLine();
        String str2 = input.readLine();

        int str1_len = str1.length();
        int str2_len = str2.length();

        int[][] dp = new int[str1_len+1][str2_len+1];

        int max = 0;

        for(int i=1; i<=str1_len; i++){
            for(int j=1; j<=str2_len; j++){
                // 같은 문자가 나오면 표시해주자
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    // 연속된 문자 수 증가 시킴 (대각선 방향 이전값 + 1)
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.print(max);

    }
}