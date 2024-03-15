import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int Max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++) {
                visited[r][c] = true;
                dfs(r, c, 1, map[r][c]);
                checkblock(r, c);
                visited[r][c] = false;
            }
        }

        System.out.println(Max);
    }

    // dfs로 체크할 수 없는 'ㅗ'모양은 따로 체크
    static void checkblock(int r, int c){
        // 'ㅜ' 체크
        // 범위 안에 있으면 max값 체크
        if(r>=0 && r<N-1 && c>=0 && c<M-2){
            int sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1];
            Max = Math.max(Max, sum);
        }

        // 회전
        // 'ㅏ' 체크
        // 범위 안에 있으면 max값 체크
        if(r>=0 && r<N-2 && c>=0 && c<M-1){
            int sum = map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+2][c];
            Max = Math.max(Max, sum);
        }

        // 회전
        // 'ㅗ' 체크
        // 범위 안에 있으면 max값 체크
        if(r>=0 && r<N-1 && c>=0 && c<M-2){
            int sum = map[r][c+1] + map[r+1][c] + map[r+1][c+1] + map[r+1][c+2];
            Max = Math.max(Max, sum);
        }

        // 회전
        // 'ㅓ' 체크
        // 범위 안에 있으면 max값 체크
        if(r>=0 && r<N-2 && c>=0 && c<M-1){
            int sum = map[r+1][c] + map[r][c+1] + map[r+1][c+1] + map[r+2][c+1];
            Max = Math.max(Max, sum);
        }
    }

    static void dfs(int r, int c, int cnt, int sum){
        if(cnt == 4) {
            Max = Math.max(Max, sum);
            return;
        }

        for(int i=0; i<4; i++){
            int dr = r + dy[i];
            int dc = c + dx[i];

            if(!isIn(dr, dc)) continue;
            if(visited[dr][dc]) continue;

            visited[dr][dc] = true;
            dfs(dr, dc, cnt+1, sum+map[dr][dc]);
            visited[dr][dc] = false;
        }
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
}