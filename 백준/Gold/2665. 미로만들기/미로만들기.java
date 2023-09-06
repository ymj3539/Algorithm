import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static char[][] map;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(input.readLine());
        map = new char[n][n];

        for(int i=0; i<n; i++){
            map[i] = input.readLine().toCharArray();
        }

        bfs();
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }

    static void bfs(){
        Queue<Point> queue = new LinkedList();
        queue.offer(new Point(0,0, 0));
        int[][] black = new int[n][n];
        for(int[] i : black){
            Arrays.fill(i, Integer.MAX_VALUE);
        }

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(cur.r == n-1 && cur.c == n-1){
                min = Math.min(min, cur.cnt);
            }

            for(int i=0; i<4; i++){
                int dr = cur.r + dy[i];
                int dc = cur.c + dx[i];

                int cnt = cur.cnt;
                if(!isIn(dr, dc)) continue;
                if(cur.cnt >= black[dr][dc]) continue;
                if(map[dr][dc] == '0') {
                    cnt = cur.cnt + 1;
                }
                queue.offer(new Point(dr, dc, cnt));
                black[dr][dc] = cnt;
            }

        }

    }

    static class Point{
        int r, c, cnt;
        public Point(int r, int c, int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static boolean isIn(int r, int c){
        return (r>=0 && r<n && c>=0 && c<n);
    }
}