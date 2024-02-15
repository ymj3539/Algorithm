import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int R, C;
    static boolean[][][] map;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        C = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        map = new boolean[R+1][C+1][2];

        int K = Integer.parseInt(input.readLine());
        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());

            // 가로줄이 공사
            if(b==d){
                if(a<c){
                    map[d][c][0] = true;
                }else if(c<a){
                    map[b][a][0] = true;
                }
            }
            // 세로줄이 공사
            else if(a==c){
                if(b<d){
                    map[d][c][1] = true;
                }else if(d<b){
                    map[b][a][1] = true;
                }
            }
        }

        long[][] dp = new long[R+1][C+1];

        // 1개의 경로 가지는거 먼저 체크
        // 가로 체크
        for(int i=1; i<=C; i++){
            if(map[0][i][0]) break;
            dp[0][i] = 1;
        }

        // 세로 체크
        for(int i=1; i<=R; i++){
            if(map[i][0][1]) break;
            dp[i][0] = 1;
        }


        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                if(!map[i][j][0]) {
                    dp[i][j] += dp[i][j-1];
                }

                if(!map[i][j][1]){
                    dp[i][j] += dp[i-1][j];
                }

            }
        }

        System.out.println(dp[R][C]);
    }
}