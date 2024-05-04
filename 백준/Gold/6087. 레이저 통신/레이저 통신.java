import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static char[][] map;
    static int W, H;
    static Point start;
    static Point end;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());

        W = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        map = new char[H][W];

        for(int r=0; r<H; r++){
            String str = input.readLine();
            for(int c=0; c<W; c++){
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'C'){
                    if(start == null){
                        start = new Point(r, c);
                    }else {
                        end = new Point(r, c);
                    }
                }
            }
        }

        bfs();
        System.out.println(min);

    }

    static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        int[][][] visited = new int[H][W][4];
        for(int r=0; r<H; r++){
            for(int c=0; c<W; c++){
                Arrays.fill(visited[r][c], Integer.MAX_VALUE);
            }
        }
        queue.offer(new Point(start.r, start.c, -1, -1));
        for(int i=0; i<4; i++){
            visited[start.r][start.c][i] = 0;
        }


        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(cur.cnt != -1 && cur.r == end.r && cur.c == end.c) {
                min = Math.min(min, cur.cnt);
            }

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                if(!isIn(dr, dc)) continue;
                if(visited[dr][dc][i] <= cur.cnt) continue;
                if(map[dr][dc] == '*') continue;

                if(cur.dir != i){
                    queue.offer(new Point(dr, dc, i, cur.cnt + 1));
                    visited[dr][dc][i] = cur.cnt + 1;
                }else {
                    queue.offer(new Point(dr, dc, i, cur.cnt));
                    visited[dr][dc][i] = cur.cnt;
                }
            }
        }
    }

    static boolean isIn(int r, int c){
        return r>=0 && r<H && c>=0 && c<W;
    }

    static class Point{
        int r, c, dir, cnt;
        public Point(int r, int c, int dir, int cnt){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}