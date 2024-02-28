import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K;
    static Obj[] objects;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        objects = new Obj[N+1];

        for(int i=1; i<=N; i++) {
            tokens = new StringTokenizer(input.readLine());
            int w = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());

            objects[i] = new Obj(w, v);
        }

        // i번째 아이템까지 담을 수 있고, 최대 j무게까지 담을 수 있을 때의 최대 value
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++){
            for(int j=0; j<=K; j++){
                if(j >= objects[i].w){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-objects[i].w] + objects[i].v);
                }else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        System.out.println(dp[N][K]);

    }

    static class Obj{
        int w, v;
        public Obj(int w, int v){
            this.w = w;
            this.v = v;
        }

    }
}