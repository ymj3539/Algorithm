import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K, R; // N : 목초지 크기, K : 소 마리수, R : 길 갯수
    static int[][] map;
    static Point[] cows;
    static boolean[][][][] roads;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args)throws Exception{
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        map = new int[N+1][N+1];
        roads = new boolean[N+1][N+1][N+1][N+1];
        cows = new Point[K];

        for(int i=0; i<R; i++){
            tokens = new StringTokenizer(input.readLine());
            int r1 = Integer.parseInt(tokens.nextToken());
            int c1 = Integer.parseInt(tokens.nextToken());
            int r2 = Integer.parseInt(tokens.nextToken());
            int c2 = Integer.parseInt(tokens.nextToken());

            roads[r1][c1][r2][c2] = true;
            roads[r2][c2][r1][c1] = true;
        }

        for(int i=0; i<K; i++){
            tokens = new StringTokenizer(input.readLine());
            int r = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            cows[i] = new Point(r, c);
            map[r][c] = 1;
        }

        int ans = 0;
        for(int i=0; i<K; i++){
            ans += bfs(cows[i]);
        }

        System.out.println(ans/2);

    }

    static int bfs(Point start){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        queue.offer(start);
        visited[start.r][start.c] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr,dc)) continue;
                if(visited[dr][dc]) continue;
                if(roads[cur.r][cur.c][dr][dc]) continue;

                queue.offer(new Point(dr, dc));
                visited[dr][dc] = true;
            }
        }

        int cnt = 0;
        for(int i=0; i<cows.length; i++){
            Point cur = cows[i];
            if(!visited[cur.r][cur.c]) cnt++;
        }

        return cnt;
    }

    static boolean isIn(int r, int c){
        return (r>=1 && c>=1 && r<=N && c<=N);
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}