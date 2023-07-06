import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static int N, M, R;
    static int[][] map;
    static char[][] ans;
    static int[] dy = {0, 0, 1, -1};    // E W S N
    static int[] dx = {1, -1, 0, 0};
    static int Score;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        map = new int[N+1][M+1];
        ans = new char[N+1][M+1];
        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=M; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
                ans[r][c] = 'S';
            }
        }

        int total = 0;

        for(int i =0; i<R; i++){
            // 공격
            tokens = new StringTokenizer(input.readLine());
            int X = Integer.parseInt(tokens.nextToken());
            int Y = Integer.parseInt(tokens.nextToken());
            char D = tokens.nextToken().charAt(0);
            int dir =0;

            if(D == 'E') dir = 0;
            else if(D == 'W') dir = 1;
            else if(D == 'S') dir = 2;
            else if(D == 'N') dir = 3;

            // 공격 수행
            Score = 0;
            attack(X, Y, dir);

            // 수비
            tokens = new StringTokenizer(input.readLine());
            X = Integer.parseInt(tokens.nextToken());
            Y = Integer.parseInt(tokens.nextToken());

            // 수비 수행
            defence(X, Y);

            total += Score;


        }

        sb.append(total+"\n");
        for(int r=1; r<=N; r++){
            for(int c=1; c<=M; c++){
                sb.append(ans[r][c]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void defence(int r, int c){
        ans[r][c] = 'S';
    }

    static void attack(int r, int c, int d){
        // 공격 받은 도미노 처리

        int h = map[r][c];
        if(ans[r][c] != 'F'){
            ans[r][c] = 'F';
            Score++;
        }

        int dr = r;
        int dc = c;

        // 연쇄로 공격 받을 도미노
        for(int i=0; i<h-1; i++){
            dr += dy[d];
            dc += dx[d];

            if(!isIn(dr, dc)) continue;
            if(ans[dr][dc] == 'F') continue;
            attack(dr, dc, d);
        }
    }

    static boolean isIn(int r, int c){
        return (r>0 && c>0 && r<=N && c<=M);
    }


}