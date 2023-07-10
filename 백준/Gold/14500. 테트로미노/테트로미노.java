import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
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
            for(int c=0; c<M; c++){
                visited[r][c] = true;
                dfs(new Point(r, c), 1, map[r][c]);
                visited[r][c] = false;
                tetromino5_check(r, c);
            }
        }

        System.out.println(Max);

    }

    static void tetromino5_check(int r, int c){
        if(r>=0 && r<N-1 && c>=0 && c<M-2){
            int sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1];
            Max = Math.max(Max, sum);
        }

        if(r>=0 && r<N-2 && c>=0 && c<M-1){
            int sum = map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+2][c];
            Max = Math.max(Max, sum);
        }

        // 3번째
        if(r>=0 && r<N-1 && c>=0 && c<M-2){
            int sum = map[r+1][c] + map[r][c+1] + map[r+1][c+1] + map[r+1][c+2];
            Max = Math.max(Max, sum);
        }

        // 4번째
        if(r>=0 && r<N-2 && c>=0 && c<M-1){
            int sum = map[r][c+1] + map[r+1][c] + map[r+1][c+1] + map[r+2][c+1];
            Max = Math.max(Max, sum);
        }
    }

    static void dfs(Point cur, int cnt, int sum){
        if(cnt == 4){
            // max 비교
            Max = Math.max(Max, sum);
            return;
        }

        for(int i=0; i<4; i++){
            int dr = cur.r + dy[i];
            int dc = cur.c + dx[i];

            if(!isIn(dr, dc)) continue;
            if(visited[dr][dc]) continue;
            visited[dr][dc] = true;
            dfs(new Point(dr, dc), cnt+1, sum+map[dr][dc]);
            visited[dr][dc] = false;
        }

    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<M);
    }

    static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}