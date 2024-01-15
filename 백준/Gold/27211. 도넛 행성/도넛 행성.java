import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
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

        int ans = 0;

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c] == 0 && !visited[r][c]){
                    // visited 체크
                    bfs(r, c);
                    ans++;
                }
            }
        }

        System.out.println(ans);


    }

    static void bfs(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int d=0; d<4; d++){
                int dr = cur.r + dy[d];
                int dc = cur.c + dx[d];

                dr = change(dr, N);
                dc = change(dc, M);

                if(map[dr][dc] == 1) continue;
                if(visited[dr][dc]) continue;

                queue.offer(new Point(dr, dc));
                visited[dr][dc] = true;
            }
        }
    }

    static int change(int x, int y){
        if(x < 0) return y-1;
        else if(x >= y) return 0;
        else return x;
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}