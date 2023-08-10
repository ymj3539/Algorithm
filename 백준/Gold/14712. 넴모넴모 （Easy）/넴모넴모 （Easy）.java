import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static boolean[][] map;
    static int ans;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new boolean[N][M];
        ans = 0;
        nemo(0, 0,0);

        System.out.println(ans);

    }

    static void nemo(int r, int c, int cnt){
        if(cnt == N*M){
            // 넴모 개수 세기
            boolean flag = false;
            for(int i=0; i<=N-2; i++){
                for(int j=0; j<=M-2; j++){
                    if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]){
                        return;

                    }
                }
            }

            ans++;
            return;
        }

        int dc = c + 1;
        int dr = r;
        if(dc == M) {
            dc = 0;
            dr = r+1;
        }


        map[r][c] = true;
        nemo(dr, dc, cnt+1);
        map[r][c] = false;
        nemo(dr, dc, cnt+1);

    }
}