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
        nemo(0);

        System.out.println(ans);

    }

    static void nemo(int cnt){
        if(cnt == N*M){
            ans++;
            return;
        }

        int c = cnt%M;
        int r = cnt/M;

        if(r>=1 && c>=1 && map[r-1][c] && map[r][c-1] && map[r-1][c-1]){
            nemo(cnt+1);
        }else{
            nemo(cnt+1);
            map[r][c] = true;
            nemo(cnt+1);
            map[r][c] = false;

        }


    }
}