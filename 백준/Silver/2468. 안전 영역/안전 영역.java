import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] map;
    static int N;

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static int rain;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());

        map = new int[N][N];

        for(int r=0; r<N; r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }
        rain = 0;
        int max = Integer.MIN_VALUE;

        while(true){
            visited = new boolean[N][N];
            int cnt = 0;
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(!visited[r][c] && map[r][c] > rain) {
                        bfs(r,c);
                        cnt++;
                    }
                }
            }

            if(cnt == 0) break;
            rain++;
            max = Math.max(cnt, max);
        }

        System.out.println(max);

    }

    static void bfs(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<dy.length; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc]) continue;
                if(map[dr][dc] <= rain) continue;

                queue.offer(new Point(dr, dc));
                visited[dr][dc] = true;
            }
        }
    }

    static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<N && c>=0 && c<N);
    }
}