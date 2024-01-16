import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] map;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 0, 1};
    static int CNT;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        map = new int[N+1][N+1];

        for(int r=1; r<=N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=1; c<=N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        dfs(1, 1, 1, 2, 0);
        System.out.println(CNT);
    }

    static void dfs(int r1, int c1, int r2, int c2, int state) {
        if (r2 == N && c2 == N) {
            CNT++;
            return;
        }

        // 가로일때
        if (state == 0) {
            // 오른쪽으로 이동
            if(isIn(r2+dy[0], c2+dx[0])){
                dfs(r2, c2, r2+dy[0], c2+dx[0], 0);
            }

            // 대각선으로 이동
            if(isIn(r2+dy[2], c2+dx[2]) && canGo(r1, c1, r2, c2)){
                dfs(r2, c2, r2+dy[2], c2+dx[2], 2);
            }

        }
        // 세로일때
        else if (state == 1) {
            // 밑으로 이동
            if(isIn(r2+dy[1], c2+dx[1])){
                dfs(r2, c2, r2+dy[1], c2+dx[1], 1);
            }

            // 대각선으로 이동
            if(isIn(r2+dy[2], c2+dx[2]) && canGo(r1, c1, r2, c2)){
                dfs(r2, c2, r2+dy[2], c2+dx[2], 2);
            }
        }
        // 대각선일때
        else {
            // 오른쪽으로 이동
            if(isIn(r2+dy[0], c2+dx[0])){
                dfs(r2, c2, r2+dy[0], c2+dx[0], 0);
            }
            // 밑으로 이동
            if(isIn(r2+dy[1], c2+dx[1])){
                dfs(r2, c2, r2+dy[1], c2+dx[1], 1);
            }

            // 대각선으로 이동
            if(isIn(r2+dy[2], c2+dx[2]) && canGo(r1, c1, r2, c2)){
                dfs(r2, c2, r2+dy[2], c2+dx[2], 2);
            }
        }
    }

    static boolean canGo(int r1, int c1, int r2, int c2){
        return (map[r2+dy[0]][c2+dx[0]] == 0 && map[r2+dy[1]][c2+dx[1]]==0);
    }

    static boolean isIn(int r, int c){
        return r>=1 && r<=N && c>=1 && c<=N && map[r][c] != 1;
    }
}